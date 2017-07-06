package Model;

import java.util.ArrayList;
import java.util.List;
import Model.FoundItem;
/**
 * Created by bwatson35 on 6/25/17.
 * Based heavily on the model for Lab 3
 * Keeps track of a list of users at the moment
 */

public class Model {

    /**
     * Singleton instance
     */
    private static final Model _instance = new Model();

    /**
     * Returns a singleton instance of the model
     * @return an instance of the model
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * holds the list of all users, lost & found items
     */
    private List<User> _users;
    private List<LostItem> _lostItems;
    private List<FoundItem> _foundItems;
    /**
     * the currently selected user, defaults to the first one
     */
    private User _currentUser;
    //the currently selected lost item
    private LostItem _currentLostItem;
    private FoundItem _currentFoundItem;

    //Null Users and items
    private final User theNullUser = new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", false, User.accountType.get(1));
    private final LostItem theNullLostItem = new LostItem("NULL", "NULL", "NULL", new User());
    private final FoundItem theNullFoundItem = new FoundItem("NULL", "NULL", "NULL", new User());
    /**
     * makes a new model
     */
    public Model() {
        _users = new ArrayList<>();
        _lostItems = new ArrayList<>();
        _foundItems = new ArrayList<>();
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
     * get the lost items
     *
     * @return a list of the lost items for that user
     */
    public List<LostItem> getLostItems() {
        return _lostItems;
    }

    /**
     * get the found items
     *
     * @return a list of the found items for that user
     */
    public List<FoundItem> getFoundItems() {
        return _foundItems;
    }

    /**
     * gets a String array of current items in the model
     *
     * @return a list of the names of items in the model
     */
    public String[] getLostItemStrings() {
        String[] list = new String[_lostItems.size()];

        for (int i = 0; i < _lostItems.size(); i++) {
            list[i] = _lostItems.get(i).getName();
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
    public boolean addLostItem(LostItem item) {
        for (LostItem c : _lostItems) {
            if (c.equals(item)) return false;
        }
        _lostItems.add(item);
        return true;
    }

    /**
     * add a found item to the app.  checks if the found item is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param item the found item to be added
     * @return true if added, false if a duplicate
     */
    public boolean addFoundItem(FoundItem item) {
        for (FoundItem c : _foundItems) {
            if (c.equals(item)) return false;
        }
        _foundItems.add(item);
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
    public LostItem getCurrentLostItem() {
        return _currentLostItem;
    }

    /**
     * sets the current lost item
     *
     * @param item the current item to be set
     */
    public void setCurrentLostItem(LostItem item) {
        _currentLostItem = item;
    }

    /**
     * gets the current found item
     *
     * @return the current found item
     */
    public FoundItem getCurrentFoundItem() {
        return _currentFoundItem;
    }

    /**
     * sets the current found item
     *
     * @param item the current found item to be set
     */
    public void setCurrentFoundItem(FoundItem item) {
        _currentFoundItem = item;
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

    /**
     *Gets both lost and found items in one list
     * @return a combined list of both items
     */
    public ArrayList getAllItems() {
        ArrayList list = new ArrayList<Item>();
        list.addAll(_lostItems);
        list.addAll(_foundItems);
        return list;
    }




}
