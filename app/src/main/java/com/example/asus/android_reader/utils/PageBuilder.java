package com.example.asus.android_reader.utils;

import android.text.TextPaint;
import android.widget.TextView;

import com.example.asus.android_reader.domain.entities.Page;

/**
 * Created by Asus on 03.10.2017.
 */

public class PageBuilder {

    public PageBuilder() {
    }

    public Page buildPage(String content) {
        Page page = new Page();

        return page;
    }


    public static int getNumberOfCharsPerLine(TextView textView) {
        if (textView == null) {
            return 0;
        }
        TextPaint paint = textView.getPaint();
        int wordWidth = (int) paint.measureText("Android Studio " +
                "filters lines that have already been logged but Log itself may filter some levels when ", 0, 1);
        int screenWidth = textView.getContext().getResources().getDisplayMetrics().widthPixels;
        int num = screenWidth / wordWidth;
        return num;
    }

    public static int getNumberOfLinesPerScreen(TextView textView) {
        if (textView == null) {
            return 0;
        }
        int linesPerScreen = textView.getHeight() / (textView.getLineHeight() + (int) textView.getLineSpacingExtra());
        return linesPerScreen;
    }

}
