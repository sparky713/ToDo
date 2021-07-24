package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    Date quiz1Due;

    @BeforeEach
    public void runBefore() {
        quiz1Due = new Date(2021, 7, 23);
    }

    @Test
    public void testConstructor() {
        assertEquals(2020,quiz1Due.getYear());
        assertEquals(7,quiz1Due.getMonth());
        assertEquals(23,quiz1Due.getDay());

    }
}
