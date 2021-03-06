package com.example.ananya.findr.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.ananya.findr.R;
import model.Item;

class ListAdapter extends BaseAdapter {

    // Declare Variables

    private final LayoutInflater inflater;
    private List<Item> namesList = null;
    private final ArrayList<Item> arraylist;
    /**
     *constructor
     * @param context context
     * @param namesList item names list
     */
    public ListAdapter(Context context, List<Item> namesList) {
        this.namesList = namesList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(namesList);
    }
    /**
     *inner class
     */
    public class ViewHolder {
        TextView name;
    }
    /**
     *getter for count
     * @return count
     */
    @Override
    public int getCount() {
        return namesList.size();
    }
    /**
     *getter for item
     * @param position position of item
     * @return item
     */
    @Override
    public String getItem(int position) {
        return namesList.get(position).getName();
    }
    /**
     *getter item id
     * @param position position of item
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     *gets view
     * @param position position of item
     * @param view view
     * @param parent view group
     * @return view
     */
    @SuppressLint("InflateParams")
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(namesList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        namesList.clear();
        if (charText.length() == 0) {
            namesList.addAll(arraylist);
        } else {
            for (Item wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    namesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}