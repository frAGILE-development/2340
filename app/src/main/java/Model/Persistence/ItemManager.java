package Model.Persistence;

import com.example.ananya.findr.Controllers.AddLostItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InterruptedIOException;
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
    private final List<User> userList = new ArrayList<>();
    /**
     * A map of items by Key == user name Value == student object
     *
     * This is marked as transient so it will not be serialized.
     * It is derived from the items collection above, so it does not
     * need to be serialized.
     */
    private transient Map<String, Item> itemMap = new HashMap<>();
    private transient Map<String, User> userMap = new HashMap<>();


    /**
     * adds an Item to the manager
     * @param name item name
     * @param description description of item
     * @param address address of item
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
     * @param name item name
     * @param description description of item
     * @param address address of item
     */
    void addLostItem(String name, String description, String address) {
        LostItem item = new LostItem(name, description, address);
        //items.add(student);
        //itemMap.put(name, student);
        AddLostItemCommand cmd = new AddLostItemCommand(item);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }
    /**
     * adds an Item to the manager
     * @param name item name
     * @param description description of item
     * @param address address of item
     */
    void addFoundItem(String name, String description, String address) {
        FoundItem item = new FoundItem(name, description, address);
        //items.add(student);
        //itemMap.put(name, student);
        AddFoundItemCommand cmd = new AddFoundItemCommand(item);
        CommandManager commandManager = AbstractCommand.manager;
        commandManager.executeCommand(cmd);
    }
    /**
     *add user
     * @param username username of user
     * @param firstName user's first name
     * @param lastName user's last name
     * @param password password of user
     * @param email user's email
     * @param phoneNumber user's phone number
     * @param type user type
     *
     */
    void addUser(String username, String firstName, String lastName, String password, String email,
                 String phoneNumber, String type) {
        User user = new User(username, firstName, lastName, password, email, phoneNumber, type);
        //items.add(student);
        //itemMap.put(name, student);
        userList.add(user);
    }

    /**
     * this is package vis because only model should be asking for this data
     *
     * @return list of items
     */
    List<Item> getItems() {
        return items;
    }
    /**
     *getter lost item
     * @return lost item
     *
     */
    List<LostItem> getLostItems() {
        return lostItems;
    }
    /**
     *getter found item
     * @return found item
     *
     */
    List<FoundItem> getFoundItems() {
        return foundItems;
    }
    /**
     *get time
     * @param name name of item
     * @return item
     */
    Item getItemByName(String name) {
        return itemMap.get(name);
    }

    /**
     *This is really just for lost items
     * @param writer print writer
     */
    void saveAsText(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (lostItems.size()) + " items" );
        writer.println(lostItems.size());

        for(LostItem s : lostItems) {
            s.saveAsText(writer);
        }
    }
//    /**
//     *This is really just for lost items
//     * @param writer
//     */
//    void saveAsBinary(PrintWriter writer) {
//        System.out.println("Item Manager saving: " + (lostItems.size()) + " items" );
//        writer.println(lostItems.size());
//
//        for (LostItem s : lostItems) {
//            s.saveAsBinary(writer);
//        }
//    }
    /**
     *save user
     * @param writer print writer
     *
     */
    void saveAsUsers(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (userList.size()) + " users" );
        writer.println(userList.size());

        for(User u : userList) {
            u.saveAsText(writer);
        }
    }
//    /**
//     *save user
//     * @param writer
//     *
//     */
//    void saveAsUsersBinary(PrintWriter writer) {
//        System.out.println("Item Manager saving: " + (userList.size()) + " users" );
//        writer.println(userList.size());
//
//        for(User u : userList) {
//            u.saveAsBinary(writer);
//        }
//    }

    /**
     * Saves both lost and found items simultaneously
     * @param lost lost item details
     * @param found found item details
     */
    void saveAsText(PrintWriter lost, PrintWriter found) {
        System.out.println("Item Manager saving: " + (lostItems.size() + foundItems.size()) + " items" );
        lost.println(lostItems.size());
        found.print(foundItems.size());
        for(LostItem s : lostItems) {
            s.saveAsText(lost);
        }
        for(FoundItem s : foundItems) {
            s.saveAsText(found);
        }
    }

    /**
     *Saves Found items in their own file
     * @param writer print writer
     */
    void saveAsFoundItems(PrintWriter writer) {
        System.out.println("Item Manager saving: " + (foundItems.size() + " found items" ));
        writer.println(foundItems.size());
        for (FoundItem s : foundItems) {
            s.saveAsText(writer);
        }
    }
