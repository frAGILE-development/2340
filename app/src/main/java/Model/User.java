package Model;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bwatson35 on 6/25/17.
 * Information Holder - represents a single user in model.
 * Is modeled in part on the student class from Lab 3.
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 */

public class User implements Parcelable{
        public static List<String> accountType = Arrays.asList("Admin", "Normal");
        //instance variables
        private int _id;
        private String _firstName;
        private String _lastName;
        private String _password;
        private String _email;
        private String _phoneNumber;
        private Boolean _admin;
        private String _username;
        private String _adminType;

        //derived instance vars
        private String _lastFirst;
        private String _fullName;

        /**
         * Constructor for the user class
         * @param firstName the user's first name
         * @param lastName the user's last name
         * @param password their password
         * @param email their email address
         * @param phoneNumber their phone number
         * @param admin whether or not they are an administrator
         */
        public User(String username, String firstName, String lastName, String password, String email,
                       String phoneNumber, Boolean admin, String type) {
            _username = username;
            _firstName = firstName;
            _lastName = lastName;
            _lastFirst = lastName + ", " + firstName;
            _fullName = firstName + " " + lastName;
            _password = password;
            _email = email;
            _phoneNumber = phoneNumber;
            _admin = admin;
            _adminType = type;
        }

        /**
         * No param constructor -- DO NOT CALL NORMALLY
         * This constructor only for GUI use in edit/new user dialog
         */
        public User() {
            this("GeorgePBurdell", "George" , "P. Burdell", "password", "george@gatech.edu", "0123456789", false, accountType.get(1));
        }

        /* **********************
         * Getters and setters
         */

        public String getUsername() { return _username; }
        public void setUsername(String s) { _username = s; }

        public String getFirstName() { return _firstName; }
        public void setFirstName(String s) { _firstName = s; }

        public String getLastName() { return _lastName; }
        public void setLastName(String s) { _lastName = s; }

        public String getLastFirst() { return _lastFirst; }

        public String getFullName() { return _fullName; }
        public void setFullName(String first, String last) {
            _firstName = first;
            _lastName = last;
        }


        public String getPassword() { return _password; }
        public void setPassword(String s) { _password = s; }


        public String getEmail() { return _email; }
        public void setEmail(String s) { _email = s; }

        public String getPhoneNumber() { return _phoneNumber; }
        public void setPhoneNumber(String s) { _phoneNumber = s; }

        public Boolean isAdmin() { return _admin; }
        public String getAdmin() {
            return _adminType;
        }

        public void makeAdmin() { _admin = true; }
        public void demoteToUser() { _admin = false; }
        public void set_adminType(String type) {
            _adminType = type;
        }
        
        public static int findPosition(String code) {
            int i = 0;
            while (i < accountType.size()) {
                if (code.equals(accountType.get(i))) {
                    return i;
                }
            }
            return 0;
        }


        /**
         *Prints the users full name, email, phone number, and whether they have admin access
         * @return the display string representation
         */
        @Override
        public String toString() {
            return "User: " + _fullName + " Email: " + _email + " Phone: " + _phoneNumber
                   + " Admin : " + _admin;
        }


    /* *********************************
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


        /* *************************
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


}
