/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openadresshashtable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Admin
 */
public class OpenAdressHashTableTest {

    public OpenAdressHashTableTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testPutGet() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(1000);
        assertNull(table.get("wdwd"));

        table.put("number", 1232);
        assertNotNull(table.get("number"));
        assertEquals(1232, table.get("number").longValue());

    }

    @Test
    public void testRemove() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(3);
        table.put("number", 1232);
        assertEquals(1232, table.get("number").longValue());

        table.remove("number");
        assertNull(table.get("number"));
    }

}
