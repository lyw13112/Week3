package com.bawei.week3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week3.R;
import com.bawei.week3.ShowActivity;
import com.bawei.week3.entity.DataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 18:30
 * @description
 */
public class BigAdapter extends RecyclerView.Adapter<BigAdapter.Holder> {

    private Context context;
    private List<DataBean.ResultBean> list;
    public SmallAdapter adapter;
    private ShowActivity showActivity;

    public BigAdapter(Context context, List<DataBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        showActivity = (ShowActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_big, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.check_item_all.setText(list.get(position).categoryName);
        List<DataBean.ResultBean.ShoppingCartListBean> shoppingCartList = list.get(position).shoppingCartList;
        holder.small_recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SmallAdapter(context, shoppingCartList,position);
        holder.small_recycler.setAdapter(adapter);

        holder.check_item_all.setChecked(list.get(position).isChecked);
        holder.check_item_all.setOnClickListener(v -> {
            boolean checked = holder.check_item_all.isChecked();
            list.get(position).isChecked = checked;

            //条目全选反选
            for (DataBean.ResultBean.ShoppingCartListBean bean : list.get(position).shoppingCartList) {
                bean.isChecked = checked;
            }
            notifyDataSetChanged();

            //计算总价
            showActivity.countPrice();
        });

        adapter.setCallback((position1, isChecked) -> {
            list.get(position1).isChecked = isChecked;
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_item_all)
        CheckBox check_item_all;
        @BindView(R.id.small_recycler)
        RecyclerView small_recycler;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
