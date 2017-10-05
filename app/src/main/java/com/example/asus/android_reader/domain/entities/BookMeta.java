package com.example.asus.android_reader.domain.entities;

/**
 * Created by Asus on 29.09.2017.
 */

public class BookMeta {
    private String name;

    public BookMeta(String name) {
        this.name = name;
    }

    public BookMeta() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
