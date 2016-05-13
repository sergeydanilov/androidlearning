package hello.sample.mobile.bpal.ru.helloworldapp.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.HttpURLConnection;

import hello.sample.mobile.bpal.ru.helloworldapp.R;
import hello.sample.mobile.bpal.ru.helloworldapp.tasks.CitiesAsyncLoader;
import hello.sample.mobile.bpal.ru.helloworldapp.tasks.CitiesHttpResult;

public class AsyncLoaderDemoActivity extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter<String> mAdapter;

    LoaderHelper loaderHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityist_loader);

        mListView = (ListView) findViewById(R.id.loaderlistView);

        String[] strings = new String[0];
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        mListView.setAdapter(mAdapter);

        loaderHelper = new LoaderHelper();
//        getLoaderManager().initLoader(0, null, loaderHelper);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Bundle b = getIntent().getExtras();
        getLoaderManager().restartLoader(0, b, loaderHelper).forceLoad();
    }


    public class LoaderHelper implements LoaderManager.LoaderCallbacks<CitiesHttpResult> {

        @Override
        public Loader<CitiesHttpResult> onCreateLoader(int id, Bundle args) {
            return new CitiesAsyncLoader(AsyncLoaderDemoActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<CitiesHttpResult> loader, CitiesHttpResult data) {

            if (data != null) {

                if (data.status == HttpURLConnection.HTTP_OK) {
                    try {

                        JSONArray jsonArray = new JSONArray(data.result);

                        String[] strings = new String[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            strings[i] = jsonArray.getString(i);
                        }


                        mAdapter = new ArrayAdapter<String>(AsyncLoaderDemoActivity.this, android.R.layout.simple_list_item_1, strings);
                        mListView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<CitiesHttpResult> loader) {
            mAdapter.clear();
        }
    }
}
