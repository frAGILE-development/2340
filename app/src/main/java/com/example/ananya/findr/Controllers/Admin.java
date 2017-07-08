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
                String[] lostNameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
                        "The Lord of the Rings", "The Things They Carried",
                        "The Three Body Problem"};
                String[] foundNameList = new String[]{"1984", "Brave New World",
                        "Animal Farm","Dune","The Princess Bride"};

                // Locate the ListView in listview_main.xml
                list = (ListView) findViewById(R.id.list_view);
                list_found = (ListView) findViewById(R.id.list_view);

                for (int i = 0; i < lostNameList.length; i++) {
                    // Binds all strings into an array
                    model.addLostItem(new LostItem(lostNameList[i], "book", "somewhere", model.getCurrentUser()));
                    model.setCurrentLostItem(new LostItem(lostNameList[i], "book", "somewhere", model.getCurrentUser()));
                    //comment this out below along with its sister comment to turn on name generation
                    lostList.add(model.getCurrentLostItem());
                }

                for (int i = 0; i < foundNameList.length; i++) {
                    // Binds all strings into an array
                    model.addFoundItem(new FoundItem(foundNameList[i], "book", "somewhere", model.getCurrentUser()));
                    model.setCurrentFoundItem(new FoundItem(foundNameList[i], "book", "somewhere", model.getCurrentUser()));
                    //comment this out below along with its sister comment to turn on name generation
                    foundList.add(model.getCurrentFoundItem());
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
