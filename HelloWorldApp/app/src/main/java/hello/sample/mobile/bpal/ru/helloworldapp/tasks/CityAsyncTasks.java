package hello.sample.mobile.bpal.ru.helloworldapp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;

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
import hello.sample.mobile.bpal.ru.helloworldapp.activity.AsynctTasksActivity;
import hello.sample.mobile.bpal.ru.helloworldapp.data.Contact;
import hello.sample.mobile.bpal.ru.helloworldapp.tasks.parser.ContactParser;

/**
 * Created by Topexpert on 11.05.2016.
 */
public class CityAsyncTasks extends AsyncTask<String, Integer, Integer> {

    private final String TAG = HelloWordApplication.TAG_PREFIX + this.getClass().getSimpleName();

    AsynctTasksActivity context;
    String resultString;

    public CityAsyncTasks(AsynctTasksActivity context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;

        URL url = null;
        try {
            url = new URL("http://bpal.ru:9090/cities");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resultString = readStream(in);
            int status = urlConnection.getResponseCode();

//            resultString = "" +
//                    "[" +
//                    "  {" +
//                    "    \"name\":\"Anjela\"," +
//                    "    \"lastName\": \"Teryuhova\"" +
//                    "  }," +
//                    "  {" +
//                    "    \"name\":\"Sergey\"," +
//                    "    \"lastName\": \"Danilov\"" +
//                    "  }" +
//                    "]" +
//                    "";

            publishProgress(50);

            Log.i(TAG, "get string = " + resultString);
            return status;
        } catch (MalformedURLException e) {
            Log.e("error: ", e.getMessage());
        } catch (IOException e) {
            Log.e("error: ", e.getMessage());
        }


        return null;
    }


    protected void onProgressUpdate(Integer... progress) {
        Log.i(TAG, "progress = " + progress);
//        setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Integer result) {
        Log.i(TAG, "result = " + result);
        if (result == HttpURLConnection.HTTP_OK) {
            context.textView.setText(resultString);

            try {

                JSONArray jsonArray = new JSONArray(resultString);

                String[] strings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    strings[i] = jsonArray.getString(i);
                }

                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strings);
                context.listView.setAdapter(itemsAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }


//            List<Contact> contacts = new ArrayList<>();
//            try {
//                JSONArray jsonArray = new JSONArray(resultString);
//                ContactParser contactParser = new ContactParser();
//                for (int i=0; i<jsonArray.length();i++) {
//                    contacts.add(contactParser.parseContcat(jsonArray.getJSONObject(i)));
//                }
//                Log.i(TAG, "list = " + contacts);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


        } else {
            context.textView.setText("ERROR " + result);
        }
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

}

