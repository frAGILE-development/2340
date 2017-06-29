package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bryce on 6/29/2017.
 */

public class LostItem implements Parcelable {
    private String _name;
    private String _description;
    private String _address;
    private User _owner;

    public LostItem(String name, String description, String address, User owner) {
        _name = name;
        _description = description;
        _address = address;
        _owner = owner;
    }

    //default constructor
    public LostItem() {
        this("Deafault Item", "No description", "221 Baker St", new User());
    }

    public String getName() { return _name; }
    public void setName(String s) { _name = s; }

    public String getDescription() { return _description; }
    public void setDescription(String s) { _description = s; }

    public String getAddress() { return _address; }
    public void setAddress(String s) { _address = s; }

    public User getOwner() { return _owner; }
    public void setOwner(User u) { _owner = u; }

    /**
     *Prints the users full name, email, phone number, and whether they have admin access
     * @return the display string representation
     */
    @Override
    public String toString() {
        return "Lost Item: " + _name + " Description: " + _description + " Address: " + _address;
    }

    /* *********************************
     * These methods are required by the parcelable interface
     *
     */

    private LostItem(Parcel in) {
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

    public static final Parcelable.Creator<LostItem> CREATOR
            = new Parcelable.Creator<LostItem>() {
        public LostItem createFromParcel(Parcel in) {
            return new LostItem(in);
        }

        public LostItem[] newArray(int size) {
            return new LostItem[size];
        }
    };
}
