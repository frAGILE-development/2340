package com.example.ananya.findr;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.FoundItem;
import model.LostItem;
import model.persistence.ManagementFacade;
import model.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jordan on 7/18/2017.
 * Junit tests for the management facade class
 */
public class ManagementFacadeJunit {
    private ManagementFacade mf;

    private final File lost = new File("lost.txt");
    private final File found = new File("found.txt");
    private final File user = new File("user.txt");
    private final PrintWriter pwu = new PrintWriter("user.txt");
    private final PrintWriter pwf = new PrintWriter("found.txt");
    private final PrintWriter pwl = new PrintWriter("lost.txt");

    private static final int to = 200;

    public ManagementFacadeJunit() throws FileNotFoundException {
    }

    @Before
    public void setUp() {
        mf = ManagementFacade.getInstance();
        User u = new User();
        mf.addUser(u);
        u.saveAsText(pwu);
        FoundItem f = new FoundItem();
        mf.addFoundItem(f);
        f.saveAsText(pwf);
        LostItem l = new LostItem();
        mf.addLostItem(l);
        l.saveAsText(pwl);
        mf.saveText(lost, found, user);
    }
    /**
     * author jordan shartar
     * test for loadText(File lost, File found, File users)
     * method in ManagementFacade
     */
    @Test(timeout = to)
    public void testLoadText() {
        boolean is = mf.loadText(lost, found, user);
        assertEquals(true, is);
    }

}
