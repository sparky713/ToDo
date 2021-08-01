package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for Task
public class TaskTest {

    Task quiz1;

    @BeforeEach
    public void runBefore() {
        quiz1 = new Task("", "", false);
        quiz1.setTaskDescription("Basics Quiz");
        quiz1.setDueDate("Tomorrow");
    }

    @Test
    public void testConstructor() {
        assertEquals("Basics Quiz", quiz1.getTaskDescription());
        assertEquals("Tomorrow", quiz1.getDueDate());
        assertFalse(quiz1.getStatus());
    }


    @Test
    public void testSetToCompleteNotCompleteToComplete() {
        assertFalse(quiz1.getStatus());
        quiz1.setToComplete();
        assertTrue(quiz1.getStatus());
    }

    @Test
    public void testSetToCompleteCompleteToNotComplete() {
        assertFalse(quiz1.getStatus());
        quiz1.setToComplete();
        quiz1.setToComplete();
        assertFalse(quiz1.getStatus());
    }
}
