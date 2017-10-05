package com.example.asus.android_reader.ui;

import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.BookMeta;

import java.util.List;

/**
 * Created by Asus on 29.09.2017.
 */

public interface IFileDisplayer {

    void displayBooks(List<BookMeta> bookList);

    void displayBook(Book book);
}
