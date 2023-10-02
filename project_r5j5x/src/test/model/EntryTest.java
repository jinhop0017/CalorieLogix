package model;

import model.entry.Date;
import model.entry.Entry;
import model.food.Food;
import model.food.ListOfFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {
    private Entry testEntry;
    private ListOfFood testList;

    private Food foodA;
    private Food foodB;
    private Food foodC;
    private Food foodD;

    @BeforeEach
    void runBefore() {
        testEntry = new Entry(2004, 9, 3, 1, 2);

        testList = new ListOfFood();

        foodA = new Food("A", 0);
        foodB = new Food("B", 1);
        foodC = new Food("C", 100);
        foodD = new Food("D", 1234.1);
    }

    @Test
    void testConstructor() {
        assertTrue(testEntry.getListOfFood().isEmpty());
        assertEquals(2004, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());
        assertEquals(1, testEntry.getWeight());
        assertEquals(2, testEntry.getWeightGoal());

        assertEquals("A", foodA.getName());
        assertEquals(0, foodA.getCalorie());

        assertEquals("B", foodB.getName());
        assertEquals(1, foodB.getCalorie());

        assertEquals("C", foodC.getName());
        assertEquals(100, foodC.getCalorie());

        assertEquals("D", foodD.getName());
        assertEquals(1234.1, foodD.getCalorie());

        assertEquals(2004, testEntry.getDate().getYear());
        assertEquals(9, testEntry.getDate().getMonth());
        assertEquals(3, testEntry.getDate().getDay());

        assertEquals(1, testEntry.getObjWeight().getWeight());
        assertEquals(2, testEntry.getObjWeight().getWeightGoal());
    }

    @Test
    void testAddFood() {
        testEntry.addFood(foodA);

        assertEquals(1, testEntry.getListOfFood().size());
        assertTrue(testEntry.getListOfFood().contains(foodA));
        assertEquals(0, testEntry.getTotalCalorie());

        testEntry.addFood(foodB);
        testEntry.addFood(foodC);

        assertEquals(101, testEntry.getTotalCalorie());
        assertEquals(3, testEntry.getListOfFood().size());
        assertTrue(testEntry.getListOfFood().contains(foodA));
        assertTrue(testEntry.getListOfFood().contains(foodB));
        assertTrue(testEntry.getListOfFood().contains(foodC));

        testEntry.addFood(foodA);

        assertEquals(4, testEntry.getListOfFood().size());
        assertTrue(testEntry.getListOfFood().contains(foodA));
        assertTrue(testEntry.getListOfFood().contains(foodB));
        assertTrue(testEntry.getListOfFood().contains(foodC));
        assertTrue(testEntry.getListOfFood().contains(foodA));

        assertTrue(testEntry.getObjLof().getListOfFood().contains(foodA));
        assertTrue(testEntry.getObjLof().getListOfFood().contains(foodB));
        assertTrue(testEntry.getObjLof().getListOfFood().contains(foodC));

        testEntry.addFood("FoodA", 123);
        assertEquals(123, testEntry.getFood("FoodA").getCalorie());
    }

    @Test
    void testRemoveFood() {
        testEntry.addFood(foodA);
        testEntry.addFood(foodB);
        testEntry.addFood(foodC);

        assertEquals(3, testEntry.getListOfFood().size());
        assertTrue(testEntry.getListOfFood().contains(foodA));
        assertTrue(testEntry.getListOfFood().contains(foodB));
        assertTrue(testEntry.getListOfFood().contains(foodC));

        assertFalse(testEntry.removeFood("D"));
        assertTrue(testEntry.removeFood("A"));

        assertEquals(2, testEntry.getListOfFood().size());
        assertFalse(testEntry.getListOfFood().contains(foodA));
        assertTrue(testEntry.getListOfFood().contains(foodB));
        assertTrue(testEntry.getListOfFood().contains(foodC));

        assertFalse(testEntry.removeFood(2));
        assertFalse(testEntry.removeFood(-1));
        assertFalse(testEntry.removeFood(100));
        assertTrue(testEntry.removeFood(1));

        assertEquals(1, testEntry.getListOfFood().size());
        assertTrue(testEntry.getListOfFood().contains(foodB));
        assertFalse(testEntry.getListOfFood().contains(foodC));
    }

    @Test
    void testGetFood() {
        Food foodB_1 = new Food("B", 2);

        testEntry.addFood(foodB);
        testEntry.addFood(foodA);
        testEntry.addFood(foodB_1);
        testEntry.addFood(foodC);

        assertEquals(103, testEntry.getTotalCalorie());
        assertEquals(foodB, testEntry.getFood("B"));
        assertEquals(foodB, testEntry.getFood(0));
        assertNotEquals(foodB_1, testEntry.getFood(0));

        assertNull(testEntry.getFood("BC"));
        assertNull(testEntry.getFood(4));

        assertEquals(foodA, testEntry.getFood("A"));
        assertEquals(foodA, testEntry.getFood(1));

        assertEquals(foodC, testEntry.getFood("C"));
        assertEquals(foodC, testEntry.getFood(3));

        assertEquals(foodB_1, testEntry.getFood(2));
    }

    @Test
    void testChangeFoodName() {
        Food foodC_1 = new Food("C", 2);

        testEntry.addFood(foodB);
        testEntry.addFood(foodC);
        testEntry.addFood(foodA);
        testEntry.addFood(foodC_1);

        double testCalorie = testEntry.getFood("B").getCalorie();

        assertTrue(testEntry.changeFoodName("B", "D"));

        assertNull(testEntry.getFood("B"));
        assertEquals("D", testEntry.getFood("D").getName());
        assertEquals(testCalorie, testEntry.getFood("D").getCalorie());
        assertTrue(testEntry.changeFoodName("D", "B"));

        assertTrue(testEntry.changeFoodName(0, "D"));
        assertFalse(testEntry.changeFoodName(-1, "D"));
        assertFalse(testEntry.changeFoodName(4, "D"));
        assertFalse(testEntry.changeFoodName(100, "D"));

        assertNull(testEntry.getFood("B"));
        assertEquals("D", testEntry.getFood("D").getName());
        assertEquals(testCalorie, testEntry.getFood("D").getCalorie());

        testCalorie = testEntry.getFood("C").getCalorie();
        assertTrue(testEntry.changeFoodName("C", "Z"));

        assertEquals("Z", testEntry.getFood("Z").getName());
        assertEquals(testCalorie, testEntry.getFood("Z").getCalorie());

        assertEquals("C", testEntry.getFood("C").getName());
        assertNotEquals(testCalorie, testEntry.getFood("C").getCalorie());

        assertTrue(testEntry.changeFoodName("Z", "C"));

        testCalorie = testEntry.getFood(3).getCalorie();
        assertTrue(testEntry.changeFoodName(3, "Z"));

        assertEquals("C", testEntry.getFood(1).getName());
    }

    @Test
    void testChangeFoodCalorie() {
        Food foodA_1 = new Food("A", 2);

        testEntry.addFood(foodC);
        testEntry.addFood(foodB);
        testEntry.addFood(foodA);
        testEntry.addFood(foodA_1);

        assertTrue(testEntry.changeFoodCalorie("C", 1));

        assertEquals(1, testEntry.getFood(0).getCalorie());

        assertFalse(testEntry.changeFoodCalorie("Z", 1));
        assertFalse(testEntry.changeFoodCalorie("Z", 1000));
        assertFalse(testEntry.changeFoodCalorie("Z", 10.1));
        assertFalse(testEntry.changeFoodCalorie(4, 1));
        assertFalse(testEntry.changeFoodCalorie(12, 1231));

        assertTrue(testEntry.changeFoodCalorie(0, 100.1));
        assertEquals(103.1, testEntry.getTotalCalorie());

        assertEquals(100.1, testEntry.getFood(0).getCalorie());

        assertTrue(testEntry.changeFoodCalorie("A", 123));
        assertEquals(123, testEntry.getFood(2).getCalorie());
        assertNotEquals(123, testEntry.getFood(3).getCalorie());
        assertEquals(226.1, testEntry.getTotalCalorie());

        assertTrue(testEntry.changeFoodCalorie(3, 232.3));
        assertEquals(232.3, testEntry.getFood(3).getCalorie());
        assertNotEquals(232.3, testEntry.getFood("A").getCalorie());
        assertEquals(456.4, testEntry.getTotalCalorie());
    }

    @Test
    void testSetYear() {
        testEntry.setYear(0);
        assertEquals(0, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());

        testEntry.setYear(1);
        assertEquals(1, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());

        testEntry.setYear(2000);
        assertEquals(2000, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());

        testEntry.setYear(2123123);
        assertEquals(2123123, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());
    }

    @Test
    void testSetMonth() {
        testEntry.setMonth(1);
        assertEquals(2004, testEntry.getYear());
        assertEquals(1, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());

        testEntry.setMonth(12);
        assertEquals(2004, testEntry.getYear());
        assertEquals(12, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());

        testEntry.setMonth(7);
        assertEquals(2004, testEntry.getYear());
        assertEquals(7, testEntry.getMonth());
        assertEquals(3, testEntry.getDay());
    }

    @Test
    void testSetDay() {
        testEntry.setDay(1);
        assertEquals(2004, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(1, testEntry.getDay());

        testEntry.setDay(31);
        assertEquals(2004, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(31, testEntry.getDay());

        testEntry.setDay(12);
        assertEquals(2004, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(12, testEntry.getDay());

        testEntry.setDay(21);
        assertEquals(2004, testEntry.getYear());
        assertEquals(9, testEntry.getMonth());
        assertEquals(21, testEntry.getDay());
    }

    @Test
    void testSetWeight() {
        testEntry.setWeight(1.1);
        assertEquals(1.1, testEntry.getWeight());
        assertEquals(2, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 1.1\n" + "Weight Goal: 2.0"), testEntry.viewWeight());

        testEntry.setWeight(1);
        assertEquals(1, testEntry.getWeight());
        assertEquals(2, testEntry.getWeightGoal());

        testEntry.setWeight(123122);
        assertEquals(123122, testEntry.getWeight());
        assertEquals(2, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 123122.0\n" + "Weight Goal: 2.0"), testEntry.viewWeight());

        testEntry.setWeight(1231.321);
        assertEquals(1231.321, testEntry.getWeight());
        assertEquals(2, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 1231.321\n" + "Weight Goal: 2.0"), testEntry.viewWeight());
    }

    @Test
    void testSetWeightGoal() {
        testEntry.setWeightGoal(1);
        assertEquals(1, testEntry.getWeight());
        assertEquals(1, testEntry.getWeightGoal());

        testEntry.setWeightGoal(1.1);
        assertEquals(1, testEntry.getWeight());
        assertEquals(1.1, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 1.1"), testEntry.viewWeight());

        testEntry.setWeightGoal(123221);
        assertEquals(1, testEntry.getWeight());
        assertEquals(123221, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 123221.0"), testEntry.viewWeight());

        testEntry.setWeightGoal(3231.23);
        assertEquals(1, testEntry.getWeight());
        assertEquals(3231.23, testEntry.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 3231.23"), testEntry.viewWeight());
    }

    @Test
    void testGetTotalCalorie() {
        assertEquals(0, testEntry.getTotalCalorie());

        testEntry.addFood(foodB);
        testEntry.addFood(foodC);
        testEntry.addFood(foodA);

        assertEquals(101, testEntry.getTotalCalorie());

        testEntry.addFood(foodD);

        assertEquals(1335.1, testEntry.getTotalCalorie());
    }
}