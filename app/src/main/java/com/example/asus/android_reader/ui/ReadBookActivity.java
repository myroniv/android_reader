package com.example.asus.android_reader.ui;

import android.app.Activity;
import android.os.Bundle;
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
import com.example.asus.android_reader.utils.PageBuilder;

import java.util.List;

/**
 * Created by Asus on 02.10.2017.
 */

public class ReadBookActivity extends Activity implements IFileDisplayer {

    private ProgressBar progressBar;
    private TextView textView;
    IFilePresenter presenter;
    private String bookName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read_book);
        bookName = getIntent().getStringExtra("name");
        textView = (TextView) findViewById(R.id.open_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
        presenter = new FilePresenterImpl(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
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
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void displayBooks(List<BookMeta> bookList) {

    }

    @Override
    public void displayBook(Book book) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        textView.setText(book.getPages().get(0).getContent());
        System.out.println("TEXTTTTTTTTTTTTTTTTTTTTt" + book.getPages().get(0).getContent());
        System.out.println("PAGES-------------------------" + book.getPages().size());
    }
}
