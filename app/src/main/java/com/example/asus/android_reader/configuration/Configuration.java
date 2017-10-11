package com.example.asus.android_reader.configuration;

import android.graphics.Color;

/**
 * Created by Asus on 11.10.2017.
 */

public class Configuration {

    public Configuration() {
    }

    private static int textSize;

    private static Color textColor;

    private static Color backgroundColor;

    private static int screenBrightness;

    public static int getTextSize() {
        return textSize;
    }

    public static void setTextSize(int textSize) {
        Configuration.textSize = textSize;
    }

    public static Color getTextColor() {
        return textColor;
    }

    public static void setTextColor(Color textColor) {
        Configuration.textColor = textColor;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        Configuration.backgroundColor = backgroundColor;
    }

    public static int getScreenBrightness() {
        return screenBrightness;
    }

    public static void setScreenBrightness(int screenBrightness) {
        Configuration.screenBrightness = screenBrightness;
    }
}
