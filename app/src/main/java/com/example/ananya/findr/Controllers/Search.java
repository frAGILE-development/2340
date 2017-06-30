package com.example.ananya.findr.Controllers;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.ananya.findr.R;

import java.util.ArrayList;

import Model.LostItem;
import Model.FoundItem;
import Model.Model;

/**
 * Created by bwatson35 on 6/29/17.
 */

import android.widget.ListView;

public class Search extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListView list_found;
    ListAdapter adapter;
    FoundAdapter adapter2;
    android.widget.SearchView editsearch;
    ArrayList<LostItem> arraylist = new ArrayList<LostItem>();
    ArrayList<FoundItem> foundList = new ArrayList<FoundItem>();
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Generate sample data

//       String[] nameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
//                "The Lord of the Rings", "Never Let Me Go", "The Things They Carried",
//                "The Three Body Problem", "1984", "Brave New World",
//                "Animal Farm","Dune","The Princess Bride"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.list_view);
        list_found = (ListView) findViewById(R.id.list_view);
//        for (int i = 0; i < nameList.length; i++) {
//            // Binds all strings into an array
//            model.addLostItem(new LostItem(nameList[i], "book", "somewhere", model.getCurrentUser()));
//            model.setCurrentLostItem(new LostItem(nameList[i], "book", "somewhere", model.getCurrentUser()));
//            //arraylist.add(model.getCurrentLostItem());
//        }

        arraylist.addAll(model.getLostItems());
        foundList.addAll(model.getFoundItems());
        // Pass results to ListViewAdapter Class
        adapter = new ListAdapter(this, arraylist);
        adapter2 = new FoundAdapter(this, foundList);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list_found.setAdapter(adapter2);
        // Locate the EditText in listview_main.xml
        editsearch = (android.widget.SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
