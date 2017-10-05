package com.example.asus.android_reader.domain.entities;

import java.util.List;

/**
 * Created by Asus on 29.09.2017.
 */

public class Book {

    private String name;

    private String text;

    private List<Page> pages;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
