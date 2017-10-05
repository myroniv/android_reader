package com.example.asus.android_reader;

import com.example.asus.android_reader.domain.entities.Book;

/**
 * Created by Asus on 26.09.2017.
 */

public interface IBookLoader {
    void onLoadFinish(Book book);
}
