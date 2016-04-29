package hello.sample.mobile.bpal.ru.helloworldapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hello.sample.mobile.bpal.ru.helloworldapp.R;
import hello.sample.mobile.bpal.ru.helloworldapp.data.Person;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Person person = (Person) getIntent().getExtras().getParcelable("person");

        TextView textView2= (TextView) findViewById(R.id.textView2);
        TextView textView3= (TextView) findViewById(R.id.textView3);
        TextView textView4= (TextView) findViewById(R.id.textView4);
        textView2.setText(person.getId());
        textView3.setText(person.getName());
        textView4.setText(person.getGrade());

    }
}
