package hello.sample.mobile.bpal.ru.helloworldapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hello.sample.mobile.bpal.ru.helloworldapp.HelloWordApplication;

/**
 * Created by Topexpert on 11.05.2016.
 */
public class CityAsyncTasks extends AsyncTask<String,Integer, Integer> {

    private final String TAG = HelloWordApplication.TAG_PREFIX + this.getClass().getSimpleName();

    @Override
    protected Integer doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;

        URL url = null;
        try {
            url = new URL("http://bpal.ru:9090/cities");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String str = readStream(in);
            int status = urlConnection.getResponseCode();

            Log.i(TAG, "get string = " + str);

            return status;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    protected void onProgressUpdate(Integer... progress) {
//        setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Integer result) {
//        showDialog("Downloaded " + result + " bytes");

    }

    String readStream(InputStream input) {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            if (input != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(input));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            Log.e(TAG, "doFilter: get data string error ",ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    Log.e(TAG, "doFilter: get data string error ",ex);
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

}

