package Model.Persistence;

import com.example.ananya.findr.Controllers.AddLostItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.*;
/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class ItemManager implements Serializable {
    /**
     * A list of items
     */
    private final List<Item> items = new ArrayList<>();
    private final List<LostItem> lostItems = new ArrayList<>();
    private final List<FoundItem> foundItems = new ArrayList<>();
    /**
     * A map of items by Key == user name Value == student object
     *
     * This is marked as transient so it will not be serialized.
     * It is derived from the items collection above, so it does not
     * need to be serialized.
     */
    private transient Map<String, Item> itemMap = new HashMap<>();


    /**
     * adds an Item to the manager
     * @param name
     * @param description
     * @param address
     */
    void addItem(String name, String description, String address) {
        Item item = new Item(name, description, address);
        //items.add(student);
        //itemMap.put(name, student);
        AddItemCommand cmd = new AddItemCommand(item);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }

    /**
     * adds an Item to the manager
     * @param name
     * @param description
     * @param address
     */
    void addLostItem(String name, String description, String address) {
        LostItem item = new LostItem(name, description, address);
        //items.add(student);
        //itemMap.put(name, student);
        AddLostItemCommand cmd = new AddLostItemCommand(item);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }

    void addFoundItem(String name, String description, String address) {
        FoundItem item = new FoundItem(name, description, address);
        //items.add(student);
        //itemMap.put(name, student);
        AddFoundItemCommand cmd = new AddFoundItemCommand(item);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }

    /**
     * this is package vis because only model should be asking for this data
     *
     * @return
     */
    List<Item> getItems() {
        return items;
    }

    List<LostItem> getLostItems() {
        return lostItems;
    }

    List<FoundItem> getFoundItems() {
        return foundItems;
    }

    Item getItemByName(String name) {
        return itemMap.get(name);
    }

    /**
     *This is really just for lost items
     * @param writer
     */
    void saveAsText(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (lostItems.size()) + " items" );
        writer.println(lostItems.size());

        for(LostItem s : lostItems) {
            s.saveAsText(writer);
        }
    }

    /**
     *Saves Found items in their own file
     * @param writer
     */
    void saveAsFoundItems(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (foundItems.size() + " found items" ));
        writer.println(foundItems.size());
        for(FoundItem s : foundItems) {
            s.saveAsText(writer);
        }
    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    void loadFromText(BufferedReader reader) {
        System.out.println("Loading Text File");
        Model model = Model.getInstance();
        itemMap.clear();
        items.clear();
        foundItems.clear();
        lostItems.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //THE FUNCTION WHERE IT HAPPENS
            //reads in each item to the model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                LostItem l = LostItem.parseEntry(line);
                lostItems.add(l);
                model.addLostItem(l);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + (items.size() + foundItems.size()) + " items");

    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    void loadFoundItemsFromText(BufferedReader reader) {
        System.out.println("Loading Text File with Found items");
        Model model = Model.getInstance();
        itemMap.clear();
        items.clear();
        foundItems.clear();
        lostItems.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //THE FUNCTION WHERE IT HAPPENS
            //reads in each item to the model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                FoundItem s = FoundItem.parseEntry(line);
                foundItems.add(s);
                model.addFoundItem(s);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + (items.size() + foundItems.size()) + " found items");

    }


    /**
     * This should only be called during serialization (reading in).
     *
     * This recomputes the student map which is derived from the student collection.
     *
     */
    void regenMap() {
        if (itemMap != null)
            itemMap.clear();
        else
            itemMap = new HashMap<>();
        for (Item s : items) {
            itemMap.put(s.getName(), s);
        }
    }

    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void addItem(Item item) {
        items.add(item);
        itemMap.put(item.getName(), item);
    }

    void removeItem(Item item) {
        items.remove(item);
        itemMap.remove(item.getName());
    }

    void addLostItem(LostItem item) {
        lostItems.add(item);
        itemMap.put(item.getName(), item);
    }

    void removeLostItem(LostItem item) {
        lostItems.remove(item);
        itemMap.remove(item.getName());
    }

    void addFoundItem(FoundItem item) {
        foundItems.add(item);
        itemMap.put(item.getName(), item);
    }

    void removeFoundItem(FoundItem item) {
        foundItems.remove(item);
        itemMap.remove(item.getName());
    }
}
