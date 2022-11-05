package com.example.kv;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<MainBean,BaseViewHolder>{
    public MainAdapter(List<MainBean> data) {
        super(R.layout.main_item, data);
    }

    protected void convert(BaseViewHolder helper, MainBean item) {

        TextView tv_product_title;
        TextView tv_product_time;
        TextView tv_product_category;
        TextView tv_product_price;
        ImageView img_product_thumbnail;

        tv_product_title = (TextView) helper.getView(R.id.product_title_id);

        tv_product_time = (TextView) helper.getView(R.id.product_time_id);

        tv_product_category = (TextView) helper.getView(R.id.product_category_id);

        tv_product_title.setText(item.getTitle());

        tv_product_time.setText(item.getTime());

        tv_product_category.setText(item.getCategory());

        img_product_thumbnail = (ImageView) helper.getView(R.id.product_img_id);

        img_product_thumbnail.setImageResource(item.getThumbnail());


    }


    public  void setMsg(String str) {
        Log.i("tab1", str);
    }
}