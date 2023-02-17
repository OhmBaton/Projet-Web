package com.example.model;

public class DataBean {
    private String date_created;
    private String secondary_creator;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date) {
        this.date_created = date_created;
    }

    public String getSecondary_creator() {
        return secondary_creator;
    }

    public void setSecondary_creator(String secondary_creator) {
        this.secondary_creator = secondary_creator;
    }
}
