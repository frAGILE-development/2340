package Model.Persistence;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import Model.*;

/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class ManagementFacade {
    public final static String DEFAULT_BINARY_FILE_NAME = "data.bin";
    public final static String DEFAULT_TEXT_FILE_NAME = "data.txt";
    public final static String OTHER_DEFAULT_TEXT_FILE_NAME = "data2.txt";
    public final static String DEFAULT_JSON_FILE_NAME = "data.json";
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

//    /**
//     *loads binary
//     * @param lost
//     * @param found
//     * @return true when completed false if not
//     */
//    public boolean loadBinary(File lost, File found) {
//        boolean success = true;
//        try {
//            BufferedReader lostReader = new BufferedReader(new FileReader(lost));
//            BufferedReader foundReader = new BufferedReader(new FileReader(found));
//            im.loadFromBinary(lostReader, foundReader);
////            /*
////              To read, we must use the ObjectInputStream since we want to read our model in with
////              a single read.
////             */
////            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
////            // assuming we saved our top level object, we read it back in with one line of code.
////            im = (ItemManager) in.readObject();
////            im.regenMap();
////            in.close();
//        } catch (IOException e) {
//            Log.e("ManagementFacade", "Error reading an entry from binary file");
//            success = false;
//        }
//
//        return success;
//    }

//    public boolean loadText(File file) {
//        try {
//            //make an input object for reading
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            im.loadFromText(reader);
//
//        } catch (FileNotFoundException e) {
//            Log.e("ModelSingleton", "Failed to open text file for loading!");
//            return false;
//        }
//
//        return true;
//    }
    /**
     *loads text
     * @param lost file for lost item
     * @param found file for found item
     * @return true when completed false if not
     */
    public boolean loadText(File lost, File found) {
        try {
            //make an input object for reading
            BufferedReader lostReader = new BufferedReader(new FileReader(lost));
            BufferedReader foundReader = new BufferedReader(new FileReader(found));
            im.loadFromText(lostReader, foundReader);

        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open text file for loading!");
            return false;
        }

        return true;
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

//    /**
//     *loads binary
//     * @param lost
//     * @param found
//     * @param users
//     * @return true when completed false if not
//     */
//    public boolean loadBinary(File lost, File found, File users) {
//        try {
//            //make an input object for reading
//            BufferedReader lostReader = new BufferedReader(new FileReader(lost));
//            BufferedReader foundReader = new BufferedReader(new FileReader(found));
//            BufferedReader userReader = new BufferedReader(new FileReader(users));
//            im.loadFromBinary(lostReader, foundReader, userReader);
//
//        } catch (FileNotFoundException e) {
//            Log.e("ModelSingleton", "Failed to open text file for loading!");
//            return false;
//        }
//
//        return true;
//    }

//    public boolean loadFoundItemText(File file) {
//        try {
//            //make an input object for reading
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            im.loadFoundItemsFromText(reader);
//
//        } catch (FileNotFoundException e) {
//            Log.e("ModelSingleton", "Failed to open text file for loading!");
//            return false;
//        }
//
//        return true;
//    }
//    /**
//     *loads json
//     * @param file
//     * @return true when completed false if not
//     */
//    public boolean loadJson(File file) {
//        try {
//            BufferedReader input = new BufferedReader(new FileReader(file));
//            //Since we saved the json as a string, we just read in the string normally
//            String inString = input.readLine();
//            Log.d("DEBUG", "JSON: " + inString);
//            //Then we use the Gson library to recreate the object references and links automatically
//            Gson gson = new Gson();
//
//            im = gson.fromJson(inString, ItemManager.class);
//
//            input.close();
//        } catch (IOException e) {
//            Log.e("ManagementFacade", "Failed to open/read the buffered reader for json");
//            return false;
//        }
//
//        return true;
//
//    }
//    /**
//     *saves binary
//     * @param file
//     * @return true when completed false if not
//     */
//    public boolean saveBinary(File file) {
//        boolean success = true;
//        try {
//            PrintWriter pw = new PrintWriter(file);
//            im.saveAsBinary(pw);
//            pw.close();
////            /*
////               For binary, we use Serialization, so everything we write has to implement
////               the Serializable interface.  Fortunately all the collection classes and APi classes
////               that we might use are already Serializable.  You just have to make sure your
////               classes implement Serializable.
////
////               We have to use an ObjectOutputStream to write objects.
////
////               One thing to be careful of:  You cannot serialize static data.
////             */
////            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
////            // We basically can save our entire data model with one write, since this will follow
////            // all the links and pointers to save everything.  Just save the top level object.
////            out.writeObject(im);
////            out.close();
//
//        } catch (IOException e) {
//            Log.e("ManagerFacade", "Error writing an entry from binary file");
//            success = false;
//        }
//        return success;
//    }
    /**
     *saves text
     * @param file file with data
     * @return true when completed false if not
     */
    public boolean saveText(File file) {
        System.out.println("Saving as a text file");
        try {
            PrintWriter pw = new PrintWriter(file);
            im.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ManagerFacade", "Error opening the text file for save!");
            return false;
        }

        return true;
    }
    /**
     *saves text
     * @param lost file for lost items
     * @param found file for found items
     * @return true when completed false if not
     */
    public boolean saveText(File lost, File found) {
        System.out.println("Saving as a text file");
        try {
            PrintWriter pwLost = new PrintWriter(lost);
            PrintWriter pwFound = new PrintWriter(found);
            im.saveAsText(pwLost);
            im.saveAsFoundItems(pwFound);
            pwLost.close();
            pwFound.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ManagerFacade", "Error opening the text file for save!");
            return false;
        }

        return true;
    }
//    /**
//     *saves binary
//     * @param lost
//     * @param found
//     * @return true when completed false if not
//     */
//    public boolean saveBinary(File lost, File found) {
//        boolean success = true;
//        try {
//            PrintWriter pwl = new PrintWriter(lost);
//            PrintWriter pwf = new PrintWriter(found);
//            im.saveAsBinary(pwl);
//            im.saveAsFoundItemsBinary(pwf);
//            pwl.close();
//            pwf.close();
////            /*
////               For binary, we use Serialization, so everything we write has to implement
////               the Serializable interface.  Fortunately all the collection classes and APi classes
////               that we might use are already Serializable.  You just have to make sure your
////               classes implement Serializable.
////
////               We have to use an ObjectOutputStream to write objects.
////
////               One thing to be careful of:  You cannot serialize static data.
////             */
////            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
////            // We basically can save our entire data model with one write, since this will follow
////            // all the links and pointers to save everything.  Just save the top level object.
////            out.writeObject(im);
////            out.close();
//
//        } catch (IOException e) {
//            Log.e("ManagerFacade", "Error writing an entry from binary file");
//            success = false;
//        }
//        return success;
//    }
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
//    /**
//     *saves binary
//     * @param lost
//     * @param found
//     * @param users
//     * @return true when completed false if not
//     */
//    public boolean saveBinary(File lost, File found, File users) {
//        boolean success = true;
//        try {
//            PrintWriter pwl = new PrintWriter(lost);
//            PrintWriter pwf = new PrintWriter(found);
//            PrintWriter pwu = new PrintWriter(users);
//            im.saveAsBinary(pwl);
//            im.saveAsFoundItemsBinary(pwf);
//            im.saveAsUsersBinary(pwu);
//            pwl.close();
//            pwf.close();
//            pwu.close();
////            /*
////               For binary, we use Serialization, so everything we write has to implement
////               the Serializable interface.  Fortunately all the collection classes and APi classes
////               that we might use are already Serializable.  You just have to make sure your
////               classes implement Serializable.
////
////               We have to use an ObjectOutputStream to write objects.
////
////               One thing to be careful of:  You cannot serialize static data.
////             */
////            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
////            // We basically can save our entire data model with one write, since this will follow
////            // all the links and pointers to save everything.  Just save the top level object.
////            out.writeObject(im);
////            out.close();
//
//        } catch (IOException e) {
//            Log.e("ManagerFacade", "Error writing an entry from binary file");
//            success = false;
//        }
//        return success;
//    }
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
//    /**
//     *saves json
//     * @param file
//     * @return true when completed false if not
//     */
//    public boolean saveJson(File file ) {
//
//        try {
//            PrintWriter writer = new PrintWriter(file);
//            /*
//                We are using the Google Gson library to make things easy.  You will need to add the
//                following line to your gradle file so the proper dependencies are set up:
//                compile 'com.google.code.gson:gson:2.3'
//
//                Gson, like object serialization will take a single object and save all the objects
//                it refers to.  You can save everything by one reference, as long as it is the
//                top-level reference.
//
//             */
//            Gson gson = new Gson();
//            // convert our objects to a string for output
//            String outString = gson.toJson(im);
//            Log.d("DEBUG", "JSON Saved: " + outString);
//            //then just write the string
//            writer.println(outString);
//            writer.close();
//        } catch (FileNotFoundException e) {
//            Log.e("ManagementFacade", "Failed to open json file for output");
//            return false;
//        }
//
//        return true;
//    }

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

        for (FoundItem f : im.getFoundItems()){
            im.removeFoundItem(f);
        }
    }

    /**
     *removes all found items
     */
    public void removeAllLostItems() {

        for (LostItem l : im.getLostItems()){
            im.removeLostItem(l);
        }
    }

}
