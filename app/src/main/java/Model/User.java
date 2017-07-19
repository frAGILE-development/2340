package Model;

import android.os.Parcelable;
import android.os.Parcel;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bwatson35 on 6/25/17.
 * Information Holder - represents a single user in model.
 * Is modeled in part on the student class from Lab 3.
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 */

public class User implements Parcelable , Serializable {
    public static final List<String> accountType = Arrays.asList("Admin", "Normal");
    //instance variables
    private int _id;
    private String _firstName;
    private String _lastName;
    private String _password;
    private String _email;
    private String _phoneNumber;
    private Boolean _admin = false;
    private String _username;
    private String _adminType;

    //derived instance vars
    private String _lastFirst;
    private String _fullName;
    private Boolean _lockoutStatus = false;
    /**
     * Constructor for the user class
     *
     * @param firstName   the user's first name
     * @param lastName    the user's last name
     * @param password    their password
     * @param email       their email address
     * @param phoneNumber their phone number
     * @param type        admin or not
     */
    public User(String username, String firstName, String lastName, String password, String email,
                String phoneNumber, String type) {
        _username = username;
        _firstName = firstName;
        _lastName = lastName;
        _lastFirst = lastName + ", " + firstName;
        _fullName = firstName + " " + lastName;
        _password = password;
        _email = email;
        _phoneNumber = phoneNumber;
        _adminType = type;
        if(type.equalsIgnoreCase("admin")) {
            _admin = true;
        }

    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new user dialog
     */
    public User() {
        this("GeorgePBurdell", "George", "P. Burdell", "password", "george@gatech.edu", "0123456789", accountType.get(1));
    }

    /**
     * Two param constructor -- only for login record keeping
     * This constructor only for GUI use in edit/new user dialog
     */
    public User(String username, String password) {
        this(username, "George", "P. Burdell", password , "george@gatech.edu", "0123456789", accountType.get(1));
    }



    /**
     * get's the user's usernmae
     *
     * @return the user's username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * sets the username
     *
     * @param s the new username
     */
    public void setUsername(String s) {
        _username = s;
    }

    /**
     * @return the user's first name
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     *
     * @param s the first name to be set
     */
    public void setFirstName(String s) {
        _firstName = s;
    }

    /**
     *
     * @return the user's last name
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     *
     * @param s the last name to be set
     */
    public void setLastName(String s) {
        _lastName = s;
    }

    /**
     * returns a string formatted [Last Name], [First Name]
     * @return the user's first and last name
     */
    public String getLastFirst() {
        return _lastFirst;
    }

    /**
     * returns a string formatted [First Name] [Last Name]
     * @return _fullname
     */
    public String getFullName() {
        return _fullName;
    }

    /**
     * Sets the first and last name of the user
     * @param first the first name
     * @param last the last name
     */
    public void setFullName(String first, String last) {
        _firstName = first;
        _lastName = last;
        _fullName = "first " + "last";
        _lastFirst = "last, " + "first";
    }

    /**
     * gets the user's password. Use with caution
     * @return the user's password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Sets the user's password. Use with caution
     * @param s the password to be set
     */
    public void setPassword(String s) {
        _password = s;
    }

    /**
     * Gets the user's email address
     * @return the user's email address as a String
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Sets the user's email address
     * @param s the user's email address as a String
     */
    public void setEmail(String s) {
        _email = s;
    }

    /**
     * Gets the user's Phone number
     * @return user's phone number as a String of only digits
     */
    public String getPhoneNumber() {
        return _phoneNumber;
    }

    /**
     * Sets the user's phone number
     * @param s a string of only digits
     */
    public void setPhoneNumber(String s) {
        _phoneNumber = s;
    }

    /**
     * Returns a true/false whether the user is an Admin
     * @return the user's admin status as a Boolean
     */
    public Boolean isAdmin() {
        return _admin;
    }

    /**
     * Gets the user's admin status
     * @return the user's privilege level
     */
    public String getAdmin() {
        return _adminType;
    }

    /**
     * Makes the current user an admin
     */
    public void makeAdmin() {
        _admin = true;
    }

    /**
     * Makes the current admin a user
     */
    public void demoteToUser() {
        _admin = false;
    }

    /**
     * Sets the privelege level of the user
     * @param type admin or user
     */
    public void set_adminType(String type) {
        _adminType = type;
    }

// --Commented out by Inspection START (7/18/2017 9:40 PM):
//    /**
//     * finds what account type an account is
//     * @param code search for account type
//     * @return number of position
//     */
//    public static int findPosition(String code) {
//        int i = 0;
//        while (i < accountType.size()) {
//            if (code.equals(accountType.get(i))) {
//                return i;
//            }
//        }
//        return 0;
//    }
// --Commented out by Inspection STOP (7/18/2017 9:40 PM)

    /**
     * returns whether the user is banned or not
     * @return
     */
    public Boolean getLockoutStatus() {
        return _lockoutStatus;
    }

    /**
     * prevents the user form logging in
     */
    public void ban() {
        _lockoutStatus = true;
    }

    /**
     * allows the user to login again
     */
    public void unban() {
        _lockoutStatus = false;
    }


    /**
     * Prints the users full name, email, phone number, and whether they have admin access
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return "User: " + _fullName + "\nEmail: " + _email + "\nPhone: " + _phoneNumber
                + "\nAdmin : " + _admin;
    }


    /**********************************
     * These methods are required by the parcelable interface
     *
     */

    private User(Parcel in) {
        _firstName = in.readString();
        _lastName = in.readString();
        _password = in.readString();
        _email = in.readString();
        _phoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    /**************************
    /  If you add new instance vars to Student, you will need to add them to the write
    /*/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_firstName);
        dest.writeString(_lastFirst);
        dest.writeString(_password);
        dest.writeString(_email);
        dest.writeString(_phoneNumber);

        //dest.writeInt(_id);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
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
        System.out.println("Saving User: " + _fullName);
        writer.println(_username + "\t" +_firstName + "\t" + _lastName + "\t" + _password + "\t" + _email
        + "\t" + _phoneNumber + "\t" + _adminType);
    }

//    /**
//     * Save this class in a custom save format
//     * I chose to use tab (\t) to make line splitting easy for loading
//     * If your data had tabs, you would need something else as a delimiter
//     *
//     * @param writer the file to write this student to
//     */
//    public void saveAsBinary(PrintWriter writer) {
//        String item = (_username + "\t" +_firstName + "\t" + _lastName + "\t" + _password + "\t" + _email
//                + "\t" + _phoneNumber + "\t" + _adminType);
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
//        System.out.println("Saving Lost Item: " + _fullName);
//        writer.println(binary);
//    }
    /**
     * Constructor for the user class
     *
     * @param firstName   the user's first name
     * @param lastName    the user's last name
     * @param password    their password
     * @param email       their email address
     * @param phoneNumber their phone number
     * @param type
     */

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
    public static User parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 7;
        return new User(tokens[0], tokens[1], tokens[2], tokens[3],tokens[4], tokens[5], tokens[6]);

    }


}
