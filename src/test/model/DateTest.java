package model;

import model.entry.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    private Date testDate;

    @BeforeEach
    void runBefore() {
        testDate = new Date(2004, 9, 3);
    }

    @Test
    void testConstructor() {
        assertEquals(2004, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(3, testDate.getDay());
    }

    @Test
    void testSetYear() {
        testDate.setYear(0);
        assertEquals(0, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(3, testDate.getDay());
        assertEquals("0/9/3", testDate.viewDate());

        testDate.setYear(1);
        assertEquals(1, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(3, testDate.getDay());

        testDate.setYear(2000);
        assertEquals(2000, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(3, testDate.getDay());
        assertEquals("2000/9/3", testDate.viewDate());

        testDate.setYear(2123123);
        assertEquals(2123123, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(3, testDate.getDay());
        assertEquals("2123123/9/3", testDate.viewDate());
    }

    @Test
    void testSetMonth() {
        testDate.setMonth(1);
        assertEquals(2004, testDate.getYear());
        assertEquals(1, testDate.getMonth());
        assertEquals(3, testDate.getDay());

        testDate.setMonth(12);
        assertEquals(2004, testDate.getYear());
        assertEquals(12, testDate.getMonth());
        assertEquals(3, testDate.getDay());
        assertEquals("2004/12/3", testDate.viewDate());

        testDate.setMonth(7);
        assertEquals(2004, testDate.getYear());
        assertEquals(7, testDate.getMonth());
        assertEquals(3, testDate.getDay());
    }

    @Test
    void testSetDay() {
        testDate.setDay(1);
        assertEquals(2004, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(1, testDate.getDay());
        assertEquals("2004/9/1", testDate.viewDate());

        testDate.setDay(31);
        assertEquals(2004, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(31, testDate.getDay());

        testDate.setDay(12);
        assertEquals(2004, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(12, testDate.getDay());
        assertEquals("2004/9/12", testDate.viewDate());

        testDate.setDay(21);
        assertEquals(2004, testDate.getYear());
        assertEquals(9, testDate.getMonth());
        assertEquals(21, testDate.getDay());
    }
}