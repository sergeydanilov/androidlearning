package ru.bpal.mobile.tpproviderdemo.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cities.sample.mobile.bpal.ru.tpproviderdemo.R;


/**
 *
 */
public class MetaInfoProcessor {

    protected static final String TAG = MetaInfoProcessor.class.getSimpleName();

    private Context mContext;


    public MetaInfoProcessor(Context mContext) {
        this.mContext = mContext;
    }

    public void getMetaInfo(TPProcessorCallback callback) {

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("http://192.168.0.3:9090/cities");
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String str = readStream(in);
            System.out.println(str);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }




//
//        if (callback != null) {
//            callback.send(0);
//        }
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
