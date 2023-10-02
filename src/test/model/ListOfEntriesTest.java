package model;

import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfEntriesTest {
    private ListOfEntries listOfEntries;
    private Entry testEntry1;
    private Entry testEntry2;
    private Entry testEntry3;

    private Food foodA1;
    private Food foodB1;
    private Food foodC1;
    private Food foodD1;

    private Food foodA2;
    private Food foodB2;
    private Food foodC2;
    private Food foodD2;

    private Food foodA3;
    private Food foodB3;
    private Food foodC3;

    @BeforeEach
    void runBefore() {
        listOfEntries = new ListOfEntries();

        testEntry1 = new Entry(2004, 9, 3, 180, 170);
        testEntry2 = new Entry(2023, 7, 19, 20, 23);
        testEntry3 = new Entry(1234, 12, 3, 45, 67.89);

        foodA1 = new Food("A1", 0);
        foodB1 = new Food("B1", 1);
        foodC1 = new Food("C1", 100);
        foodD1 = new Food("D1", 1234.1);

        foodA2 = new Food("A2", 9);
        foodB2 = new Food("B2", 17);
        foodC2 = new Food("C2", 105);
        foodD2 = new Food("D2", 1324.2);

        foodA3 = new Food("A3", 9);
        foodB3 = new Food("B3", 17);
        foodC3 = new Food("C3", 105.123);

        testEntry2.addFood(foodA2);
        testEntry2.addFood(foodB2);
        testEntry2.addFood(foodC2);
        testEntry2.addFood(foodD2);

        testEntry3.addFood(foodA3);
        testEntry3.addFood(foodB3);
        testEntry3.addFood(foodC3);
    }

    @Test
    void testGetListOfEntry() {
        assertTrue(listOfEntries.getListOfEntry().isEmpty());

        listOfEntries.addEntry(testEntry1);
        assertEquals(1, listOfEntries.getListOfEntry().size());
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry1));

        listOfEntries.addEntry(testEntry2);
        assertEquals(2, listOfEntries.getListOfEntry().size());
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry1));
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry2));

        listOfEntries.addEntry(testEntry3);
        assertEquals(3, listOfEntries.getListOfEntry().size());
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry1));
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry2));
        assertTrue(listOfEntries.getListOfEntry().contains(testEntry3));
    }

    @Test
    void testGetEntry() {
        listOfEntries.addEntry(testEntry1);
        listOfEntries.addEntry(testEntry2);
        listOfEntries.addEntry(testEntry3);

        assertEquals(testEntry2, listOfEntries.getEntry(2023, 7, 19));
        assertNull(listOfEntries.getEntry(2023, 7, 20));
        assertNull(listOfEntries.getEntry(2023, 7, 31));
        assertNull(listOfEntries.getEntry(2023, 12, 20));
        assertNull(listOfEntries.getEntry(12321, 7, 20));

        assertEquals(testEntry3, listOfEntries.getEntry(1234, 12, 3));

        assertEquals(testEntry1, listOfEntries.getEntry(2004, 9, 3));
    }

    @Test
    void testRemoveEntry() {
        listOfEntries.addEntry(testEntry1);
        listOfEntries.addEntry(testEntry2);
        listOfEntries.addEntry(testEntry3);

        assertTrue(listOfEntries.removeEntry(2023, 7, 19));
        assertNull(listOfEntries.getEntry(2023, 7, 19));

        assertFalse(listOfEntries.removeEntry(1234, 12, 4));
        assertFalse(listOfEntries.removeEntry(1234, 12, 29));
        assertFalse(listOfEntries.removeEntry(1234, 1, 4));
        assertFalse(listOfEntries.removeEntry(12, 12, 4));
        assertFalse(listOfEntries.removeEntry(123421, 3, 4));
        assertEquals(testEntry3, listOfEntries.getEntry(1234, 12, 3));

        assertTrue(listOfEntries.removeEntry(1234, 12, 3));
        assertNull(listOfEntries.getEntry(1234, 12, 3));
        assertEquals(1, listOfEntries.getListOfEntry().size());

        assertTrue(listOfEntries.removeEntry(2004, 9, 3));
        assertEquals(0, listOfEntries.getListOfEntry().size());
    }

    @Test
    void testGetTotalCalorie() {
        Entry testEntry4 = new Entry(2023, 7, 23, 20, 23);
        testEntry4.addFood(foodB2);
        testEntry4.addFood(foodC2);
        testEntry4.addFood(foodD1);

        listOfEntries.addEntry(testEntry1);
        listOfEntries.addEntry(testEntry2);
        listOfEntries.addEntry(testEntry3);
        listOfEntries.addEntry(testEntry4);

        assertEquals(0, listOfEntries.getTotalCalorie(2004, 1, 3, 3));
        assertEquals(1356.1, listOfEntries.getTotalCalorie(2023, 7, 20, 25));
        assertEquals(1455.2, listOfEntries.getTotalCalorie(2023, 7, 1, 22));
        assertEquals(2811.3, listOfEntries.getTotalCalorie(2023, 7, 12, 25));
    }
}