package model;

import model.food.Food;
import model.food.ListOfFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfFoodTest {
    private ListOfFood testList;

    private Food foodA;
    private Food foodB;
    private Food foodC;
    private Food foodD;

    @BeforeEach
    void runBefore() {
        testList = new ListOfFood();

        foodA = new Food("A", 0);
        foodB = new Food("B", 1);
        foodC = new Food("C", 100);
        foodD = new Food("D", 1234.1);
    }

    @Test
    void testConstructor() {
        assertTrue(testList.getListOfFood().isEmpty());

        assertEquals("A", foodA.getName());
        assertEquals(0, foodA.getCalorie());

        assertEquals("B", foodB.getName());
        assertEquals(1, foodB.getCalorie());

        assertEquals("C", foodC.getName());
        assertEquals(100, foodC.getCalorie());

        assertEquals("D", foodD.getName());
        assertEquals(1234.1, foodD.getCalorie());
    }

    @Test
    void testAddFood() {
        testList.addFood(foodA);

        assertEquals(1, testList.getListOfFood().size());
        assertTrue(testList.getListOfFood().contains(foodA));
        assertEquals(0, testList.getTotalCalorie());

        testList.addFood(foodB);
        testList.addFood(foodC);

        assertEquals(101, testList.getTotalCalorie());
        assertEquals(3, testList.getListOfFood().size());
        assertTrue(testList.getListOfFood().contains(foodA));
        assertTrue(testList.getListOfFood().contains(foodB));
        assertTrue(testList.getListOfFood().contains(foodC));

        testList.addFood(foodA);

        assertEquals(4, testList.getListOfFood().size());
        assertTrue(testList.getListOfFood().contains(foodA));
        assertTrue(testList.getListOfFood().contains(foodB));
        assertTrue(testList.getListOfFood().contains(foodC));
        assertTrue(testList.getListOfFood().contains(foodA));
    }

    @Test
    void testRemoveFood() {
        testList.addFood(foodA);
        testList.addFood(foodB);
        testList.addFood(foodC);

        assertEquals(3, testList.getListOfFood().size());
        assertTrue(testList.getListOfFood().contains(foodA));
        assertTrue(testList.getListOfFood().contains(foodB));
        assertTrue(testList.getListOfFood().contains(foodC));

        assertFalse(testList.removeFood("D"));
        assertTrue(testList.removeFood("A"));

        assertEquals(2, testList.getListOfFood().size());
        assertFalse(testList.getListOfFood().contains(foodA));
        assertTrue(testList.getListOfFood().contains(foodB));
        assertTrue(testList.getListOfFood().contains(foodC));

        assertFalse(testList.removeFood(2));
        assertFalse(testList.removeFood(-1));
        assertFalse(testList.removeFood(100));
        assertTrue(testList.removeFood(1));

        assertEquals(1, testList.getListOfFood().size());
        assertTrue(testList.getListOfFood().contains(foodB));
        assertFalse(testList.getListOfFood().contains(foodC));
    }

    @Test
    void testGetFood() {
        Food foodB_1 = new Food("B", 2);

        testList.addFood(foodB);
        testList.addFood(foodA);
        testList.addFood(foodB_1);
        testList.addFood(foodC);

        assertEquals(103, testList.getTotalCalorie());
        assertEquals(foodB, testList.getFood("B"));
        assertEquals(foodB, testList.getFood(0));
        assertNotEquals(foodB_1, testList.getFood(0));

        assertNull(testList.getFood("BC"));
        assertNull(testList.getFood(4));

        assertEquals(foodA, testList.getFood("A"));
        assertEquals(foodA, testList.getFood(1));

        assertEquals(foodC, testList.getFood("C"));
        assertEquals(foodC, testList.getFood(3));

        assertEquals(foodB_1, testList.getFood(2));
    }

    @Test
    void testChangeFoodName() {
        Food foodC_1 = new Food("C", 2);

        testList.addFood(foodB);
        testList.addFood(foodC);
        testList.addFood(foodA);
        testList.addFood(foodC_1);

        double testCalorie = testList.getFood("B").getCalorie();

        assertTrue(testList.changeFoodName("B", "D"));

        assertNull(testList.getFood("B"));
        assertEquals("D", testList.getFood("D").getName());
        assertEquals(testCalorie, testList.getFood("D").getCalorie());
        assertFalse(testList.changeFoodName("AS", "B"));
        assertTrue(testList.changeFoodName("D", "B"));

        assertTrue(testList.changeFoodName(0, "D"));
        assertFalse(testList.changeFoodName(-1, "D"));
        assertFalse(testList.changeFoodName(4, "D"));
        assertFalse(testList.changeFoodName(100, "D"));

        assertNull(testList.getFood("B"));
        assertEquals("D", testList.getFood("D").getName());
        assertEquals(testCalorie, testList.getFood("D").getCalorie());

        testCalorie = testList.getFood("C").getCalorie();
        assertTrue(testList.changeFoodName("C", "Z"));

        assertEquals("Z", testList.getFood("Z").getName());
        assertEquals(testCalorie, testList.getFood("Z").getCalorie());

        assertEquals("C", testList.getFood("C").getName());
        assertNotEquals(testCalorie, testList.getFood("C").getCalorie());

        assertTrue(testList.changeFoodName("Z", "C"));

        testCalorie = testList.getFood(3).getCalorie();
        assertTrue(testList.changeFoodName(3, "Z"));

        assertEquals("C", testList.getFood(1).getName());
    }

    @Test
    void testChangeFoodCalorie() {
        Food foodA_1 = new Food("A", 2);

        testList.addFood(foodC);
        testList.addFood(foodB);
        testList.addFood(foodA);
        testList.addFood(foodA_1);

        assertTrue(testList.changeFoodCalorie("C", 1));

        assertEquals(1, testList.getFood(0).getCalorie());

        assertFalse(testList.changeFoodCalorie("Z", 1));
        assertFalse(testList.changeFoodCalorie("Z", 1000));
        assertFalse(testList.changeFoodCalorie("Z", 10.1));
        assertFalse(testList.changeFoodCalorie(4, 1));
        assertFalse(testList.changeFoodCalorie(12, 1231));
        assertFalse(testList.changeFoodCalorie(-1, 1231));

        assertTrue(testList.changeFoodCalorie(0, 100.1));
        assertEquals(103.1, testList.getTotalCalorie());

        assertEquals(100.1, testList.getFood(0).getCalorie());

        assertNull(testList.getFood(-1));
        assertNull(testList.getFood(4));

        assertTrue(testList.changeFoodCalorie("A", 123));
        assertEquals(123, testList.getFood(2).getCalorie());
        assertNotEquals(123, testList.getFood(3).getCalorie());
        assertEquals(226.1, testList.getTotalCalorie());

        assertTrue(testList.changeFoodCalorie(3, 232.3));
        assertEquals(232.3, testList.getFood(3).getCalorie());
        assertNotEquals(232.3, testList.getFood("A").getCalorie());
        assertEquals(456.4, testList.getTotalCalorie());
    }

    @Test
    void testGetTotalCalorie() {
        assertEquals(0, testList.getTotalCalorie());

        testList.addFood(foodB);
        testList.addFood(foodC);
        testList.addFood(foodA);

        assertEquals(101, testList.getTotalCalorie());

        testList.addFood(foodD);

        assertEquals(1335.1, testList.getTotalCalorie());
    }
}