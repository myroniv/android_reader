package com.example.asus.android_reader;

import android.os.AsyncTask;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Asus on 26.09.2017.
 */

public class ReadTextTask extends AsyncTask<Void,Void,String> {
    private static final String FILE_NAME ="kniga.fb2";
    private IBookLoader loader;

    StringBuilder text;
    TextView textView;
    ProgressBar progressBar;

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
        progressBar.setVisibility(ProgressBar.VISIBLE);

    }

    @Override
    protected String doInBackground(Void... voids) {
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
        return text.toString();
    }



    @Override
    protected void onPostExecute(String  str) {
        super.onPostExecute(str);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        loader.onLoadFinish(str);
    }
}
