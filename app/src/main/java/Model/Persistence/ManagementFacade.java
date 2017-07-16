package Model.Persistence;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    /**
     * the facade maintains references to any required model classes
     */
    private ItemManager im;

    /**
     * Singleton pattern
     */
    private static ManagementFacade instance = new ManagementFacade();


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

    public List<Item> getItemsAsList() {
        return im.getItems();
    }

    public Item getItemByName(final String name) {
        return im.getItemByName(name);
    }

    public void addNewItem(final String name, final String description, final String address) {
        im.addItem(name, description, address);
    }

    public void addLostItem(final String name, final String description, final String address) {
        im.addLostItem(name, description, address);
    }

    public void addFoundItem(final String name, final String description, final String address) {
        im.addFoundItem(name, description, address);
    }


    public boolean loadBinary(File file) {
        boolean success = true;
        try {
            /*
              To read, we must use the ObjectInputStream since we want to read our model in with
              a single read.
             */
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // assuming we saved our top level object, we read it back in with one line of code.
            im = (ItemManager) in.readObject();
            im.regenMap();
            in.close();
        } catch (IOException e) {
            Log.e("ManagementFacade", "Error reading an entry from binary file");
            success = false;
        } catch (ClassNotFoundException e) {
            Log.e("ManagementFacade", "Error casting a class from the binary file");
            success = false;
        }

        return success;
    }

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

    public boolean loadFoundItemText(File file) {
        try {
            //make an input object for reading
            BufferedReader reader = new BufferedReader(new FileReader(file));
            im.loadFoundItemsFromText(reader);

        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open text file for loading!");
            return false;
        }

        return true;
    }

    public boolean loadJson(File file) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            //Since we saved the json as a string, we just read in the string normally
            String inString = input.readLine();
            Log.d("DEBUG", "JSON: " + inString);
            //Then we use the Gson library to recreate the object references and links automagically
            Gson gson = new Gson();

            im = gson.fromJson(inString, ItemManager.class);

            input.close();
        } catch (IOException e) {
            Log.e("ManagementFacade", "Failed to open/read the buffered reader for json");
            return false;
        }

        return true;

    }

    public boolean saveBinary(File file) {
        boolean success = true;
        try {
            /*
               For binary, we use Serialization, so everything we write has to implement
               the Serializable interface.  Fortunately all the collection classes and APi classes
               that we might use are already Serializable.  You just have to make sure your
               classes implement Serializable.

               We have to use an ObjectOutputStream to write objects.

               One thing to be careful of:  You cannot serialize static data.
             */
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            // We basically can save our entire data model with one write, since this will follow
            // all the links and pointers to save everything.  Just save the top level object.
            out.writeObject(im);
            out.close();

        } catch (IOException e) {
            Log.e("ManagerFacade", "Error writing an entry from binary file");
            success = false;
        }
        return success;
    }

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

    public boolean saveJson(File file ) {

        try {
            PrintWriter writer = new PrintWriter(file);
            /*
                We are using the Google Gson library to make things easy.  You will need to add the
                following line to your gradle file so the proper dependencies are set up:
                compile 'com.google.code.gson:gson:2.3'

                Gson, like object serialization will take a single object and save all the objects
                it refers to.  You can save everything by one reference, as long as it is the
                top-level reference.

             */
            Gson gson = new Gson();
            // convert our objects to a string for output
            String outString = gson.toJson(im);
            Log.d("DEBUG", "JSON Saved: " + outString);
            //then just write the string
            writer.println(outString);
            writer.close();
        } catch (FileNotFoundException e) {
            Log.e("ManagementFacade", "Failed to open json file for output");
            return false;
        }

        return true;
    }


    public void addItem(Item item) {
        im.addItem(item);
    }

    public void addLostItem(LostItem item) {
        im.addLostItem(item);
    }

    public void addFoundItem(FoundItem item) {
        im.addFoundItem(item);
    }

    public void removeItem(Item item) {
        im.removeItem(item);
    }
    public void removeLostItem(LostItem item) {
        im.removeLostItem(item);
    }
    public void removeFoundItem(FoundItem item) {
        im.removeFoundItem(item);
    }

}