//    /**
//     *Saves Found items in their own file
//     * @param writer
//     */
//    void saveAsFoundItemsBinary(PrintWriter writer) {
//        System.out.println("Item Manager saving: " + (foundItems.size() + " found items" ));
//        writer.println(foundItems.size());
//        for (FoundItem s : foundItems) {
//            s.saveAsBinary(writer);
//        }
//    }
//    void loadFromBinary(BufferedReader lost, BufferedReader found) {
//        System.out.println("Loading Text File");
//        Model model = Model.getInstance();
//        itemMap.clear();
//        items.clear();
//        foundItems.clear();
//        lostItems.clear();
//        try {
//            String countStrLost = lost.readLine();
//            assert countStrLost != null;
//
//            String countStrFound = found.readLine();
//            assert countStrFound != null;
//
//            int lostCount = Integer.parseInt(countStrLost);
//            int foundCount = Integer.parseInt(countStrFound);
//            //THE FUNCTION WHERE IT HAPPENS
//            //reads in each item to the model
//            for (int i = 0; i < lostCount; ++i) {
//                String line = lost.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                LostItem l = LostItem.parseEntry(item);
//                lostItems.add(l);
//                model.addLostItem(l);
//            }
//            for (int i = 0; i < foundCount; ++i) {
//                String line = found.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                FoundItem l = FoundItem.parseEntry(item);
//                foundItems.add(l);
//                model.addFoundItem(l);
//            }
//            //be sure and close the file
//            lost.close();
//            found.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done loading text file with " + foundItems.size() + "found items" +
//                "and " + lostItems.size() + " lost items");
//
//    }

    /**
     * load from text
     * @param lost lost item
     * @param found found item
     */
    void loadFromText(BufferedReader lost, BufferedReader found) {
        System.out.println("Loading Text File");
        Model model = Model.getInstance();
        itemMap.clear();
        items.clear();
        foundItems.clear();
        lostItems.clear();
        try {
            String countStrLost = lost.readLine();
            assert countStrLost != null;

            String countStrFound = found.readLine();
            assert countStrFound != null;

            int lostCount = Integer.parseInt(countStrLost);
            int foundCount = Integer.parseInt(countStrFound);
            //THE FUNCTION WHERE IT HAPPENS
            //reads in each item to the model
            for (int i = 0; i < lostCount; ++i) {
                String line = lost.readLine();
                LostItem l = LostItem.parseEntry(line);
                lostItems.add(l);
                model.addLostItem(l);
            }
            for (int i = 0; i < foundCount; ++i) {
                String line = found.readLine();
                FoundItem l = FoundItem.parseEntry(line);
                foundItems.add(l);
                model.addFoundItem(l);
            }
            //be sure and close the file
            lost.close();
            found.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + foundItems.size() + "found items" +
        "and " + lostItems.size() + " lost items");

    }

    /**
     *loads form text
     * @param lost lost item
     * @param found found item
     * @param users users
     */
    void loadFromText(BufferedReader lost, BufferedReader found, BufferedReader users) {
        System.out.println("Loading Text File");
        Model model = Model.getInstance();
        itemMap.clear();
        items.clear();
        foundItems.clear();
        lostItems.clear();
        userList.clear();
        try {
            String countStrLost = lost.readLine();
            assert countStrLost != null;
            String countStrFound = found.readLine();
            assert countStrFound != null;
            String countStrUsers = users.readLine();
            assert countStrUsers != null;
            int lostCount = Integer.parseInt(countStrLost);
            int foundCount = Integer.parseInt(countStrFound);
            int userCount = Integer.parseInt(countStrUsers);
            //THE FUNCTION WHERE IT HAPPENS
            //reads in each item to the model
            for (int i = 0; i < lostCount; ++i) {
                String line = lost.readLine();
                LostItem l = LostItem.parseEntry(line);
                lostItems.add(l);
                model.addLostItem(l);
            }
            for (int i = 0; i < foundCount; ++i) {
                String line = found.readLine();
                FoundItem l = FoundItem.parseEntry(line);
                foundItems.add(l);
                model.addFoundItem(l);
            }
            for (int i = 0; i < userCount; ++i) {
                String line = users.readLine();
                User l = User.parseEntry(line);
                userList.add(l);
                model.addUser(l);
            }

            //be sure and close the file
            users.close();
            lost.close();
            found.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done loading text file with " + foundItems.size() + "found items" +
                "and " + lostItems.size() + " lost items");

    }
