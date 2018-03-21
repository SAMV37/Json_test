package com.ss.mob.apps.mary_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Question_screen extends Activity {

    public TextView question_text;
    public Button wrong;
    public Button right;
    public TextView score;

    public int score_num = 0;

    public String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        question_text = (TextView) findViewById(R.id.question_text);
        wrong = (Button) findViewById(R.id.wrong_button);
        right = (Button) findViewById(R.id.right_button);
        score = (TextView) findViewById(R.id.score);

        value = getIntent().getStringExtra("value");

        main_function(0);
    }
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public String question_returner(int number){
        String data = loadJSONFromAsset(this);

        try {
            JSONObject jsonObj = new JSONObject(data);

            JSONArray food = jsonObj.getJSONArray(value);
            JSONArray food_question = (JSONArray) food.getJSONArray(number);
            return (String) food_question.get(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String answer_returner(int number){
        String data = loadJSONFromAsset(this);

        try {
            JSONObject jsonObj = new JSONObject(data);

            JSONArray food = jsonObj.getJSONArray(value);
            JSONArray food_question = (JSONArray) food.getJSONArray(number);
            return (String) food_question.get(1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
    public String wrong_returner(int number){
        String data = loadJSONFromAsset(this);

        try {
            JSONObject jsonObj = new JSONObject(data);

            JSONArray food = jsonObj.getJSONArray(value);
            JSONArray food_question = (JSONArray) food.getJSONArray(number);
            return (String) food_question.get(3);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
    public String right_returner(int number){
        String data = loadJSONFromAsset(this);

        try {
            JSONObject jsonObj = new JSONObject(data);

            JSONArray food = jsonObj.getJSONArray(value);
            JSONArray food_question = (JSONArray) food.getJSONArray(number);
            return (String) food_question.get(2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void main_function(final int number){
        String data = loadJSONFromAsset(this);
        int length = 5;

        try {
            JSONObject jsonObj = new JSONObject(data);

            JSONArray food = jsonObj.getJSONArray(value);
            length = food.length();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(number != length) {
            question_text.setText(question_returner(number));
            wrong.setAlpha(1);
            right.setAlpha(1);
            wrong.setText(wrong_returner(number));
            right.setText(right_returner(number));
            wrong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    question_text.setText(answer_returner(number));
                    wrong.setAlpha(0);
                    right.setText("OK");
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            main_function(number + 1);
                        }
                    });
                }
            });

            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score_num++;
                    main_function(number + 1);
                }
            });
        }else{
            score.setAlpha(1);
            score.setText(Integer.toString(score_num));
            question_text.setText("The game is over!!");
            wrong.setAlpha(0);
            right.setText("OK");
            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }
    }
}
