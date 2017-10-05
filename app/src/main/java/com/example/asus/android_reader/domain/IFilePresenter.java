package com.example.asus.android_reader.domain;

import com.example.asus.android_reader.domain.entities.Book;

/**
 * Created by Asus on 29.09.2017.
 */

public interface IFilePresenter {

    void loadBooks();

    Book getBookByName(String name, int numberOfCharsPerScreen);

}
