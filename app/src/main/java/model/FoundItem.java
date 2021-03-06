package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Created by Bryce Watson on 6/29/17.
 * Information holder for a lost item
 */

public class FoundItem extends Item implements Parcelable, Serializable {

    //instance variables
    private final String _name;
    private final String _description;
    private final String _address;
    private User _owner;

    /**
     * The constructor for the found Item class
     *
     * @param name        the name of the item
     * @param description the description of the lost item
     * @param address     the address of where the item was found
     */
    public FoundItem(String name, String description, String address){
        super(name, description, address);
        _name = name;
        _description = description;
        _address = address;
    }

    /**
     * the Default constructor. For GUI use only
     */
    public FoundItem() {
        this("Default Found Item", "No description", "221 Baker St");
    }


    public String getType() {
        return "Found Item";
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

    /*********************************
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
//    /**
//     * Save this class in a custom save format
//     * I chose to use tab (\t) to make line splitting easy for loading
//     * If your data had tabs, you would need something else as a delimiter
//     *
//     * @param writer the file to write this student to
//     */
//    public void saveAsBinary(PrintWriter writer) {
//        String item = (_name + "\t" + _description + "\t" + _address);
//        byte[] data = item.getBytes();
//        StringBuilder binary = new StringBuilder();
//        for (byte b : data) {
//            int value = b;
//            for (int i = 0; i < 8; i++) {
//                binary.append((value & 128) == 0 ? 0 : 1);
//                value <<= 1;
//            }
//            binary.append(' ');
//        }
//        System.out.println("Saving Lost Item: " + _name);
//        writer.println(binary);
//    }

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
        return new FoundItem(tokens[0], tokens[1], tokens[2]);

    }


}
