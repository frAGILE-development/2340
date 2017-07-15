package com.example.ananya.findr.Controllers;

import android.support.v7.app.AppCompatActivity;

import com.example.ananya.findr.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Persistence.ManagementFacade;
import Model.User;
import Model.Model;
import Model.FoundItem;
import Model.LostItem;
import Model.Item;
/**

/**
 * Created by bwatson35 on 7/7/17.
 */

public class Admin extends AppCompatActivity {
    //instance variables for data generation
    ListView list;
    ListView list_found;
    ListAdapter adapter;
    FoundAdapter adapter2;
    android.widget.SearchView editsearch;
    ArrayList<LostItem> lostList = new ArrayList<LostItem>();
    ArrayList<FoundItem> foundList = new ArrayList<FoundItem>();
    ArrayList<Item> totalList = new ArrayList<Item>();
    Model model = Model.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //get button ids
        Button generate = (Button) findViewById(R.id.generate);
        Button back = (Button) findViewById(R.id.back);
        Button viewUsers = (Button) findViewById(R.id.viewUsers);


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagementFacade mf = ManagementFacade.getInstance();

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

                // Locate the ListView in listview_main.xml
                list = (ListView) findViewById(R.id.list_view);
                list_found = (ListView) findViewById(R.id.list_view);

                for (int i = 0; i < lostNameList.length; i++) {
                    // Binds all strings into an array

                    model.addLostItem(new LostItem(lostNameList[i], "book", "somewhere"));
                    model.setCurrentLostItem(new LostItem(lostNameList[i], "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    lostList.add(model.getCurrentLostItem());
                    LostItem item = model.getCurrentLostItem();
                    mf.addLostItem(item);

                }

                for (int i = 0; i < foundNameList.length; i++) {
                    // Binds all strings into an array
                    model.addFoundItem(new FoundItem(foundNameList[i], "book", "somewhere"));
                    model.setCurrentFoundItem(new FoundItem(foundNameList[i], "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    foundList.add(model.getCurrentFoundItem());
                    FoundItem item = model.getCurrentFoundItem();
                    mf.addFoundItem(item);

                }

                Toast.makeText(Admin.this, "Sample data successfully generated", Toast.LENGTH_SHORT).show();
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, ViewUsers.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Admin.this, Application.class);
                    startActivity(intent);

            }
        });
    }

}
