package com.example.asus.android_reader.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.asus.android_reader.R;
import com.example.asus.android_reader.domain.FilePresenterImpl;
import com.example.asus.android_reader.domain.IFilePresenter;
import com.example.asus.android_reader.domain.entities.Page;
import com.example.asus.android_reader.utils.PageBuilder;

import java.util.Random;

/**
 * Created by Asus on 03.10.2017.
 */

public class PageFragment extends Fragment {
    static final String TAG = "myLogs";

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String SAVE_PAGE_NUMBER = "save_page_number";

    String bookName;
    IFilePresenter presenter;
    private TextView pageTextView;
    String pageContent;
    int backColor;

    public static PageFragment newInstance(Page page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PAGE_NUMBER, page.getContent());
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageContent = getArguments().getString(ARGUMENT_PAGE_NUMBER);
        Log.d(TAG, "onCreate: " + pageContent);


//        int savedPageNumber = -1;
//        if (savedInstanceState != null) {
//            savedPageNumber = savedInstanceState.getInt(SAVE_PAGE_NUMBER);
//        }
//        Log.d(TAG, "savedPageNumber = " + savedPageNumber);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pages, null);

        pageTextView = (TextView) view.findViewById(R.id.view_pages_text_view);
        pageTextView.setText("Page " + pageContent);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //    outState.putString(SAVE_PAGE_NUMBER, pageContent);
    }


}
