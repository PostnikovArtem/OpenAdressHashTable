/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openadresshashtable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

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

    @Test
    public void testSize()
    {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(3);
        assertEquals(0, table.size());

        table.put("1", 1);
        assertEquals(1, table.size());
        table.put("2", 1);
        table.put("3", 1);
        table.put("4", 1);
        assertEquals(3, table.size());

    }

    @Test
    public void testContainsKeyValue()
    {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(3);
        assertFalse(table.containsKey("someKey"));
        assertFalse(table.containsValue(123));

        table.put("1", 1);
        assertFalse(table.containsKey("someKey"));
        assertFalse(table.containsValue(123));

        assertTrue(table.containsKey("1"));
        assertTrue(table.containsValue(1));
    }

    @Test
    public void testPutAll_Key_Values()
    {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(3);

        HashMap<String, Integer> someMap = new HashMap<>();
        someMap.put("1", 1);
        someMap.put("10", 10);
        someMap.put("100", 100);

        table.putAll(someMap);
        assertEquals(3, table.size());

        assertTrue(table.containsKey("1"));
        assertTrue(table.containsKey("10"));
        assertTrue(table.containsKey("100"));


        assertArrayEquals(someMap.keySet().toArray(), table.keySet().toArray());

        assertTrue(table.values().containsAll(someMap.values()));

        assertTrue(table.entrySet().containsAll(someMap.entrySet()));

    }

    @Test
    public void testClear()
    {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable(3);

        assertTrue(table.isEmpty());
        table.put("1", 1);
        table.put("2", 1);
        table.put("3", 1);
        assertEquals(3, table.size());

        table.clear();

        assertTrue(table.isEmpty());
    }

}
