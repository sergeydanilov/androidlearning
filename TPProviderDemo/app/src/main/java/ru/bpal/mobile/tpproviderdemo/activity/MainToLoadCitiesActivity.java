package ru.bpal.mobile.tpproviderdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.bpal.mobile.tpproviderdemo.R;
import ru.bpal.mobile.tpproviderdemo.service.TPService;

public class MainToLoadCitiesActivity extends AppCompatActivity {

    Button loadBtn;
    Button runBtn;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_to_load_cities);
        ctx = this;

        loadBtn = (Button) findViewById(R.id.loadButton);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, TPService.class);
                intent.putExtra(TPService.METHOD_EXTRA, TPService.METHOD_GET);
                intent.putExtra(TPService.RESOURCE_TYPE_EXTRA, TPService.RESOURCE_TYPE_METAINFO);

                ctx.startService(intent);
            }
        });

        runBtn  = (Button) findViewById(R.id.runButton);
        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainToLoadCitiesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
