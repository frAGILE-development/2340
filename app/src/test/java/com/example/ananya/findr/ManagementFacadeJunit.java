package com.example.ananya.findr;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Model.FoundItem;
import Model.LostItem;
import Model.Persistence.ManagementFacade;
import Model.User;

import static com.example.ananya.findr.ManagementFacadeJunit.to;
import static junit.framework.Assert.assertEquals;

/**
 * Created by jordan on 7/18/2017.
 */

public class ManagementFacadeJunit {
    private ManagementFacade mf;
    private User u;
    private FoundItem f;
    private LostItem l;

    private final File lost = new File("lost.txt");
    private final File found = new File("found.txt");
    private final File user = new File("user.txt");
    private final PrintWriter pwu = new PrintWriter("user.txt");
    private final PrintWriter pwf = new PrintWriter("found.txt");
    private final PrintWriter pwl = new PrintWriter("lost.txt");

    public static final int to = 200;

    public ManagementFacadeJunit() throws FileNotFoundException {
    }

    @Before
    public void setUp() {
        mf = ManagementFacade.getInstance();
        u = new User();
        u.saveAsText(pwu);
        f = new FoundItem();
        f.saveAsText(pwf);
        l = new LostItem();
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