//    /**
//     *loads form binary
//     * @param lost lost
//     * @param found
//     * @param users
//     */
//    void loadFromBinary(BufferedReader lost, BufferedReader found, BufferedReader users) {
//        System.out.println("Loading Text File");
//        Model model = Model.getInstance();
//        itemMap.clear();
//        items.clear();
//        foundItems.clear();
//        lostItems.clear();
//        userList.clear();
//        try {
//            String countStrLost = lost.readLine();
//            assert countStrLost != null;
//
//            String countStrFound = found.readLine();
//            assert countStrFound != null;
//            String countStrUsers = users.readLine();
//            assert countStrUsers != null;
//
//            int lostCount = Integer.parseInt(countStrLost);
//            int foundCount = Integer.parseInt(countStrFound);
//            int userCount = Integer.parseInt(countStrUsers);
//            //THE FUNCTION WHERE IT HAPPENS
//            //reads in each item to the model
//            for (int i = 0; i < lostCount; ++i) {
//                String line = lost.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                LostItem l = LostItem.parseEntry(item);
//                lostItems.add(l);
//                model.addLostItem(l);
//            }
//            for (int i = 0; i < foundCount; ++i) {
//                String line = found.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                FoundItem l = FoundItem.parseEntry(item);
//                foundItems.add(l);
//                model.addFoundItem(l);
//            }
//            for (int i = 0; i < userCount; ++i) {
//                String line = users.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                User l = User.parseEntry(item);
//                userList.add(l);
//                model.addUser(l);
//            }
//            //be sure and close the file
//            lost.close();
//            found.close();
//            users.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done loading text file with " + foundItems.size() + "found items" +
//                "and " + lostItems.size() + " lost items");
//
//    }

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
//    /**
//     * load the model from a custom binary file
//     *
//     * @param reader  the file to read from
//     */
//    void loadFoundItemsFromTextBinary(BufferedReader reader) {
//        System.out.println("Loading Text File with Found items");
//        Model model = Model.getInstance();
//        itemMap.clear();
//        items.clear();
//        foundItems.clear();
//        lostItems.clear();
//        try {
//            String countStr = reader.readLine();
//            assert countStr != null;
//            int count = Integer.parseInt(countStr);
//
//            //THE FUNCTION WHERE IT HAPPENS
//            //reads in each item to the model
//            for (int i = 0; i < count; ++i) {
//                String line = reader.readLine();
//                int conv = Integer.parseInt(line, 2);
//                String item = new Character((char)conv).toString();
//                FoundItem s = FoundItem.parseEntry(item);
//                foundItems.add(s);
//                model.addFoundItem(s);
//            }
//            //be sure and close the file
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done loading text file with " + (items.size() + foundItems.size()) + " found items");
//
//    }


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
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void removeItem(Item item) {
        items.remove(item);
        itemMap.remove(item.getName());
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void addLostItem(LostItem item) {
        lostItems.add(item);
        itemMap.put(item.getName(), item);
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void removeLostItem(LostItem item) {
        lostItems.remove(item);
        itemMap.remove(item.getName());
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void addFoundItem(FoundItem item) {
        foundItems.add(item);
        itemMap.put(item.getName(), item);
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void removeFoundItem(FoundItem item) {
        foundItems.remove(item);
        itemMap.remove(item.getName());
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param user the student to add
     */
    void addUser(User user) {
        userList.add(user);
        userMap.put(user.getUsername(), user);
    }
    /**
     * used by command pattern, should be not called otherwise
     *
     * @param item the student to add
     */
    void removeUser(User item) {
        foundItems.remove(item);
        userMap.remove(item.getUsername());
    }
}
