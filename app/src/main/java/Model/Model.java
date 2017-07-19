package Model;

import java.util.ArrayList;
import java.util.List;
import Model.FoundItem;
import Model.Persistence.ManagementFacade;

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
    private List<User> _bannedUsers;
    /**
     * the currently selected user, defaults to the first one
     */
    private User _currentUser;
    //the currently selected lost item
    private LostItem _currentLostItem;
    private FoundItem _currentFoundItem;
    private Item _currentItem;

    //Null Users and items
    private final User theNullUser = new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", User.accountType.get(1));
    private final LostItem theNullLostItem = new LostItem("NULL", "NULL", "NULL");
    private final FoundItem theNullFoundItem = new FoundItem("NULL", "NULL", "NULL");
    /**
     * makes a new model
     */
    private Model() {
        _users = new ArrayList<>();
        _lostItems = new ArrayList<>();
        _foundItems = new ArrayList<>();
        _bannedUsers = new ArrayList<>();
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
     * @return true if added, false if a duplicate
     */
    public boolean addBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)) return false;
        }
        _bannedUsers.add(user);
        return true;
    }


    /**
     * removes a banned user.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be removed
     * @return true if removed, false not there
     */
    public boolean removeBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)){
                _bannedUsers.remove(c);
                return true;
            };
        }
    return false;
    }

    /**
     * returns true if the user is banned, false otherwise
     * @param user the user to check its lockout status
     * @return
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
        ArrayList<Item> list = new ArrayList<Item>();
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
        ManagementFacade mf = ManagementFacade.getInstance();
        Model model = Model.getInstance();
        mf.removeAllFoundItems();
        mf.removeAllLostItems();
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
