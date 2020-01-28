package com.bawei.week3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week3.MainActivity;
import com.bawei.week3.R;
import com.bawei.week3.ShowActivity;
import com.bawei.week3.entity.DataBean;
import com.bawei.week3.widget.AdderView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 18:34
 * @description 
 */
public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.Holder> {

    private Context context;
    private List<DataBean.ResultBean.ShoppingCartListBean> list;
    private ShowActivity showActivity;
    private int mPosition;

    public SmallAdapter(Context context, List<DataBean.ResultBean.ShoppingCartListBean> list, int position) {
        this.context = context;
        this.list = list;
        showActivity = (ShowActivity) context;
        this.mPosition = position;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_small, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.check_item.setChecked(list.get(position).isChecked);
        holder.text_name.setText(list.get(position).commodityName);
        holder.text_price.setText(list.get(position).price + "");
        Glide.with(context).load(list.get(position).pic).into(holder.imageView);

        //条目多选框监听
        holder.check_item.setOnClickListener(v -> {
            list.get(position).isChecked = holder.check_item.isChecked();
            //发送是否商家全选
            callback.back(mPosition, isAllCheck());
            //计算总价++是否全选
            showActivity.countPrice();
        });

        //加减器监听
        holder.adderView.setNum(list.get(position).num);
        holder.adderView.setOnClickListener((AdderView.AdderOnClickListener) num -> {
            list.get(position).num = num;
            notifyDataSetChanged();
            showActivity.countPrice();
        });

    }

    public boolean isAllCheck() {
        boolean isAllCheck = true;
        for (DataBean.ResultBean.ShoppingCartListBean bean : list) {
            if (!bean.isChecked) {
                isAllCheck = false;
            }
        }
        return isAllCheck;
    }

    SmallCallback callback;

    public interface SmallCallback {
        void back(int position, boolean isChecked);
    }

    public void setCallback(SmallCallback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.text_name)
        TextView text_name;
        @BindView(R.id.text_price)
        TextView text_price;
        @BindView(R.id.check_item)
        CheckBox check_item;
        @BindView(R.id.adderView)
        AdderView adderView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
