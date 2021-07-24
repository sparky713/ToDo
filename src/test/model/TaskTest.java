package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Date quiz1DueDate;
    Course cpsc210;
    Task quiz1;

    @BeforeEach
    public void runBefore() {
        quiz1DueDate = new Date(2021, 7, 23);
        cpsc210 = new Course("CPSC210", 9, 1, "Felix");
        quiz1 = new Task("Quiz 1", cpsc210, quiz1DueDate, false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Quiz 1", quiz1.getTaskDescription());
        assertEquals(cpsc210, quiz1.getCourse());
        assertEquals(quiz1DueDate, quiz1.getDeadline());
        assertFalse(quiz1.getStatus());
    }


    @Test
    public void testSetToComplete() {
        assertFalse(quiz1.getStatus());
        quiz1.setToComplete();
        assertTrue(quiz1.getStatus());
    }
}
