package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test class for Course
class CourseTest {

    private Course cpsc210;

    @BeforeEach
    public void runBefore() {
        cpsc210 = new Course("CPSC210", "9:00AM", "1:00PM", "Felix");
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC210",cpsc210.getCode());
        assertEquals("9:00AM", cpsc210.getStartTime());
        assertEquals("1:00PM", cpsc210.getEndTime());
        assertEquals("Felix", cpsc210.getProfessor());
    }
}