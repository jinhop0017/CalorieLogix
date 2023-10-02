package model;

import model.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food testFood;

    @BeforeEach
    void runBefore() {
        testFood = new Food("testFood", 100);
    }

    @Test
    void testConstructor() {
        assertEquals("testFood", testFood.getName());
        assertEquals(100, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 100.0"), testFood.viewFood());
    }

    @Test
    void testSetCalorie() {
        testFood.setCalorie(1);
        assertEquals(1, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 1.0"), testFood.viewFood());

        testFood.setCalorie(2);
        assertEquals(2, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 2.0"), testFood.viewFood());

        testFood.setCalorie(100);
        assertEquals(100, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 100.0"), testFood.viewFood());

        testFood.setCalorie(100000);
        assertEquals(100000, testFood.getCalorie());

        testFood.setCalorie(1.1);
        assertEquals(1.1, testFood.getCalorie());

        testFood.setCalorie(100.1312);
        assertEquals(100.1312, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 100.1312"), testFood.viewFood());

        testFood.setCalorie(10000.13123);
        assertEquals(10000.13123, testFood.getCalorie());
        assertEquals(("Food Name: testFood\n" + "Calories: 10000.13123"), testFood.viewFood());
    }

    @Test
    void testSetName() {
        testFood.setName("");
        assertEquals("", testFood.getName());
        assertEquals(("Food Name: \n" + "Calories: 100.0"), testFood.viewFood());

        testFood.setName("a");
        assertEquals("a", testFood.getName());
        assertEquals(("Food Name: a\n" + "Calories: 100.0"), testFood.viewFood());

        testFood.setName("Coffee");
        assertEquals("Coffee", testFood.getName());
        assertEquals(("Food Name: Coffee\n" + "Calories: 100.0"), testFood.viewFood());

        testFood.setName("Mocha Ice Cream");
        assertEquals("Mocha Ice Cream", testFood.getName());
        assertEquals(("Food Name: Mocha Ice Cream\n" + "Calories: 100.0"), testFood.viewFood());
    }
}