package persistence;

import model.ListOfEntriesTest;
import model.entry.Entry;
import model.entry.ListOfEntries;
import model.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest extends EqualTest {
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

    // SOURCE: copied from JsonSerializationDemo
    @Test
    void testReaderInvalidFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // SOURCE: copied and modified from JsonSerializationDemo
    @Test
    void testReaderEmptyLoe() {
        Reader reader = new Reader("./data/testWriterEmptyLoe.json");
        try {
            listOfEntries = reader.read();
            assertEquals(0, listOfEntries.getListOfEntry().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // SOURCE: inspired from JsonSerializationDemo
    @Test
    void testReaderGeneralLoe() {
        Reader reader = new Reader("./data/testWriterGeneralLoe.json");
        try {
            listOfEntries.addEntry(testEntry1);
            listOfEntries.addEntry(testEntry2);
            listOfEntries.addEntry(testEntry3);

            ListOfEntries listOfEntriesCopy;
            listOfEntriesCopy = reader.read();

            checkLoeEqual(listOfEntries, listOfEntriesCopy);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}