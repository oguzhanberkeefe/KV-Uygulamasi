package com.example.kv;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by LiuShen on 2018/9/3 0003.
 * 用的一个recyclerview库，brvah，很好用的，一搜就有
 */
public class MainAdapter2  extends BaseQuickAdapter<MainBean2,BaseViewHolder>{
    public MainAdapter2(  List<MainBean2> data) {
        super(R.layout.main_item2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean2 item) {

        TextView tv=helper.getView(R.id.main_recy_item2);
        helper.setText(R.id.main_recy_item2,item.getTitle());


    }


    public  void setMsg(String str) {
        Log.i("tab1", str);
    }
}
