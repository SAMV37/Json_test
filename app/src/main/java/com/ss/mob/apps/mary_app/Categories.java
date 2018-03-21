package com.ss.mob.apps.mary_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categories extends Activity {

    public Button nature;
    public Button history;
    public Button science;
    public Button art;
    public Button food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        nature = (Button) findViewById(R.id.nature);
        history = (Button) findViewById(R.id.history);
        science = (Button) findViewById(R.id.science);
        art = (Button) findViewById(R.id.art);
        food = (Button) findViewById(R.id.food);

        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Question_screen.class);
                myIntent.putExtra("value","nature");
                startActivity(myIntent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Question_screen.class);
                myIntent.putExtra("value","history");
                startActivity(myIntent);

            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Question_screen.class);
                myIntent.putExtra("value","science");
                startActivity(myIntent);

            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Question_screen.class);
                myIntent.putExtra("value","art");
                startActivity(myIntent);

            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Question_screen.class);
                myIntent.putExtra("value","food");
                startActivity(myIntent);

            }
        });
    }
}
