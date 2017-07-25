package model.persistence;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import model.*;

/**
 * Created by Bryce Watson on 7/14/2017.
 * Facade for managing persistence
 */

public class ManagementFacade {
    public final static String DEFAULT_TEXT_FILE_NAME = "data.txt";
    public final static String OTHER_DEFAULT_TEXT_FILE_NAME = "data2.txt";
    public final static String USER_FILE_NAME = "user.text";

    /**
     * the facade maintains references to any required model classes
     */
    private final ItemManager im;

    /**
     * Singleton pattern
     */
    private static final ManagementFacade instance = new ManagementFacade();


    /**
     * private constructor for facade pattern
     */
    private ManagementFacade() {
        im = new ItemManager();
    }

    /**
     * Singleton pattern accessor for instance
     *
     *
     * @return the one and only one instance of this facade
     */
    public static ManagementFacade getInstance() { return instance; }
    /**
     *getter items as list
     * @return items in form of list
     */
    public List<Item> getItemsAsList() {
        return im.getItems();
    }
    /**
     *getter for item by name
     * @param name name of item
     * @return item
     */
    public Item getItemByName(final String name) {
        return im.getItemByName(name);
    }
    /**
     *add items
     * @param name name of item
     * @param description description of item
     * @param address address of item
     */
    public void addNewItem(final String name, final String description, final String address) {
        im.addItem(name, description, address);
    }
    /**
     *add lost item
     *@param name name of item
     * @param description description of item
     * @param address address of item
     */
    public void addLostItem(final String name, final String description, final String address) {
        im.addLostItem(name, description, address);
    }
    /**
     *add found item
     *@param name name of item
     * @param description description of item
     * @param address address of item
     */
    public void addFoundItem(final String name, final String description, final String address) {
        im.addFoundItem(name, description, address);
    }

    /**
     *loads text
     * @param lost file for lost item
     * @param found file for found item
     * @param users file for users
     * @return true when completed false if not
     */
    public boolean loadText(File lost, File found, File users) {
        try {
            //make an input object for reading
            BufferedReader lostReader = new BufferedReader(new FileReader(lost));
            BufferedReader foundReader = new BufferedReader(new FileReader(found));
            BufferedReader userReader = new BufferedReader(new FileReader(users));
            im.loadFromText(lostReader, foundReader, userReader);

        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open text file for loading!");
            return false;
        }

        return true;
    }

    /**
     *saves text
     * @param lost lost item file
     * @param found found item file
     * @param users users file
     * @return true when completed false if not
     */
    public boolean saveText(File lost, File found, File users) {
        System.out.println("Saving as a text file");
        try {
            PrintWriter pwLost = new PrintWriter(lost);
            PrintWriter pwFound = new PrintWriter(found);
            PrintWriter pwUsers = new PrintWriter(users);
            im.saveAsText(pwLost);
            im.saveAsFoundItems(pwFound);
            im.saveAsUsers(pwUsers);
            pwLost.close();
            pwFound.close();
            pwUsers.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ManagerFacade", "Error opening the text file for save!");
            return false;
        }

        return true;
    }

    /**
     *saves found items
     * @param file file for found items
     * @return true when completed false if not
     */
    public boolean saveFoundItems(File file) {
        System.out.println("Saving found items as a text file");
        try {
            PrintWriter pw = new PrintWriter(file);
            im.saveAsFoundItems(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ManagerFacade", "Error opening the text file for save!");
            return false;
        }

        return true;
    }

    /**
     *add item
     * @param item item to be added
     */
    public void addItem(Item item) {
        im.addItem(item);
    }
    /**
     *add lost item
     * @param item item to be added
     */
    public void addLostItem(LostItem item) {
        im.addLostItem(item);
    }
    /**
     *add found item
     * @param item item to be added
     */
    public void addFoundItem(FoundItem item) {
        im.addFoundItem(item);
    }
    /**
     *add user
     * @param user user to be added
     */
    public void addUser(User user) {
        im.addUser(user);
    }
    /**
     *removes user
     * @param user user to be removed
     */
    public void removeUser(User user) {
        im.removeUser(user);
    }
    /**
     *remove item
     * @param item item to be removed
     */
    public void removeItem(Item item) {
        im.removeItem(item);
    }
    /**
     *remove lost item
     * @param item item to be removed
     */
    public void removeLostItem(LostItem item) {
        im.removeLostItem(item);
    }
    /**
     *remove found item
     * @param item item to be removed
     */
    public void removeFoundItem(FoundItem item) {
        im.removeFoundItem(item);
    }

    /**
     *removes all found items
     */
    public void removeAllFoundItems() {
        List<FoundItem> foundItems = im.getFoundItems();

        for (Iterator<FoundItem> iterator = foundItems.iterator(); iterator.hasNext(); ) {
            iterator.remove();
        }
    }

    /**
     *removes all found items
     */
    public void removeAllLostItems() {
        List<LostItem> lostItems = im.getLostItems();
        for (Iterator<LostItem> iterator = lostItems.iterator(); iterator.hasNext(); ) {
            iterator.remove();
        }
    }

    public List<LostItem> getLostItems() {
        return im.getLostItems();
    }

    public List<FoundItem> getFoundItems() {
        return im.getFoundItems();
    }

}
