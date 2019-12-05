package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;

    Button b1, b2, b3;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.editText_name);
        ed2 = (EditText) findViewById(R.id.editText_email);
        ed3 = (EditText) findViewById(R.id.editText_pass);

        b1 = (Button) findViewById(R.id.button_save);
        b2 = (Button) findViewById(R.id.button_retrieve);
        b3 = (Button) findViewById(R.id.button_clear);

        pref = getSharedPreferences("user_creds", Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String pw = ed3.getText().toString();

                SharedPreferences.Editor editor = pref.edit();

                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("password", pw);
                editor.commit();
                Toast.makeText(MainActivity.this, "Save Successful!", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String creds = pref.getString("user_creds", "");
                if (creds != null) {
                    String name = pref.getString("name", "");
                    String email = pref.getString("email", "");
                    String pw = pref.getString("password", "");
                    ed1.setText(name);
                    ed2.setText(email);
                    ed3.setText(pw);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
            }
        });
    }
}
