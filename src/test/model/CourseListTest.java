package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for CourseList
public class CourseListTest {

    CourseList summer2021;
    Course cs210;
    Course eng110;
    Course cs121;

    @BeforeEach
    public void runBefore() {
        summer2021 = new CourseList();
        cs210 = new Course("CS210", "9:00AM", "1:00PM", "Felix");
        eng110 = new Course("ENGL110", "12:OOPM", "3:00PM", "Rebecca");
        cs121 = new Course("CS121", "3:00PM", "5:00PM", "Bart");
    }

    @Test
    public void testConstructor() {
        assertTrue(summer2021.isEmpty());
    }

    @Test
    public void testAddCourseJustOne() {
        summer2021.addCourse(cs210);
        assertTrue(summer2021.contains(cs210));
        assertEquals(1, summer2021.getSize());
        assertFalse(summer2021.isEmpty());
    }

    @Test
    public void testAddCourseMultipleTimes() {
        summer2021.addCourse(cs210);
        summer2021.addCourse(eng110);
        summer2021.addCourse(cs121);
        assertTrue(summer2021.contains(cs210));
        assertTrue(summer2021.contains(cs121));
        assertTrue(summer2021.contains(eng110));
        assertEquals(3, summer2021.getSize());
        assertFalse(summer2021.isEmpty());
    }

    @Test
    public void testRemoveCourseOneTime() {
        summer2021.addCourse(cs210);
        summer2021.addCourse(eng110);
        summer2021.addCourse(cs121);
        assertTrue(summer2021.contains(cs210));
        assertTrue(summer2021.contains(cs121));
        assertTrue(summer2021.contains(eng110));
        assertEquals(3, summer2021.getSize());

        summer2021.removeCourse(cs121);
        assertFalse(summer2021.contains(cs121));
        assertEquals(2, summer2021.getSize());
    }

    @Test
    public void testRemoveCourseMultipleTimes() {
        summer2021.addCourse(cs210);
        summer2021.addCourse(eng110);
        summer2021.addCourse(cs121);
        assertTrue(summer2021.contains(cs210));
        assertTrue(summer2021.contains(cs121));
        assertTrue(summer2021.contains(eng110));
        assertEquals(3, summer2021.getSize());

        summer2021.removeCourse(cs121);
        summer2021.removeCourse(eng110);
        summer2021.removeCourse(cs210);
        assertFalse(summer2021.contains(cs121));
        assertFalse(summer2021.contains(cs210));
        assertFalse(summer2021.contains(eng110));
        assertEquals(0, summer2021.getSize());
    }

    @Test
    public void testIsEmptyFalse() {
        summer2021.addCourse(cs210);
        summer2021.addCourse(eng110);
        summer2021.addCourse(cs121);
        summer2021.removeCourse(cs121);
        summer2021.removeCourse(eng110);

        assertFalse(summer2021.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() {
        summer2021.addCourse(cs210);
        summer2021.addCourse(eng110);
        summer2021.addCourse(cs121);
        summer2021.removeCourse(cs121);
        summer2021.removeCourse(eng110);
        summer2021.removeCourse(cs210);

        assertTrue(summer2021.isEmpty());
    }
}
