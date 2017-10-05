package com.example.asus.android_reader.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.asus.android_reader.IBookLoader;
import com.example.asus.android_reader.R;
import com.example.asus.android_reader.domain.FilePresenterImpl;
import com.example.asus.android_reader.domain.IFilePresenter;
import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.BookMeta;
import com.example.asus.android_reader.ui.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements IBookLoader, IFileDisplayer {

    private RecyclerView recyclerView;


    private IFilePresenter presenter;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private BookAdapter bookAdapter;
    TextView textView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.book_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        bookAdapter = new BookAdapter(new ArrayList<BookMeta>());
        recyclerView.setAdapter(bookAdapter);
        presenter = new FilePresenterImpl(this);
        presenter.loadBooks();


    }


    @Override
    public void onLoadFinish(Book book) {
        textView.setText(book.getText());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void displayBooks(List<BookMeta> bookList) {
        bookAdapter.setBookMetas(bookList);

    }

    @Override
    public void displayBook(Book book) {

    }
}
