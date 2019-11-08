package com.harman.phonehealth.service.presenter;

import android.util.Log;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.base.BasePresenter;
import com.harman.phonehealth.common.UsageModel;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.service.DataContract;
import com.harman.phonehealth.util.RoomUtils;
import com.harman.phonehealth.utils.DateTransUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataPresenter extends BasePresenter<DataContract.View> implements DataContract.Presenter {
    private final UsageModel mUsageModel;
    public DataPresenter(DataContract.View view,UsageModel usageModel) {
        super(view);
        mUsageModel = usageModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        insertData();

    }

    private void insertData(){
        Log.i("DataService","insertData");
        if(mUsageModel!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String str=simpleDateFormat.format(date);
            Log.i("DataService","today str="+str);
            List<String> days = getDaysBetwwen(7);
            List<PackageInfoBean> list = new ArrayList<PackageInfoBean>();
            if (days!=null){
                for (int i=0;i<days.size();i++){
                    list.addAll(mUsageModel.getOneDayData(days.get(i)));
                    Log.i("DataService","days.get(i)= "+days.get(i));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("DataService","Thread insert  list=");
                        RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoBeanDao().deletePackageInfoBean();
                        RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoBeanDao().insertPackageInfos(list);
                        Log.i("DataService","day ,"+" select   date.getTime()="+date.getTime());
                        long temp = 1572537600000L;
                        long startTime = 0L;
                        long endTime = 0L;
                        try {
                            startTime = DateTransUtils.getStartClockTimeStamp(str);
                            endTime = DateTransUtils.getEndClockTimeStamp(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoBeanDao().findPackageInfoSomeAppSevenDayUsedTimeAsc("相册",startTime,endTime).subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .subscribe(new SingleObserver<List<PackageInfoBean>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(List<PackageInfoBean> packageInfoBeans) {
                                        if ( packageInfoBeans!= null) {
                                            Log.i("DataService","day ,"+" select  packageInfoBeans.size()="+packageInfoBeans.size());
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("DataService","day ,"+" select  packageInfoBeans. onError()=");
                                    }
                                });


                    }
                }).start();

            }
        }

    }

    private   Date getDateAdd(int days){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }
    private List<String> getDaysBetwwen(int days){
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(df.format(d));
            dayss.add(df.format(d));
            time += oneDay;
        }
        Log.i("DataService","today dayss="+dayss);
        return dayss;
    }
}
