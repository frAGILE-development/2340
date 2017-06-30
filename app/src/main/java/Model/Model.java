package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwatson35 on 6/25/17.
 * Based heavily on the model for Lab 3
 * <p>
 * Keeps track of a list of users at the moment
 */

public class Model {

    /**
     * Singleton instance
     */
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    /**
     * holds the list of all users
     */
    private List<User> _users;
    private List<LostItem> _items;
    /**
     * the currently selected user, defaults to the first one
     */
    private User _currentUser;
    //the currently selected lost item
    private LostItem _currentItem;

    //Null Users and items
    private final User theNullUser = new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", false, User.accountType.get(1));
    private final LostItem theNullItem = new LostItem("NULL", "NULL", "NULL", new User());

    /**
     * makes a new model
     */
    public Model() {
        _users = new ArrayList<>();
        _items = new ArrayList<>();
        //comment this out after full app developed
        //loadDummyData();
    }

    /**
     * get the users
     *
     * @return a list of the users in the app
     */
    public List<User> getUsers() {
        return _users;
    }

    /**
     * get the users
     *
     * @return a list of the users in the app
     */
    public List<LostItem> getLostItems() {
        return _items;
    }

    /**
     * gets a String array of current items in the model
     *
     * @return a list of the names of items in the model
     */
    public String[] getLostItemStrings() {
        String[] list = new String[_items.size()];

        for (int i = 0; i < _items.size(); i++) {
            list[i] = _items.get(i).getName();
        }

        return list;
    }

    /**
     * add a user to the app.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean addUser(User user) {
        for (User c : _users) {
            if (c.equals(user)) return false;
        }
        _users.add(user);
        return true;
    }

    /**
     * add a lost item to the app.  checks if the lost item is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param item the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean addItem(LostItem item) {
        for (LostItem c : _items) {
            if (c.equals(item)) return false;
        }
        _items.add(item);
        return true;
    }

    /**
     * @return the currently selected course
     */
    public User getCurrentUser() {
        return _currentUser;
    }

    /**
     * sets the current user
     *
     * @param user the current user in the model
     */
    public void setCurrentUser(User user) {
        _currentUser = user;
    }

    /**
     * gets the current lost item
     *
     * @return the current lost item
     */
    public LostItem getCurrentItem() {
        return _currentItem;
    }

    /**
     * sets the current item
     *
     * @param item the current item to be set
     */
    public void setCurrentItem(LostItem item) {
        _currentItem = item;
    }

    /**
     * Return a course that has matching number.
     * This uses an O(n) linear search.
     *
     * @param username the username of the user to find
     * @return the user with that username or the NullUser if no such user exists.
     */
    public User getUserByUsername(String username) {
        for (User c : _users) {
            if (c.getUsername().equals(username)) return c;
        }
        return theNullUser;
    }


}
