package com.harman.phonehealth.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.harman.phonehealth.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * 创建时间:2017/12/21  19:45<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017年12月22日13:43:56<br/>
 * 描述: RecyclerViewAdapter基类
 */

public abstract class RecyclerAdapter<M,VH extends BaseViewHolder>
        extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<M> mData = new ArrayList<>();
    /**
     * 头部类型
     */
    public static final int TYPE_HEADER = -1;
    /**
     * 底部类型
     */
    public static final int TYPE_FOOTER = -2;
    /**
     * 无数据类型
     */
    public static final int TYPE_EMPTY = -3;
    /**
     * 正常的item
     */
    public static final int TYPE_NORMAL = 0;
    /**
     * 头部
     */
    private View mHeaderView;

    /**
     * 底部
     */
    private View mFooterView;
    /**
     * 是否展示空页面
     */
    private boolean showEmpty;
    /**
     * 空页面layout
     */
    private int emptyLayout = R.layout.view_load_empty;
    /**
     * 是否是第一次加载数据
     */
    private boolean firstLoad = true;
    protected OnItemClickListener mListener;


    public RecyclerAdapter() {
    }

    /**
     * 设置空页面
     */
    public void setEmptyLayout(@LayoutRes int emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    /**
     * 添加头部
     */
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    /**
     * 获取头部
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 添加底部
     */
    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    /**
     * 获取底部
     */
    public View getFooterView() {
        return mFooterView;
    }

    /**
     * 设置每行点击事件的监听
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public List<M> getData() {
        if (isNull(mData)) {
            throw new IllegalStateException("mData is null(数据为空)");
        }
        return mData;
    }

    private boolean isNull(List<M> list){
        if (list!=null) {
            return false;
        }
        return true;
    }

    public M get(int position) {
        if (isNull(mData)) {
            return null;
        }
        if (position < 0 || position > (mData.size() - 1)) {
            throw new IllegalStateException("position必须大于0,且不能大于mData的个数");
        }
        return mData.get(position);
    }

    /**
     * 设置list为这个list
     */
    public void set(List<M> data) {
        firstLoad = false;
        if (data != null) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * list中添加更多的数据
     */
    public void add(List<M> data) {
        if (mData == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0 && showEmpty) {
            //当前数据空位,展示空页面
            return TYPE_EMPTY;
        }
        if (position == 0 && mHeaderView != null) {
            //当前view是头部信息
            return TYPE_HEADER;
        }
        if (position == getItemCount() && mFooterView != null) {
            //当前view是底部信息
            return TYPE_FOOTER;
        }

        return getCenterViewType(position);
    }

    /**
     * 标准的item的类型
     *
     * @return 返回参数不能小于0
     */
    @IntRange(from = 0)
    public int getCenterViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        if (mHeaderView != null) {
            //有头部,item的个数+1
            size++;
        }
        if (mFooterView != null) {
            //有底部,item的个数+1
            size++;
        }
        if (size == 0) {
            showEmpty = true;
            size = 1;
        } else {
            showEmpty = false;
        }
        return size;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载头部信息
        if (TYPE_HEADER == viewType) {
            return new HeaderAndFooterViewHolder(mHeaderView);
        }
        //加载底部信息
        if (TYPE_FOOTER == viewType) {
            return new HeaderAndFooterViewHolder(mFooterView);
        }
        //加载空页面
        if (TYPE_EMPTY == viewType) {
            View v = inflate(emptyLayout, parent);
            return new HeaderAndFooterViewHolder(v);
        }
        return createHolder(parent, viewType);
    }


    /**
     * 除头部和底部的ViewHolder的获取
     *
     * @param viewType holder的类型
     */
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType){
        return reflectViewHolder(parent);
    }


    protected abstract int layoutId();

    protected abstract Class<VH> viewHolderClass();

    /**
     * 反射获得ViewHolder
     */
    @SuppressWarnings("unchecked")
    private VH reflectViewHolder(ViewGroup parent) {
        View v = inflate(layoutId(), parent);
        VH holder = null;
        try {
            Constructor<VH> con = viewHolderClass().getConstructor(View.class);
            holder = con.newInstance(v);
        } catch (NoSuchMethodException e) {
            System.err.println("检查ViewHolder类及构造函数是否是public");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return holder;
    }

    /**
     * 获取需要viewHolder的view
     *
     * @param layoutId 布局文件
     */
    protected View inflate(int layoutId, ViewGroup group) {
        LayoutInflater inflater = LayoutInflater.from(group.getContext());
        return inflater.inflate(layoutId, group, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        int index = position;
        if (mHeaderView != null) {
            //当前holder是头部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_HEADER) {
                return;
            } else {
                /*
                 * 有头部的情况,需要要减1,否则取item的数据会取到当前数据的下一条,
                 * 取出最后一条数据的时候,会报下标溢出
                 */
                index--;
            }
        }
        if (mFooterView != null) {
            //当前holder是底部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_FOOTER) {
                return;
            }
        }
        //空页面状态,不需要设置holder的内容
        if (getItemViewType(position) == TYPE_EMPTY) {
            //第一次加载数据,不展示空页面
            if (firstLoad) {
                holder.itemView.setVisibility(View.INVISIBLE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }
        final int finalIndex = index;
        final M m = mData.get(index);
        //设置item的点击回调事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.itemClick(mRecyclerView.getId(), m, finalIndex);
                }
            }
        });

        bindViewHolder((VH) holder, m, position);
    }

    /**
     * 绑定viewHolder的数据
     */
    public abstract void bindViewHolder(VH holder, M m, int position);

    protected RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER
                            || getItemViewType(position) == TYPE_FOOTER)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p =
                    (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 头部和底部的ViewHolder
     */
    static class HeaderAndFooterViewHolder extends BaseViewHolder {

        public HeaderAndFooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener<M> {
        /**
         * @param id RecyclerView.getId()
         * @param m item下的实体
         * @param position item所在的位置
         */
        void itemClick(@IdRes int id, M m, int position);
    }
}
