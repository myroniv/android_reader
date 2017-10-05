package com.example.asus.android_reader;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asus.android_reader.domain.entities.Book;
import com.example.asus.android_reader.domain.entities.Page;
import com.example.asus.android_reader.utils.PageBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 26.09.2017.
 */

public class ReadTextTask extends AsyncTask<String, Void, Book> {
    private static final String FILE_NAME = "kniga.fb2";
    private IBookLoader loader;

    StringBuilder text;
    TextView textView;
    ProgressBar progressBar;

    private int numberOfCharsPerScreen;

    public int getNumberOfCharsPerScreen() {
        return numberOfCharsPerScreen;
    }

    public void setNumberOfCharsPerScreen(int numberOfCharsPerScreen) {
        this.numberOfCharsPerScreen = numberOfCharsPerScreen;
    }

    public ReadTextTask(IBookLoader loader) {
        this.loader = loader;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Book doInBackground(String... strings) {
        FileInputStream fin = null;
        Book book = new Book();
        try {
            String s = strings[0];
            book.setName(s.substring(s.lastIndexOf('/') + 1, s.length()));
            text = new StringBuilder();
            PageBuilder pageBuilder = new PageBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(s));
                StringBuffer buffer = new StringBuffer();
                Page page = new Page();

                int numberOfPage = 0;
                char chr;
                int counter = 0;
                int c = br.read();
                List<Page> pageList = new ArrayList<>();
                while (c != -1) {
                    chr = (char) c;
                    buffer.append(chr);
                    if (buffer.length() == numberOfCharsPerScreen) {
                        page.setContent(buffer.toString());
                        buffer = new StringBuffer();
                        pageList.add(page);
                        page.setNumber(numberOfPage);
                        numberOfPage++;
                        page = new Page();


                    }
                    c = br.read();


                }
                br.close();
                book.setPages(pageList);
                book.setText(String.valueOf(buffer.toString()));
            } catch (IOException e) {
                //You'll need to add proper error handling here
            }

        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return book;
    }


    @Override
    protected void onPostExecute(Book book) {
        super.onPostExecute(book);
        loader.onLoadFinish(book);
    }
}
