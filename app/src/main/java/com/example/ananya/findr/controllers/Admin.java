package com.example.ananya.findr.controllers;

import android.support.v7.app.AppCompatActivity;

import com.example.ananya.findr.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import model.persistence.ManagementFacade;
import model.User;
import model.Model;
import model.FoundItem;
import model.LostItem;
/**

/**
 * Created by Bryce Watson on 7/7/17.
 * Controller for the admin page, which has the following functions:
 * 1. Generate sample content using preset Strings
 * 2. Erase all Found & Lost Items and Users from the backend
 * 3. Access the user management system, where you can block and unblock individual users
 * 4. A Back button
 * This menu is not possible to access unless the user's privilege level is Admin
 */

public class Admin extends AppCompatActivity {
    //instance variables for data generation
    private final Model model = Model.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //get button ids
        Button generate = (Button) findViewById(R.id.generate);
        Button back = (Button) findViewById(R.id.back);
        Button viewUsers = (Button) findViewById(R.id.viewUsers);
        Button delete = (Button) findViewById(R.id.button_delete);

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
                mf.addUser(user1);
                mf.addUser(user2);
                mf.addUser(user3);
                mf.addUser(user4);

                // Locate the ListView in listview_main.xml
//                list = (ListView) findViewById(R.id.list_view);
//                list_found = (ListView) findViewById(R.id.list_view);

                for (String aLostNameList : lostNameList) {
                    // Binds all strings into an array

                    model.addLostItem(new LostItem(aLostNameList, "book", "somewhere"));
                    model.setCurrentLostItem(new LostItem(aLostNameList, "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    LostItem item = model.getCurrentLostItem();
                    mf.addLostItem(item);

                }

                for (String aFoundNameList : foundNameList) {
                    // Binds all strings into an array
                    model.addFoundItem(new FoundItem(aFoundNameList, "book", "somewhere"));
                    model.setCurrentFoundItem(new FoundItem(aFoundNameList, "book", "somewhere"));
                    //comment this out below along with its sister comment to turn on name generation
                    FoundItem item = model.getCurrentFoundItem();
                    mf.addFoundItem(item);

                }

                Toast.makeText(Admin.this, "Sample data successfully generated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.nuclearMeltdown();
                Toast.makeText(Admin.this, "Data erased", Toast.LENGTH_SHORT).show();
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
