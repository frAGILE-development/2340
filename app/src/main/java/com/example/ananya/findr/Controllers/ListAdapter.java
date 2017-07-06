package com.example.ananya.findr.Controllers;

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
import Model.Model;
import Model.LostItem;
import Model.Item;

public class ListAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Item> namesList = null;
    private ArrayList<Item> arraylist;

    public ListAdapter(Context context, List<Item> namesList) {
        mContext = context;
        this.namesList = namesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Item>();
        this.arraylist.addAll(namesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return namesList.size();
    }

    @Override
    public String getItem(int position) {
        return namesList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        Model model = Model.getInstance();
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