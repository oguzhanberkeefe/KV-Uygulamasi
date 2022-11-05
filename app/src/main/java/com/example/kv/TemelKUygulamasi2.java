package com.example.kv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TemelKUygulamasi2 extends AppCompatActivity {

    private List<MainBean2> list;
    private MainAdapter2 adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private LinearLayoutManager manager;
    private LinearLayoutManager manager2;

    /**
     * 需要定位的地方，从小到大排列，需要和tab对应起来，长度一样
     */
    private int[] str1 = {0, 8, 27, 38, 59};
    private boolean isScrolled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temel_kuygulamasi2);
        initData();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.mian_recy);
        recyclerView2 = findViewById(R.id.mian_recy2);

        manager = new LinearLayoutManager(this);
        manager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView2.setLayoutManager(manager2);
        adapter = new MainAdapter2(list);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:

                        Toast.makeText(TemelKUygulamasi2.this, "0", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，12都是滑动
                if (newState == 0) {
                    isScrolled = false;
                } else {
                    isScrolled = true;
                }
                setMsg("isScrolled" + isScrolled + "--newState=" + newState);
            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法
                if (isScrolled) {
                    int top = manager.findFirstVisibleItemPosition();
                    int bottom = manager.findLastVisibleItemPosition();

                    int pos = 0;
                    if (bottom == list.size() - 1) {
                        //先判断滑到底部，tab定位到最后一个
                        pos = str1.length - 1;
                    } else if (top == str1[str1.length - 1]) {
                        //如果top等于指定的位置，对应到tab即可，
                        pos = str1[str1.length - 1];
                    } else {
                        //循环遍历，需要比较i+1的位置，所以循环长度要减1，
                        //  如果 i<top<i+1,  那么tab应该定位到i位置的字符，不管是向上还是向下滑动
                        for (int i = 0; i < str1.length - 1; i++) {
                            if (top == str1[i]) {
                                pos = i;
                                break;
                            } else if (top > str1[i] && top < str1[i + 1]) {
                                pos = i;
                                break;
                            }
                        }
                    }
                }

            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
            }
        });

        recyclerView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


    }


    public static void setMsg(String str) {
        Log.i("tab", str);
    }

    private void initData() {
        list = new ArrayList<>();
            list.add(new MainBean2("Cappucino"));
            list.add(new MainBean2("Espresso"));
            list.add(new MainBean2("Americano"));
            list.add(new MainBean2("Machiato"));
            list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        list.add(new MainBean2("Cappucino"));
        list.add(new MainBean2("Espresso"));
        list.add(new MainBean2("Americano"));
        list.add(new MainBean2("Machiato"));
        list.add(new MainBean2("Frappe"));
        }



    }