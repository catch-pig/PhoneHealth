package com.harman.phonehealth.mvp.statistics.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StatisticsActivity extends BasePresenterActivity<StatisticsContract.Presenter> implements StatisticsContract.View
        , OnChartValueSelectedListener {
    @BindView(R.id.lcTest)
    LineChart lcTest;
    @BindView(R.id.tvSevenCount)
    TextView tvSevenCount;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.lcTime)
    LineChart lcTime;
    @BindView(R.id.tvSevenTime)
    TextView tvSevenTime;
    Map<String,PackageInfoBean> mPackageInfoBeans;
    String appName;

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
        appName = getIntent().getStringExtra("appName");
        if (appName != null && !appName.trim().equals("")) {
            setDatas();
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

    long startTime;
    private void setDatas() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        long endTime = 0;
        try {
            endTime = dateFormat.parse(year + "-" + month + "-" + day).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startTime = endTime - (1000 * 60 * 60 * 24 * 6);
        RoomUtils.getDataBase(getBaseActivity())
                .packageInfoBeanDao()
                .findPackageInfoSomeAppSevenDayUsedTimeAsc(appName, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PackageInfoBean>>() {
                    @Override
                    public void accept(List<PackageInfoBean> packageInfoBeans) throws Exception {
                        tvSevenCount.setText("App Used Counts:");
                        tvSevenTime.setText("App Used Time:");
                        tvTitle.setText(packageInfoBeans.get(0).getAppName());
                        mPackageInfoBeans = new HashMap<>();
                        for(int j = 0;j<7;j++){
                            long day = startTime+1000 * 60 * 60 * 24 * j;
                            String data = dateFormat.format(new Date(day));
                            for(int i = 0;i<packageInfoBeans.size();i++){
                                long time = packageInfoBeans.get(i).getDate();
                                if(time == day){
                                    mPackageInfoBeans.put(data,packageInfoBeans.get(i));
                                    break;
                                }
                            }
                        }

                         initCountLineChart();
                        initTimeLineChart();
                    }
                });
    }

    /**
     * 设置折线图的数据
     */
    private void setCountLineChartData() {
        //填充数据，在这里换成自己的数据源
        List<Entry> valsComp1 = new ArrayList<>();
//        List<Entry> valsComp2 = new ArrayList<>();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        for (int i = 0; i < 7; i++) {
            String data = dateFormat.format(new Date(startTime+1000 * 60 * 60 * 24 * i));
            PackageInfoBean packageInfoBean = mPackageInfoBeans.get(data);
            if (packageInfoBean == null) {
                valsComp1.add(new Entry(i, 0));
            } else {
                valsComp1.add(new Entry(i, packageInfoBean.getUsedCount()));
            }

        }

//        valsComp2.add(new Entry(0, 2));
//        valsComp2.add(new Entry(1, 0));
//        valsComp2.add(new Entry(2, 4));
//        valsComp2.add(new Entry(3, 2));

        //这里，每重新new一个LineDataSet，相当于重新画一组折线
        //每一个LineDataSet相当于一组折线。比如:这里有两个LineDataSet：setComp1，setComp2。
        //则在图像上会有两条折线图，分别表示公司1 和 公司2 的情况.还可以设置更多
        LineDataSet setComp1 = new LineDataSet(valsComp1, "使用次数");
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

    private void setTimeLineChartData() {
        //填充数据，在这里换成自己的数据源
        List<Entry> valsComp1 = new ArrayList<>();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        for (int i = 0; i < 7; i++) {
            String data = dateFormat.format(new Date(startTime+1000 * 60 * 60 * 24 * i));
            PackageInfoBean packageInfoBean = mPackageInfoBeans.get(data);
            if (packageInfoBean == null) {
                valsComp1.add(new Entry(i, 0));
            } else {
                long useTime = packageInfoBean.getUsedTime();
                float time = Long.valueOf(useTime).floatValue() / 1000 / 60;
                DecimalFormat fnum = new DecimalFormat("##0.0");
                float dd = Float.parseFloat(fnum.format(time));
                valsComp1.add(new Entry(Integer.valueOf(i).floatValue(),dd));
            }

        }

        //这里，每重新new一个LineDataSet，相当于重新画一组折线
        //每一个LineDataSet相当于一组折线。比如:这里有两个LineDataSet：setComp1，setComp2。
        //则在图像上会有两条折线图，分别表示公司1 和 公司2 的情况.还可以设置更多
        LineDataSet setComp1 = new LineDataSet(valsComp1, "Used Time(unit:mimute)");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.colorPrimary));
        setComp1.setDrawCircles(false);
        setComp1.setMode(LineDataSet.Mode.LINEAR);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        LineData lineData = new LineData(dataSets);

        lcTime.setData(lineData);
        lcTime.invalidate();
    }

    /**
     * 初始化折线图控件属性
     */
    private void initCountLineChart() {
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

        setCountLineChartData();
    }

    private void initTimeLineChart() {
        lcTime.setOnChartValueSelectedListener(this);
        lcTime.getDescription().setEnabled(false);
        lcTime.setBackgroundColor(Color.WHITE);

        //自定义适配器，适配于X轴
        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter();

        XAxis xAxis = lcTime.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        //自定义适配器，适配于Y轴
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = lcTime.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        lcTime.getAxisRight().setEnabled(false);

        setTimeLineChartData();
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


        @Override

        public String getFormattedValue(float value, AxisBase axis) {
            int position = (int) value;
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (position >= 7) {
                position = 0;
            }
            if (position == 6) {
                return "Today";
            }
            return "" + month + "-" + (day - (6 - position));
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
    }
}
