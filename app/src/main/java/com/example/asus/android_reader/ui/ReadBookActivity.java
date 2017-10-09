package com.example.asus.android_reader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asus.android_reader.R;
import com.example.asus.android_reader.domain.FilePresenterImpl;
import com.example.asus.android_reader.domain.IFilePresenter;
import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.BookMeta;
import com.example.asus.android_reader.domain.entities.Page;
import com.example.asus.android_reader.ui.adapter.PageFragmentAdapter;
import com.example.asus.android_reader.utils.PageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02.10.2017.
 */

public class ReadBookActivity extends AppCompatActivity implements IFileDisplayer {

    private TextView textView;
    IFilePresenter presenter;
    private String bookName;
    private ViewPager viewPager;
    private PageFragmentAdapter pagerAdapter;
    private boolean isBookLoaded = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        bookName = getIntent().getStringExtra("name");
        textView = (TextView) findViewById(R.id.activity_read_book_text);

//        textView.setMovementMethod(new ScrollingMovementMethod());


        presenter = new FilePresenterImpl(this);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                PageBuilder pageBuilder = new PageBuilder();
                int numberOfCharsPerScreen = 1;
                numberOfCharsPerScreen =
                        pageBuilder.getNumberOfCharsPerLine(textView) * pageBuilder.getNumberOfLinesPerScreen(textView);
                System.out.println(numberOfCharsPerScreen + "    NUMBER!!!!!!!");
                presenter.getBookByName(bookName, numberOfCharsPerScreen);
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


    @Override
    public void displayBooks(List<BookMeta> bookList) {

    }

    @Override
    public void displayBook(Book book) {
        pagerAdapter = new PageFragmentAdapter(getSupportFragmentManager(),book.getPages());
        viewPager.setAdapter(pagerAdapter);
        isBookLoaded = true;
        System.out.println("TEXTTTTTTTTTTTTTTTTTTTTt" + book.getPages().get(0).getContent());
        System.out.println("PAGES-------------------------" + book.getPages().size());
        pagerAdapter.setPages(book.getPages());
    }

    public IFilePresenter getPresenter() {
        return presenter;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isBookLoaded() {
        return isBookLoaded;
    }

    public void setBookLoaded(boolean bookLoaded) {
        isBookLoaded = bookLoaded;
    }
}
