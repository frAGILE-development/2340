package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import FloatingActionButton.FloatingActionsMenu;
import FloatingActionButton.FloatingActionButton;
import Model.Model;

import com.example.ananya.findr.R;
import com.example.ananya.findr.Controllers.Admin;

/**
 * Created by Ananya on 6/22/17.
 */

public class Application extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Model model = Model.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Button logout = (Button) findViewById(R.id.logout);
        Button lostitems = (Button) findViewById(R.id.lostitems);
        Button foundItems = (Button) findViewById(R.id.button_foundItemsList);
        Button map = (Button) findViewById(R.id.button_map);
        Button admin = (Button) findViewById(R.id.admin);

        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Login.class);
                startActivity(intent);
            }
        });

        if (!model.getCurrentUser().isAdmin()) {
            admin.setVisibility(admin.GONE);
        }

        admin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Admin.class);
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

        map.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton lostItem = new FloatingActionButton(getBaseContext());
        lostItem.setIcon(R.drawable.ic_action_lost_item);
        lostItem.setTitle("Add a lost item");
        lostItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.this, AddLostItem.class);
                startActivity(intent);

            }
        });

        FloatingActionButton foundItem = new FloatingActionButton(getBaseContext());
        foundItem.setIcon(R.drawable.ic_action_found_item);
        foundItem.setTitle("Add a found item");
        foundItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.this, AddFoundItem.class);
                startActivity(intent);

            }
        });

        FloatingActionButton searchButton = new FloatingActionButton(getBaseContext());
        searchButton.setIcon(R.drawable.ic_action_search);
        //searchButton.setColorNormal(R.color.white);
        searchButton.setColorPressed(R.color.white_pressed);
        searchButton.setTitle("Search");


        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Search.class);
                startActivity(intent);

            }
        });

        //menu holding the actions
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(searchButton);
        menuMultipleActions.addButton(lostItem);
        menuMultipleActions.addButton(foundItem);

    }



    @Override
    public void onBackPressed() {
    }
}
