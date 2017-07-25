package com.example.ananya.findr;



import org.junit.Test;

import Model.FoundItem;
import Model.LostItem;

import static org.junit.Assert.*;

/**
 * Created by Ananya on 7/24/17.
 * Junit test for the getType() method
 */
public class getTypeTest {

    private final FoundItem found = new FoundItem();

    private final LostItem lost = new LostItem();

    @Test(timeout = 200)
    public void testFoundGetType() {
        String foundType = found.getType();
        assertEquals("Found Item", foundType);
    }

    @Test(timeout = 200)
    public void testLostGetType() {
        String lostType = lost.getType();
        assertEquals("Lost Item", lostType);
    }


}
