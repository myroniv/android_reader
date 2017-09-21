package com.example.asus.android_reader;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME ="kniga.fb2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //comment
    }

    // открытие файла
    public void openText(View view){

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.open_text);
        try {
            String s = Environment.getExternalStorageDirectory()+"/" + "Download" + "/" +FILE_NAME;
            StringBuilder text = new StringBuilder();
            textView.setMovementMethod(new ScrollingMovementMethod());
            try {
                BufferedReader br = new BufferedReader(new FileReader(s));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
                //You'll need to add proper error handling here
            }
            textView.setText(text);
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
