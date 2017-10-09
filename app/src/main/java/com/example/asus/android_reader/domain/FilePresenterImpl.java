package com.example.asus.android_reader.domain;

import android.os.Environment;

import com.example.asus.android_reader.IBookLoader;
import com.example.asus.android_reader.ReadTextTask;
import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.BookMeta;
import com.example.asus.android_reader.ui.IFileDisplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 29.09.2017.
 */

public class FilePresenterImpl implements IFilePresenter, IBookLoader {

    ReadTextTask readTextTask;
    IFileDisplayer displayer;
    private int numberCharactersInScreen;

    public FilePresenterImpl(IFileDisplayer displayer) {
        this.displayer = displayer;
    }


    @Override
    public void loadBooks() {

        String path = Environment.getExternalStorageDirectory().toString() + "/Download";
        final File folder = new File(path);
        listFilesForFolder(folder);

    }


    @Override
    public Book getBookByName(String name, int numberCharactersInScreen) {
        Book book = new Book();

        String path = Environment.getExternalStorageDirectory().toString() + "/Download/" + name;

        readTextTask = new ReadTextTask(this);
        readTextTask.setNumberOfCharsPerScreen(numberCharactersInScreen);
        readTextTask.execute(path);


        return book;
    }



    public void listFilesForFolder(final File folder) {
        List<BookMeta> listBooks = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);

            } else {
                System.out.println(fileEntry.getName());
                BookMeta bookMeta = new BookMeta();
                String fileName = fileEntry.getName();
                bookMeta.setName(fileName);
                listBooks.add(bookMeta);
            }
        }
        displayer.displayBooks(listBooks);
    }


    @Override
    public void onLoadFinish(Book book) {
        displayer.displayBook(book);
    }
}
