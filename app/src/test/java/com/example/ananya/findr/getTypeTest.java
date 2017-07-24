package com.example.ananya.findr;

/**
 * Created by Ananya on 7/24/17.
 */

import org.junit.Before;
import org.junit.Test;

import Model.FoundItem;
import Model.LostItem;

import static org.junit.Assert.*;

public class getTypeTest {

    private FoundItem found = new FoundItem();

    private LostItem lost = new LostItem();

    @Test(timeout = 200)
    public void testFoundgetType() {
        String foundType = found.getType();
        assertEquals("Found Item", foundType);
    }

    @Test(timeout = 200)
    public void testLostgetType() {
        String lostType = lost.getType();
        assertEquals("Lost Item", lostType);
    }


}
