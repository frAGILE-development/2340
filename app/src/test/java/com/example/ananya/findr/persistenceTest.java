package com.example.ananya.findr;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.persistence.ManagementFacade;
import model.User;
import model.Model;
import model.FoundItem;
import model.LostItem;

/**
 * Created by Bryce Watson on 7/25/2017.
 * A junit test to test persistence by saving a loading through text
 */

@SuppressLint("Registered")
public class persistenceTest extends AppCompatActivity{

    //Instance Variables
    private final ManagementFacade mf = ManagementFacade.getInstance();
    private final Model model = Model.getInstance();
    private final String[] lostNameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
            "The Lord of the Rings", "The Things They Carried",
            "The Three Body Problem"};
    private final String[] foundNameList = new String[]{"1984", "Brave New World",
            "Animal Farm", "Dune", "The Princess Bride"};
    private final File lost = new File("lost.txt");
    private final File found = new File("found.txt");
    private final File user = new File("user.txt");



    @Test(timeout = 200)
    public void saveUsersAndItems() throws Exception {

        User user1 = new User("user1", "Eren", "Jaegar", "123", "user1@gatech.edu", "0123456789", "user");
        User user2 = new User("user2", "Mikasa", "Ackerman", "234", "user2@gatech.edu", "987654321", "admin");
        User user3 = new User("user2", "Mikasa", "Ackerman", "345", "user2@gatech.edu", "010101010", "admin");
        User user4 = new User("user4", "Reiner", "Braun", "456", "user4@gatech.edu", "122122121", "user");
        List<User> _userList = new ArrayList<>();

        _userList.add(user1);
        _userList.add(user2);
        _userList.add(user3);
        _userList.add(user4);


        LostItem item1 = new LostItem(lostNameList[0], "A  novel by Charles Dickens, set in London " +
                "and Paris before and during the French Revolution.", "London, England");

        LostItem item2 = new LostItem(lostNameList[1], "A comedy science fiction series created " +
                "by Douglas Adams. Originally a radio comedy broadcast on BBC Radio 4 in 1978, " +
                "it was later adapted to other formats, including stage shows, novels, comic books, " +
                "a 1981 TV series, a 1984 computer game, and 2005 feature film", "London, England");


        FoundItem item3 = new FoundItem(foundNameList[0], "A dystopian novel published in 1949 " +
                "by English author George Orwell." +
                " The novel is set in Airstrip One (formerly known as Great Britain), a province " +
                "of the superstate Oceania in a world of perpetual war, omnipresent government " +
                "surveillance, and public manipulation.", "London, England");

        FoundItem item4 = new FoundItem(foundNameList[4], "A 1973 fantasy romance novel written" +
                " by William Goldman. The book combines elements of comedy, adventure, " +
                "fantasy, romantic love, romance, and fairy tale", "Chicago, Illinois, US");

        model.addUser(user1);
        mf.addUser(user1);
        model.addUser(user2);
        mf.addUser(user2);
        model.addUser(user3);
        mf.addUser(user3);
        model.addUser(user4);
        mf.addUser(user4);
        model.addLostItem(item1);
        model.addLostItem(item2);
        model.addFoundItem(item3);
        model.addFoundItem(item4);
        mf.addLostItem(item1);
        mf.addLostItem(item2);
        mf.addFoundItem(item3);
        mf.addFoundItem(item4);

        mf.saveText(lost, found, user);
        model.nuclearMeltdown();//erase all data
        mf.loadText(lost, found, user);//load data again
        List<LostItem> lostItems = model.getLostItems();
        List<FoundItem> foundItems = model.getFoundItems();
        List<User> users = model.getUsers();

        assert(lostItems.get(0).equals(item1));
        assert(lostItems.get(1).equals(item2));
        assert(foundItems.get(0).equals(item3));
        assert(foundItems.get(1).equals(item4));

        for (int i = 0; i < 4; i++) {
            assert(_userList.get(i).equals(users.get(i)));
        }

    }








}
