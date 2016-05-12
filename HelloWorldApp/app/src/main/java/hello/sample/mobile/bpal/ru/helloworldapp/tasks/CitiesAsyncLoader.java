package hello.sample.mobile.bpal.ru.helloworldapp.tasks;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import hello.sample.mobile.bpal.ru.helloworldapp.HelloWordApplication;

/**
 *
 */
public class CitiesAsyncLoader extends AsyncTaskLoader<CitiesHttpResult> {

    private final String TAG = HelloWordApplication.TAG_PREFIX + this.getClass().getSimpleName();

    private String resultString;

    public CitiesAsyncLoader(Context context) {
        super(context);
    }

    @Override
    public CitiesHttpResult loadInBackground() {
        HttpURLConnection urlConnection = null;
        URL url = null;
        CitiesHttpResult httpResult = new CitiesHttpResult();
        try {
            url = new URL("http://bpal.ru:9090/cities");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resultString = LoaderUtils.readStream(in);
            int status = urlConnection.getResponseCode();

            Log.i(TAG, "get string = " + resultString);

            httpResult.status = status;
            httpResult.result = resultString;

        } catch (MalformedURLException e) {
            Log.e("error: ", e.getMessage());
        } catch (IOException e) {
            Log.e("error: ", e.getMessage());
        }
        return httpResult;
    }
}
