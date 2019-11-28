package com.harman.phonehealth.mvp.statistics.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.harman.phonehealth.R;
import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BasePresenterActivity;
import com.harman.phonehealth.di.module.StatisticsModule;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.mvp.statistics.StatisticsContract;
import com.harman.phonehealth.util.RoomUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StatisticsActivity extends BasePresenterActivity<StatisticsContract.Presenter> implements StatisticsContract.View
        , OnChartValueSelectedListener {
    @BindView(R.id.lcTest)
    LineChart lcTest;
    @BindView(R.id.tvSevenCount)
    TextView tvSevenCount;
    List<PackageInfoBean> mPackageInfoBeans;
    String pakageName;
    //    private Typeface mTfRegular;
//    private Typeface mTfLight;
//    private LineChart lineChart;
    @Override
    protected void initParam() {

    }
    @Override
    protected void injectComponent() {
        PhoneHealthApp.getAppComponent().statisticsComponent(new StatisticsModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        pakageName = getIntent().getStringExtra("appName");
        if(pakageName!=null&&!pakageName.trim().equals("")){
             RoomUtils.getDataBase(getBaseActivity()).packageInfoBeanDao()
                    .findPackageInfoWithPackageName(pakageName).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<PackageInfoBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfoBean> packageInfoBeans) {
                            if(packageInfoBeans!=null&&packageInfoBeans.size()>0){
                                mPackageInfoBeans = packageInfoBeans;
                                tvSevenCount.setText(mPackageInfoBeans.get(0).getAppName()+":Seven-day Usage");
                                initLineChart();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    });
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_statistics;
    }

    @Override
    public void initStatisticsView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    /**
     * 设置折线图的数据
     */
    private void setLineChartData() {
        //填充数据，在这里换成自己的数据源
        List<Entry> valsComp1 = new ArrayList<>();
//        List<Entry> valsComp2 = new ArrayList<>();

        for(int i = 0;i<7;i++){
            PackageInfoBean packageInfoBean = mPackageInfoBeans.get(i);
            if(i>=mPackageInfoBeans.size()){
                valsComp1.add(new Entry(i,0));
            }else {
                valsComp1.add(new Entry(i,packageInfoBean.getUsedCount()));
            }

        }

//        valsComp2.add(new Entry(0, 2));
//        valsComp2.add(new Entry(1, 0));
//        valsComp2.add(new Entry(2, 4));
//        valsComp2.add(new Entry(3, 2));

        //这里，每重新new一个LineDataSet，相当于重新画一组折线
        //每一个LineDataSet相当于一组折线。比如:这里有两个LineDataSet：setComp1，setComp2。
        //则在图像上会有两条折线图，分别表示公司1 和 公司2 的情况.还可以设置更多
        LineDataSet setComp1 = new LineDataSet(valsComp1,"使用次数");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.colorPrimary));
        setComp1.setDrawCircles(false);
        setComp1.setMode(LineDataSet.Mode.LINEAR);

//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2 ");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//        setComp2.setDrawCircles(true);
//        setComp2.setColor(getResources().getColor(R.color.design_default_color_primary));
//        setComp2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
//        dataSets.add(setComp2);

        LineData lineData = new LineData(dataSets);

        lcTest.setData(lineData);
        lcTest.invalidate();
    }
    /**
     * 初始化折线图控件属性
     */
    private void initLineChart() {
        lcTest.setOnChartValueSelectedListener(this);
        lcTest.getDescription().setEnabled(false);
        lcTest.setBackgroundColor(Color.WHITE);

        //自定义适配器，适配于X轴
        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter();

        XAxis xAxis = lcTest.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        //自定义适配器，适配于Y轴
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = lcTest.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        lcTest.getAxisRight().setEnabled(false);

        setLineChartData();
    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public class XAxisValueFormatter implements IAxisValueFormatter {
        private String[] xStrs = new String[]{"Mon", "Tus", "Wen", "Thu","Fri","Sta","Sun"};

        @Override

        public String getFormattedValue(float value, AxisBase axis) {
            int position = (int) value;
            if (position >= 7) {
                position = 0;
            }
            return xStrs[position];
        }
    }
    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {
            mFormat = new DecimalFormat("###,###,###,###");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mFormat.format(value);
        }
    }
    public class DecimalFormatter implements IAxisValueFormatter {
        private DecimalFormat format;

        public DecimalFormatter() {
            format = new DecimalFormat("###,###,###");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return format.format(value) + "$";
        }
    }}
