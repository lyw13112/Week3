package com.bawei.week3.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bawei.week3.R;

/**
 * @author 刘云蔚
 * @createTime 2020/1/11 10:26
 * @description
 */
public class AdderView extends LinearLayout {

    private TextView text_jia;
    private TextView text_num;
    private TextView text_jian;
    private int num = 1;

    public void setNum(int num) {
        this.num = num;
    }

    public AdderView(Context context) {
        super(context);
        initView(context);
    }

    public AdderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public AdderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.custom_adder, this);
        text_jia = view.findViewById(R.id.text_jia);
        text_num = view.findViewById(R.id.text_num);
        text_jian = view.findViewById(R.id.text_jian);
        text_jian.setOnClickListener(v -> {
            num--;
            if (num < 1) {
                Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                num = 1;
            }
            text_num.setText(num + "");
            if (onClickListener != null) onClickListener.onClick(num);
        });
        text_jia.setOnClickListener(v -> {
            num++;
            text_num.setText(num + "");
            if (onClickListener != null) onClickListener.onClick(num);
        });
    }

    public AdderOnClickListener onClickListener;

    public interface AdderOnClickListener {
        void onClick(int num);
    }

    public void setOnClickListener(AdderOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}