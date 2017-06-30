package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.ananya.findr.R;

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
        Button addFoundItem = (Button) findViewById(R.id.button_addFoundItem);
        Button foundItems = (Button) findViewById(R.id.button_foundItemsList);
        Button search = (Button) findViewById(R.id.Search);

        addLostItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, AddLostItem.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Search.class);
                startActivity(intent);
            }
        });

        addFoundItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, AddFoundItem.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Homepage.class);
                startActivity(intent);
            }
        });

        lostitems.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, ViewLostItems.class);
                startActivity(intent);
            }
        });

        foundItems.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, ViewFoundItems.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
