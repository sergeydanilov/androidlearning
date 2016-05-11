package hello.sample.mobile.bpal.ru.helloworldapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hello.sample.mobile.bpal.ru.helloworldapp.R;
import hello.sample.mobile.bpal.ru.helloworldapp.tasks.CityAsyncTasks;

public class AsynctTasksActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynct_tasks);

        Button button= (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityAsyncTasks cityAsyncTasks= new CityAsyncTasks();
                cityAsyncTasks.execute("");
            }
        });
    }
}
