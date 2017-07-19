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
import android.widget.ListView;
import android.widget.Toast;

import FloatingActionButton.FloatingActionsMenu;
import FloatingActionButton.FloatingActionButton;
import Model.*;
import Model.Persistence.ManagementFacade;

import com.example.ananya.findr.R;

import java.io.File;
import java.util.ArrayList;

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
        ///////////////////////////////////////////////////////////////////////////
        //Buttons
        ////////////////////////////////////////////////////////////////////////////
        Button logout = (Button) findViewById(R.id.logout);
        Button lostitems = (Button) findViewById(R.id.lostitems);
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

        //Generate Sample Data
        generate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<LostItem> lostList = new ArrayList<LostItem>();
                ArrayList<FoundItem> foundList = new ArrayList<FoundItem>();
                ManagementFacade mf = ManagementFacade.getInstance();
                Model model = Model.getInstance();
                String[] lostNameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
                        "The Lord of the Rings", "The Things They Carried",
                        "The Three Body Problem"};
                String[] foundNameList = new String[]{"1984", "Brave New World",
                        "Animal Farm","Dune","The Princess Bride"};

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

                for (int i = 0; i < lostNameList.length; i++) {
                    // Binds all strings into an array
                    model.addLostItem(new LostItem(lostNameList[i], "book", "somewhere"));
                    model.setCurrentLostItem(new LostItem(lostNameList[i], "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    lostList.add(model.getCurrentLostItem());
                    mf.addLostItem(model.getCurrentLostItem());
                }

                for (int i = 0; i < foundNameList.length; i++) {
                    // Binds all strings into an array
                    model.addFoundItem(new FoundItem(foundNameList[i], "book", "somewhere"));
                    model.setCurrentFoundItem(new FoundItem(foundNameList[i], "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    foundList.add(model.getCurrentFoundItem());
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
     *creates menu
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
     *item selected click
     * @param item item to be selected
     * @return true when completed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ManagementFacade mf = ManagementFacade.getInstance();
        File file;
        File file2;
        File file3;
//         I know switch statements are usually bad, but there in this case it is required
//         by the api design,
//         We just use the item id to decide which menu item was selected.
        switch(item.getItemId()) {

//            case R.id.load_binary:
//                //create a file object in the local files section
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_BINARY_FILE_NAME);
//                //Log.d("MY APP", "Loading Binary Data");
//                mf.loadBinary(file);
//                //reset adapter to new data that has come in.
//                //myAdapter.updateList(mf.getStudentsAsList());
//                //Log.d("MY APP", "New Adaptor set");
//                return true;
            case R.id.load_all_items://Currently load all items
                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
                file2 = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
                file3 = new File(this.getFilesDir(), ManagementFacade.USER_FILE_NAME);
                //"Other default" is found items
                mf.loadText(file, file2, file3);
                Toast.makeText(Application.this, "Data loaded from text", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.load_lost_items:
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
//                mf.loadText(file);
//                //myAdapter.notifyDataSetChanged();
//                return true;
//            case R.id.load_found_items:
//                file = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
//                mf.loadFoundItemText(file);
//                //myAdapter.notifyDataSetChanged();
//                return true;
//            case R.id.load_json:
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_JSON_FILE_NAME);
//                mf.loadJson(file);
//                //myAdapter.updateList(mf.getStudentsAsList());
//                return true;
//            case R.id.save_binary:
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_BINARY_FILE_NAME);
//                return mf.saveBinary(file);
//            case R.id.save_json:
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_JSON_FILE_NAME);
//                return mf.saveJson(file);
//            case R.id.save_lost_items:
//                file = new File(this.getFilesDir(), ManagementFacade.DEFAULT_TEXT_FILE_NAME);
//                return mf.saveText(file);
//            case R.id.save_found_items:
//                file2 = new File(this.getFilesDir(), ManagementFacade.OTHER_DEFAULT_TEXT_FILE_NAME);
//                return mf.saveFoundItems(file2);
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
