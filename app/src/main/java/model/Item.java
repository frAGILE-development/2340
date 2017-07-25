package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.io.PrintWriter;

/**
 * Created by Bryce Watson on 6/29/17.
 *
 * The parent class of both the lost and found items
 */

public class Item implements Parcelable, Serializable {
    //instance variables
    private String _name;
    private String _description;
    private String _address;
    private User _owner;
    private LatLng _location = new LatLng(33.775078, -84.396401);//Default location is the culc

    /**
     * The constructor for the Lost Item class
     *
     * @param name        the name of the item
     * @param description the description of the lost item
     * @param address     the address of where the item was found
     * @param owner       the user under which the item was originally entered
     */
    public Item(String name, String description, String address, User owner) {
        _name = name;
        _description = description;
        _address = address;
        _owner = owner;
    }

    /**
     * The constructor for the Lost Item class
     *
     * @param name        the name of the item
     * @param description the description of the lost item
     * @param address     the address of where the item was found
     */
    public Item(String name, String description, String address) {
        _name = name;
        _description = description;
        _address = address;
    }

    /**
     * the Default constructor. For GUI use only
     */
    public Item() {
        this("Default Item", "No description", "221 Baker St");
    }

    /**
     * Gets the item's name
     * @return the name of the item
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the name of the item
     * @param s the string of the new name of the item
     */
    public void setName(String s) {
        _name = s;
    }

    /**
     * Gets the description of the item
     * @return the description of the lost item
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the description of the lost item
     * @param s the description to be set
     */
    public void setDescription(String s) {
        _description = s;
    }

    /**
     * Gets the address as a string of where the item was found
     * @return the address of the item
     */
    public String getAddress() {
        return _address;
    }

    /**
     * Sets a new address of where the lost item was originally found
     * @param s the new address
     */
    public void setAddress(String s) {
        _address = s;
    }

    /**
     * Gets the current user under which the lost item has been filed
     * @return the owner of the item
     */
    public User getOwner() {
        return _owner;
    }

    /**
     * Sets a new owner of the lost item. Use with caution
     * @param u the new owner of the lost item
     */
    public void setOwner(User u) {
        _owner = u;
    }

    /**
     * Prints the users full name, email, phone number, and whether they have admin access
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return "Item: " + _name + "\n" + "Description: " + _description + "\n" + "Address: " + _address;
    }

    /**
     * Sets the location of the item
     * @param loc the location
     */
    public void setLocation(LatLng loc) {
        _location = loc;
    }

    /**
     * returns the location of the item
     * @return the location
     */
    public LatLng getLocation() {
        return _location;
    }


    /**
     * gets the type
     * @return type of item
     */
    public String getType() {
        String _type;
        if (this.getClass().isInstance(FoundItem.class)) {
            _type = "Found Item";
        } else {
            _type = "Lost Item";
        }
        return _type;
    }

    /**********************************
     * These methods are required by the parcelable interface
     *
     */

    private Item(Parcel in) {
        _name = in.readString();
        _description = in.readString();
        _address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    /*************************
    /  If you add new instance vars to Student, you will need to add them to the write
    /*/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeString(_address);
        //dest.writeInt(_id);
    }
    /**
     * creates new parcelable items array
     * items in parcel
     * array of items
     */
    public static final Parcelable.Creator<Item> CREATOR
            = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    /**
     * Save this class in a custom save format
     * I chose to use tab (\t) to make line splitting easy for loading
     * If your data had tabs, you would need something else as a delimiter
     *
     * @param writer the file to write this student to
     */
    public void saveAsText(PrintWriter writer) {
        System.out.println("Saving item: " + _name);
        writer.println(_name + "\t" + _description + "\t" + _address);
    }

    /**
     * This is a static factory method that constructs a student given a text line in the correct format.
     * It assumes that a student is in a single string with each attribute separated by a tab character
     * The order of the data is assumed to be:
     *
     * 0 - name
     * 1 - user id
     * 2 - id code
     * 3 - email
     * 4 - password
     *
     * @param line  the text line containing the data
     * @return the student object
     */
    public static Item parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 3;
        return new LostItem(tokens[0], tokens[1], tokens[2]);
    }


}
