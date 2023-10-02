package model;

import model.entry.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightTest {
    private Weight testWeight;

    @BeforeEach
    void runBefore() {
        testWeight = new Weight(1, 2);
    }

    @Test
    void testConstructor() {
        assertEquals(1, testWeight.getWeight());
        assertEquals(2, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 2.0"), testWeight.viewWeight());
    }

    @Test
    void testSetWeight() {
        testWeight.setWeight(1.1);
        assertEquals(1.1, testWeight.getWeight());
        assertEquals(2, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1.1\n" + "Weight Goal: 2.0"), testWeight.viewWeight());

        testWeight.setWeight(1);
        assertEquals(1, testWeight.getWeight());
        assertEquals(2, testWeight.getWeightGoal());

        testWeight.setWeight(123122);
        assertEquals(123122, testWeight.getWeight());
        assertEquals(2, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 123122.0\n" + "Weight Goal: 2.0"), testWeight.viewWeight());

        testWeight.setWeight(1231.321);
        assertEquals(1231.321, testWeight.getWeight());
        assertEquals(2, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1231.321\n" + "Weight Goal: 2.0"), testWeight.viewWeight());
    }

    @Test
    void testSetWeightGoal() {
        testWeight.setWeightGoal(1);
        assertEquals(1, testWeight.getWeight());
        assertEquals(1, testWeight.getWeightGoal());

        testWeight.setWeightGoal(1.1);
        assertEquals(1, testWeight.getWeight());
        assertEquals(1.1, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 1.1"), testWeight.viewWeight());

        testWeight.setWeightGoal(123221);
        assertEquals(1, testWeight.getWeight());
        assertEquals(123221, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 123221.0"), testWeight.viewWeight());

        testWeight.setWeightGoal(3231.23);
        assertEquals(1, testWeight.getWeight());
        assertEquals(3231.23, testWeight.getWeightGoal());
        assertEquals(("Current Weight: 1.0\n" + "Weight Goal: 3231.23"), testWeight.viewWeight());
    }
}