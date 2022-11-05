package com.example.kv;

/**
 * Created by LiuShen on 2018/9/3 0003.
 */
public class MainBean {


    private String title;
    private String Time;
    private String Category;
    private String Price;
    private int Thumbnail;

    public MainBean() {
    }

    public MainBean(String title, String time , String category, String price, int thumbnail) {
        this.title = title;
        Time = time;
        Category = category;
        Price = price;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }


}
