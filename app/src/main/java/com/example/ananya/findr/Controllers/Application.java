package com.example.ananya.findr.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import FloatingActionButton.FloatingActionsMenu;
import FloatingActionButton.FloatingActionButton;
import Model.*;
import Model.Persistence.ManagementFacade;

import com.example.ananya.findr.R;

import java.io.File;

/**
 * Created by Ananya on 6/22/17.
 */

public class Application extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Model model = Model.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

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
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Persistence Menu
    ///////////////////////////////////////////////////////////////////////////////////////////
    /* Next two methods handle the menu options */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ManagementFacade mf = ManagementFacade.getInstance();
        File file;
        File file2;
        /**
         * I know switch statements are usually bad, but there in this case it is required
         * by the api design.
         *
         * We just use the item id to decide which menu item was selected.
         */
        switch(item.getItemId()) {
            case R.id.load_binary:
                //create a file object in the local files section
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_BINARY_FILE_NAME);
                //Log.d("MY APP", "Loading Binary Data");
                mf.loadBinary(file);
                //reset adapter to new data that has come in.
                //myAdapter.updateList(mf.getStudentsAsList());
                //Log.d("MY APP", "New Adaptor set");
                return true;
            case R.id.load_lost_items:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
                mf.loadText(file);
                //myAdapter.notifyDataSetChanged();
                return true;
            case R.id.load_found_items:
                file = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
                mf.loadFoundItemText(file);
                //myAdapter.notifyDataSetChanged();
                return true;
            case R.id.load_json:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_JSON_FILE_NAME);
                mf.loadJson(file);
                //myAdapter.updateList(mf.getStudentsAsList());
                return true;
            case R.id.save_binary:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_BINARY_FILE_NAME);
                return mf.saveBinary(file);
            case R.id.save_json:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_JSON_FILE_NAME);
                return mf.saveJson(file);
            case R.id.save_lost_items:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
                return mf.saveText(file);
            case R.id.save_found_items:
                file2 = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
                return mf.saveFoundItems(file2);
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    public void onBackPressed() {
    }
}
