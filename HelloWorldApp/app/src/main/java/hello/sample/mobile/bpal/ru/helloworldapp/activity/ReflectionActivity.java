package hello.sample.mobile.bpal.ru.helloworldapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import hello.sample.mobile.bpal.ru.helloworldapp.R;
import hello.sample.mobile.bpal.ru.helloworldapp.reflection.ReflectionExample;

public class ReflectionActivity extends AppCompatActivity {


    Button mReflectionButton;
    TextView mReflectionTextView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        mReflectionButton = (Button) findViewById(R.id.reflectionButton);
        mReflectionTextView7 = (TextView) findViewById(R.id.reflectionTextView7);

        ReflectionExample reflectionExample = new ReflectionExample();
        mReflectionTextView7.setText(reflectionExample.printReflectionExample(null));
    }
}
