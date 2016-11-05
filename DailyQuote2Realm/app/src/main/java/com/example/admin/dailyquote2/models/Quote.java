package com.example.admin.dailyquote2.models;

import io.realm.RealmObject;

/**
 * Created by Admin on 10/26/2016.
 */

public class Quote extends RealmObject{
    private String title;
    private String content;

    public Quote() {

    }
    public static Quote create(String title,String content){
        Quote quote = new Quote();
        quote.setContent(content);
        quote.setTitle(title);
        return quote;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Quote{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
