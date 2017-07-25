package com.example.ananya.findr.controllers;

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
import android.widget.Toast;

import FloatingActionButton.FloatingActionsMenu;
import FloatingActionButton.FloatingActionButton;
import model.*;
import model.persistence.ManagementFacade;

import com.example.ananya.findr.R;

import java.io.File;

/**
 * Created by Ananya on 6/22/17.
 * The homepage v4.0ish
 * ////////////////////////////////////////////////
 * List of functions and menus:
 * ////////////////////////////////////////////////
 * 1. View Lost Items list
 * 2. View Found Items list
 * 3. View the map
 * 4. Generate sample data
 * 5. Logout
 * 6. Admin settings (only accessible to admins)
 * ------------------------------------------
 * 7. Floating action button menu
 * ------------------------------------------
 * 7a. Search
 * 7b. Add a Found Item
 * 7c. Add a Lost Item
 * --------------------------------------------
 * 8. Persistence Menu
 *----------------------------------------------
 * 8a. Save data as text
 * 8b. Load data from text
 */

public class Application extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        Model model = Model.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        ///////////////////////////////////////////////////////////////////////////
        //Buttons
        ////////////////////////////////////////////////////////////////////////////
        Button logout = (Button) findViewById(R.id.logout);
        Button lostItems = (Button) findViewById(R.id.lost_items);
        Button foundItems = (Button) findViewById(R.id.button_foundItemsList);
        Button map = (Button) findViewById(R.id.button_map);
        Button admin = (Button) findViewById(R.id.admin);
        Button generate = (Button) findViewById(R.id.generate);

        ////////////////////////////////////////////////////////////////////////////
        //Intents
        ////////////////////////////////////////////////////////////////////////////
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Login.class);
                startActivity(intent);
            }
        });

        if (!model.getCurrentUser().isAdmin()) {
            admin.setVisibility(View.GONE);
        }

        if (model.getCurrentUser().isAdmin()) {
            admin.setVisibility(View.VISIBLE);
        }

        admin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Application.this, Admin.class);
                startActivity(intent);
            }
        });

        lostItems.setOnClickListener(new OnClickListener() {
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

        //Generate Sample Data
        generate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementFacade mf = ManagementFacade.getInstance();
                Model model = Model.getInstance();
                String[] lostNameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
                        "The Lord of the Rings", "The Things They Carried",
                        "The Three Body Problem"};
                String[] foundNameList = new String[]{"1984", "Brave New World",
                        "Animal Farm", "Dune", "The Princess Bride"};

                User user1 = new User("user1", "Eren", "Jaegar", "aot", "user1@gatech.edu", "0123456789", "user");
                User user2 = new User("user2", "Mikasa", "Ackerman", "aot", "user2@gatech.edu", "0123456789", "admin");
                User user3 = new User("user3", "Levi", "Ackerman", "aot", "user3@gatech.edu", "0123456789", "admin");
                User user4 = new User("user4", "Reiner", "Braun", "aot", "user4@gatech.edu", "0123456789", "user");
                model.addUser(user1);
                model.addUser(user2);
                model.addUser(user3);
                model.addUser(user4);
                mf.addUser(user1);
                mf.addUser(user2);
                mf.addUser(user3);
                mf.addUser(user4);

                for (String aLostNameList : lostNameList) {
                    // Binds all strings into an array
                    model.addLostItem(new LostItem(aLostNameList, "book", "somewhere"));
                    model.setCurrentLostItem(new LostItem(aLostNameList, "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    mf.addLostItem(model.getCurrentLostItem());
                }

                for (String aFoundNameList : foundNameList) {
                    // Binds all strings into an array
                    model.addFoundItem(new FoundItem(aFoundNameList, "book", "somewhere"));
                    model.setCurrentFoundItem(new FoundItem(aFoundNameList, "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    mf.addFoundItem(model.getCurrentFoundItem());

                }

                Toast.makeText(Application.this, "Sample data successfully generated", Toast.LENGTH_SHORT).show();
            }
        });

        //////////////////////////////////////////////////////////////////////////////
        //Floating Action Button
        //////////////////////////////////////////////////////////////////////////////
        //Add Lost item button
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
        //Add found item button
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
        //Search button
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

        //Menu Object
        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(searchButton);
        menuMultipleActions.addButton(lostItem);
        menuMultipleActions.addButton(foundItem);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //Persistence Menu
    ///////////////////////////////////////////////////////////////////////////////////////////
    /* Next two methods handle the menu options */

    /**
     * creates menu
     *
     * @param menu the menu to be populated
     * @return true after completed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * item selected click
     *
     * @param item item to be selected
     * @return true when completed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ManagementFacade mf = ManagementFacade.getInstance();
        File file;
        File file2;
        File file3;

        switch (item.getItemId()) {
            //Loads the serializable Lost & Found Items and Users through 3 different text files
            case R.id.load_all_items:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
                file2 = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
                file3 = new File(this.getFilesDir(), ManagementFacade.USER_FILE_NAME);
                //"Other default" is found items
                mf.loadText(file, file2, file3);
                Toast.makeText(Application.this, "Data loaded from text", Toast.LENGTH_SHORT).show();
                return true;

            //Saves the serializable Lost & Found Items and Users through 3 different text files
            case R.id.save_all_items:
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
                file2 = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
                file3 = new File(this.getFilesDir(), ManagementFacade.USER_FILE_NAME);
                Toast.makeText(Application.this, "Data saved to text", Toast.LENGTH_SHORT).show();
                return mf.saveText(file, file2, file3);
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void onBackPressed() {
    }
}
