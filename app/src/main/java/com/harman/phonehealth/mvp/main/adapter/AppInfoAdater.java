package com.harman.phonehealth.mvp.main.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.phonehealth.R;
import com.harman.phonehealth.base.adapter.BaseViewHolder;
import com.harman.phonehealth.base.adapter.RecyclerAdapter;
import com.harman.phonehealth.entity.PackageInfoBean;
import com.harman.phonehealth.utils.IconUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppInfoAdater extends RecyclerAdapter<PackageInfoBean, AppInfoAdater.ViewHolder> {

    @Override
    protected int layoutId() {
        return R.layout.item_app_info;
    }

    @Override
    protected Class<ViewHolder> viewHolderClass() {
        return ViewHolder.class;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, PackageInfoBean packageInfoBean, int position) {
        Drawable drawable = IconUtils.getAppIcon(holder.mLogo.getContext(),packageInfoBean.getPackageName());
        holder.mLogo.setImageDrawable(drawable);
        holder.mAppName.setText(packageInfoBean.getAppName());
        holder.mUseCount.setText(String.format("使用次数:%d次",packageInfoBean.getUsedCount()));
        long useTime = packageInfoBean.getUsedTime()/(1000*60);
        if(useTime<1){
            holder.mUseTime.setText("使用时长:<1分钟");
        }else if(useTime>60){
            long hour = useTime/60;
            long minute = useTime%60;
            holder.mUseTime.setText(String.format("使用时长:%d小时%d分钟",hour,minute));
        }else{
            holder.mUseTime.setText(String.format("使用时长:%d分钟",useTime));
        }
    }

    public static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.logo)
        ImageView mLogo;
        @BindView(R.id.app_name)
        TextView mAppName;
        @BindView(R.id.use_time)
        TextView mUseTime;
        @BindView(R.id.user_count)
        TextView mUseCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
