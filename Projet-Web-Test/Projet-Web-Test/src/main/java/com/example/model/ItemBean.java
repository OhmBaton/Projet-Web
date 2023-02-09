package com.example.model;

import java.util.ArrayList;

public class ItemBean {
    private ArrayList<DataBean> data = new ArrayList<>();
    private ArrayList<LinksBean> links = new ArrayList<>();

    public ArrayList<LinksBean> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinksBean> links) {
        this.links = links;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setDatas(ArrayList<DataBean> datas) {
        this.data = data;
    }
}
