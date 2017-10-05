package com.example.asus.android_reader.domain.entities;

/**
 * Created by Asus on 29.09.2017.
 */

public class Page {

    private int number;

    private String content;

    public Page() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
