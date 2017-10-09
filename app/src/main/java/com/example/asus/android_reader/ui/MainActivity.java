package com.example.asus.android_reader.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.asus.android_reader.IBookLoader;
import com.example.asus.android_reader.R;
import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.BookMeta;

import java.util.List;

public class MainActivity extends Activity implements IBookLoader, IFileDisplayer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this.getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        }, 3000);
    }



    @Override
    public void displayBooks(List<BookMeta> bookList) {
      //  bookAdapter.setBookMetas(bookList);

    }

    @Override
    public void displayBook(Book book) {

    }

    @Override
    public void onLoadFinish(Book book) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
