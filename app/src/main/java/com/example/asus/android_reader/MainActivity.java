package com.example.asus.android_reader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.textclassifier.TextSelection;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements IBookLoader {


    private static final String FILE_NAME ="kniga.fb2";
    ReadTextTask readTextTask;
    MyTask mt;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    // открытие файла
    public void openText(View view){
        textView = (TextView) findViewById(R.id.open_text);
        textView.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());
        readTextTask = new ReadTextTask(this);
        readTextTask.setProgressBar(progressBar);
        String str = null;
        readTextTask.execute();


    }

    @Override
    public void onLoadFinish(String text) {
        textView.setText(text);
    }

    class MyTask extends AsyncTask<Void,Void,Void>{
        StringBuilder text;

        @Override
        protected Void doInBackground(Void... voids) {
            FileInputStream fin = null;

            try {
                String s = Environment.getExternalStorageDirectory()+"/" + "Download" + "/" +FILE_NAME;
                text = new StringBuilder();

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

            }
            finally{

                try{
                    if(fin!=null)
                        fin.close();
                }
                catch(IOException ex){
                    //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView = (TextView) findViewById(R.id.open_text);
            textView.setMovementMethod(new ScrollingMovementMethod());
            progressBar.setVisibility(ProgressBar.VISIBLE);
            textView.setText("");
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            textView.setText(text);


        }
    }
}
