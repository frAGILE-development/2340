package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ananya.findr.R;
import com.example.ananya.findr.Controllers.AddLostItem;

/**
 * Created by Ananya on 6/22/17.
 */

public class Application extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Button logout = (Button) findViewById(R.id.logout);
        Button addLostItem = (Button) findViewById(R.id.AddLostItem);
        Button lostitems = (Button) findViewById(R.id.lostitems);

        addLostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, AddLostItem.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Homepage.class);
                startActivity(intent);
            }
        });

        lostitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, ViewItems.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
