package com.ss.mob.apps.mary_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    public EditText name_field;
    public Button submit_button;

    public String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String restored_text = prefs.getString("name", null);
        if(restored_text == null) {
            name_field = (EditText) findViewById(R.id.name_field);
            submit_button = (Button) findViewById(R.id.submit_button);

            submit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user_name = String.valueOf(name_field.getText());
                    startActivity(new Intent(getApplicationContext(), Categories.class));
                    SharedPreferences.Editor edit = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                    edit.putString("name", user_name);
                    edit.apply();
                }
            });
        }else{
            startActivity(new Intent(getApplicationContext(), Categories.class));
        }
    }


}
