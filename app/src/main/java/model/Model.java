package model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bryce Watson on 6/25/17.
 * This model is kind of also a item and user management system.
 * It is a singleton with getters and setters for current lost, found, & generic items & users
 * It can also add and remove items, as well as find items and users by name with O(n) time
 * It is ultimately responsible for handling user lockouts if they are banned or fail 3 password
 * attempts. If an admin decides to erase all data from the backend, that button press is a direct
 * call to the function nuclearMeltdown() and will purge the system.
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
    private ArrayList<User> _users;
    private ArrayList<LostItem> _lostItems;
    private ArrayList<FoundItem> _foundItems;
    private ArrayList<User> _bannedUsers;
    private ArrayList<Marker> _markers;
    private HashMap<Item, Marker> _hash;
    /**
     * the currently selected user, defaults to the first one
     */
    private User _currentUser;
    //the currently selected lost item
    private LostItem _currentLostItem;
    private FoundItem _currentFoundItem;
    private Item _currentItem;
    private Marker _currentMarker;

    //Null Users and items
    private final User theNullUser = new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", User.accountType.get(1));
    private final LostItem theNullLostItem = new LostItem("NULL", "NULL", "NULL");
    private final FoundItem theNullFoundItem = new FoundItem("NULL", "NULL", "NULL");

    private LatLng currentLocation;
    /**
     * makes a new model
     */
    private Model() {
        _users = new ArrayList<>();
        _lostItems = new ArrayList<>();
        _foundItems = new ArrayList<>();
        _bannedUsers = new ArrayList<>();
        _markers = new ArrayList<>();
    }

    /**
     * Sets the current location
     * @param loc the location to set
     */
    public void setCurrentLocation(LatLng loc) {
        currentLocation = loc;
    }

    /**
     * Gets the current location
     * @return currentLocation
     */
    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Gets the current marker
     * @return currentLocation
     */
    public Marker getCurrentMarker() {
        return _currentMarker;
    }


    /**
     * Sets the current marker
     * @param mk the marker to be set
     */
    public void setCurrentMarker(Marker mk) {
        _currentMarker = mk;
    }

    /**
     * Gets all the markers
     * @return _markers
     */
    public ArrayList<Marker> getMarkers() {
        return _markers;
    }

    /**
     * Gets all the markers
     * @@param item the item to add
     * @param marker the marker to add
     */
    public void addMarkerToHash(Item item, Marker marker) {
        _hash.put(item, marker);
    }

    /**
     * Gets all the markers
     * @return _markers
     */
    public Marker getMarkerFromHash(Item item) {
        if (item != null) {
            return _hash.get(item);
        }
        return null;
    }

    /**
     * add a marker to the app.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param mk the marker to be added
     */
    public void addMarker(Marker mk) {
        for (Marker m : _markers) {
            if (m.equals(mk)) return;
        }
        _markers.add(mk);
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
     * returns a list of users whose accounts have been locked
     * @return a list of banned users
     */
    public List<User> getBannedUsers() {
        return _bannedUsers;
    }

    /**
     * add a banned user to the app.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be added
     */
    public void addBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)) return;
        }
        _bannedUsers.add(user);
    }


    /**
     * removes a banned user.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be removed
     */
    public void removeBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)){
                _bannedUsers.remove(c);
                return;
            }
        }
    }

    /**
     * returns true if the user is banned, false otherwise
     * @param user the user to check its lockout status
     * @return true if user banned else false
     */
    public boolean isBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)) return true;
        }
        return false;
    }
    /**
     * getter for null user
     * @return null user
     */
    public User getNullUser() {
        return theNullUser;
    }
    /**
     * getter for null found item
     * @return null found item
     */
    public FoundItem getNullFoundItem() {
        return theNullFoundItem;
    }
    /**
     * getter for null lost item
     * @return null lost item
     */
    public LostItem getNullLostItem() {
        return theNullLostItem;
    }

    /**
     * get the lost items
     *
     * @return a list of the lost items for that user
     */
    public ArrayList<LostItem> getLostItems() {
        return _lostItems;
    }

    /**
     * get the found items
     *
     * @return a list of the found items for that user
     */
    public ArrayList<FoundItem> getFoundItems() {
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
     */
    public void addUser(User user) {
        for (User c : _users) {
            if (c.equals(user)) return;
        }
        _users.add(user);
    }

    /**
     * add a lost item to the app.  checks if the lost item is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param item the user to be added
     */
    public void addLostItem(LostItem item) {
        for (LostItem c : _lostItems) {
            if (c.equals(item)) return;
        }
        _lostItems.add(item);
    }

    /**
     * add an item to the app.  checks if the lost item is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param item the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean addItem(Item item) {
        for (Item c : _lostItems) {
            if (c.equals(item)) return false;
        }
        for (Item c : _foundItems) {
            if (c.equals(item)) return false;
        }
        System.out.println(item.getType());
        if (item.getType().equals("Found Item")) {
            _foundItems.add(new FoundItem(item.getName(), item.getDescription(), item.getAddress()));
        } else {
            _lostItems.add(new LostItem(item.getName(), item.getDescription(), item.getAddress()));
        }
        return true;
    }

    /**
     * add a found item to the app.  checks if the found item is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param item the found item to be added
     */
    public void addFoundItem(FoundItem item) {
        for (FoundItem c : _foundItems) {
            if (c.equals(item)) return;
        }
        _foundItems.add(item);
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
     * gets the current item
     *
     * @return the current item
     */
    public Item getCurrentItem() {
        return _currentItem;
    }

    /**
     * sets the current item
     *
     * @param item the current item to be set
     */
    public void setCurrentItem(Item item) {
        _currentItem = item;
    }
    /**
     * Return a user that has matching number.
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
     * creates items out of the information
     * @param found found item array
     * @param lost lost item array
     * @return list of items
     */
    private static ArrayList<Item> convertToItem(List<FoundItem> found, List<LostItem> lost) {
        ArrayList<Item> list = new ArrayList<>();
        for (FoundItem f: found) {
            Item newItem = new Item();
            newItem.setName(f.getName());
            newItem.setAddress(f.getAddress());
            newItem.setDescription(f.getDescription());
            System.out.println(newItem);
            list.add(newItem);
        }
        for (LostItem l: lost) {
            Item newItem = new Item(l.getName(), l.getDescription(), l.getAddress());
            list.add(newItem);
        }
        System.out.println("Size of list converted: " + list.size());
        return list;
    }

    /**
     * Return a user that has matching number.
     * This uses an O(n) linear search.
     *
     * @param name the username of the user to find
     * @return the user with that username or the NullUser if no such user exists.
     */
    public Item getItemByName(String name) {
        for (FoundItem c : _foundItems) {
            if (c.getName().equals(name)) return c;
        }
        for (LostItem c : _lostItems) {
            if (c.getName().equals(name)) return c;
        }
        return theNullLostItem;
    }

    /**
     * Completely wipes everything in the model
     */
    public void nuclearMeltdown() {
//        ManagementFacade mf = ManagementFacade.getInstance();
        Model model = Model.getInstance();
//        mf.removeAllFoundItems();
//        mf.removeAllLostItems();
        _users = new ArrayList<>();
        _lostItems = new ArrayList<>();
        _foundItems = new ArrayList<>();
        _bannedUsers = new ArrayList<>();
        model.setCurrentLostItem(theNullLostItem);
        model.setCurrentFoundItem(theNullFoundItem);
        model.setCurrentItem(theNullFoundItem);
    }

    /**
     *Gets both lost and found items in one list
     * @return a combined list of both items
     */
    public ArrayList getAllItems() {
        return convertToItem(_foundItems, _lostItems);
    }




}
