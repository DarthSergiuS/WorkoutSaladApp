package com.martyniv.workoutsaladapp.model;


public class WorkoutItem {

    private int id;
    private String itemTitle;
    private int imageResId;
    private String text;
    private String type;
    private String subTitle;



    public WorkoutItem(int id, String itemTitle, String text, String type) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.text = text;
        this.type = type;

    }
    public WorkoutItem(String itemTitle, String text, String type) {
        this.itemTitle = itemTitle;
        this.text = text;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
