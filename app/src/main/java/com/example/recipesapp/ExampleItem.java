package com.example.recipesapp;

public class ExampleItem {
    private String mImageUrl;
    private String mName;
    private String mType;

    public ExampleItem(String imageUrl, String name ,String type){
        mImageUrl = imageUrl;
        mName = name;
        mType = type;

    }
    public String getImageUrl(){
        return mImageUrl;
    }
    public String getName(){
        return mName;
    }
    public String getType(){
        return mType;
    }
}
