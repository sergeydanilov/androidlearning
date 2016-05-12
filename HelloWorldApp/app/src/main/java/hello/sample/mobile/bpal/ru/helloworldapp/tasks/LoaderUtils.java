package hello.sample.mobile.bpal.ru.helloworldapp.tasks;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hello.sample.mobile.bpal.ru.helloworldapp.HelloWordApplication;

/**
 * Created by serg on 13.05.16.
 */
public final class LoaderUtils {

    private static final String TAG = HelloWordApplication.TAG_PREFIX + "LoaderUtils";

    public static String readStream(InputStream input) {

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
            Log.e(TAG, "doFilter: get data string error ", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    Log.e(TAG, "doFilter: get data string error ", ex);
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }


    private LoaderUtils() {
    }
}
