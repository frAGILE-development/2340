package com.example.ananya.findr.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ananya.findr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Model.LostItem;
import Model.Model;
import Model.FoundItem;

public class FoundAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<FoundItem> namesList = null;
    private ArrayList<FoundItem> arraylist;
    /**
     *constructor
     * @param context context
     * @param namesList list of names
     */
    public FoundAdapter(Context context, List<FoundItem> namesList) {
        mContext = context;
        this.namesList = namesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FoundItem>();
        this.arraylist.addAll(namesList);
    }
    /**
     * inner class viewholder
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
     * @return item name
     */
    @Override
    public String getItem(int position) {
        return namesList.get(position).getName();
    }
    /**
     *getter for item id
     * @param position position of item
     * @return item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     *gets the correct view
     * @param position position of item
     * @param view view
     *@param parent view group
     * @return view
     */
    public View getView(final int position, View view, ViewGroup parent) {
        Model model = Model.getInstance();
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_found_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView textView=(TextView) view.findViewById(android.R.id.text1);
        // Set the results into TextViews
        holder.name.setText(namesList.get(position).getName());
        return view;
    }
    /**
     *filters text
     * @param charText character being typed in
     */
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        namesList.clear();
        if (charText.length() == 0) {
            namesList.addAll(arraylist);
        } else {
            for (FoundItem wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    namesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}