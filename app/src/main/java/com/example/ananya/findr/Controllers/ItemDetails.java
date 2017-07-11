package com.example.ananya.findr.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ananya.findr.R;
import Model.Model;
import Model.Item;


/**
 * Created by bwatson35 on 7/6/17.
 */

public class ItemDetails extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_item_details);
        super.onCreate(savedInstanceState);

        final Model model = Model.getInstance();
        Item item = model.getCurrentItem();
        Button back = (Button)findViewById(R.id.back);

        TextView name_text = (TextView) findViewById(R.id.text_view_name);
        TextView description_text = (TextView) findViewById(R.id.text_view_description);
        TextView address_text = (TextView) findViewById(R.id.text_view_address);

        name_text.setText(item.getName());
        description_text.setText(item.getDescription());
        address_text.setText(item.getAddress());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetails.this, Application.class);
                startActivity(intent);
            }
        });
    }
}
