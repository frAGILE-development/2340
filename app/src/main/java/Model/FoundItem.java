package Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Created by bwatson35 on 6/29/17.
 */

public class FoundItem extends Item implements Parcelable, Serializable {

    //instance variables
    private String _name;
    private String _description;
    private String _address;
    private User _owner;
    private String type = "Found Item";

    /**
     * The constructor for the found Item class
     *
     * @param name        the name of the item
     * @param description the decription of the lost item
     * @param address     the address of where the item was found
     */
    public FoundItem(String name, String description, String address){
        _name = name;
        _description = description;
        _address = address;
    }

    /**
     * the Default contructor. For GUI use only
     */
    public FoundItem() {
        this("Default Found Item", "No description", "221 Baker St");
    }

    /**
     * Gets the item's name
     * @return the name of the item
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Sets the name of the item
     * @param s the string of the new name of the item
     */
    public void setName(String s) {
        super.setName(s);
    }

    /**
     * Gets the description of the item
     * @return the description of the lost item
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Sets the description of the lost item
     * @param s the description to be set
     */
    public void setDescription(String s) {
        super.setDescription(s);
    }

    /**
     * Gets the address as a string of where the item was found
     * @return the address of the item
     */
    public String getAddress() {
        return super.getAddress();
    }

    /**
     * Sets a new address of where the lost item was originally found
     * @param s the new address
     */
    public void setAddress(String s) {
        super.setAddress(s);
    }

    /**
     * Gets the current user under which the lost item has been filed
     * @return the owner of the item
     */
    public User getOwner() {
        return super.getOwner();
    }

    /**
     * Sets a new owner of the lost item. Use with caution
     * @param u the new owner of the lost item
     */
    public void setOwner(User u) {
        super.setOwner(u);
    }

    public String getType() {
        return type;
    }


    /**
     * Prints the users full name, email, phone number, and whether they have admin access
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return "Found Item: " + _name + "\n" + "Description: " + _description + "\n" + "Address: " + _address;
    }

    /* *********************************
     * These methods are required by the parcelable interface
     *
     */

    private FoundItem(Parcel in) {
        _name = in.readString();
        _description = in.readString();
        _address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    /* *************************
    /  If you add new instance vars to Student, you will need to add them to the write
    /*/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeString(_address);
        //dest.writeInt(_id);
    }

    public static final Parcelable.Creator<FoundItem> CREATOR
            = new Parcelable.Creator<FoundItem>() {
        public FoundItem createFromParcel(Parcel in) {
            return new FoundItem(in);
        }

        public FoundItem[] newArray(int size) {
            return new FoundItem[size];
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
        System.out.println("Saving Found Item: " + _name);
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
    public static FoundItem parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 3;
        FoundItem s = new FoundItem(tokens[0], tokens[1], tokens[2]);

        return s;
    }


}
