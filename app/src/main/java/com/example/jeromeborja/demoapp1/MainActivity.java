package com.example.jeromeborja.demoapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*Dynamically get JSON Data from the internet and use it in your android app*/
public class MainActivity extends AppCompatActivity {

    Button btnDemo;
    //public static in order to call in other activity
    public static TextView tvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDemo = findViewById(R.id.btn_Demo);
        tvDemo = findViewById(R.id.tvJson);

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call async task and execute it
                fetchData process = new fetchData();
                process.execute();
            }
        });
    }
}
