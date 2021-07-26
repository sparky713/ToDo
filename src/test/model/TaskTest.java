package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Course cpsc210;
    Task quiz1;

    @BeforeEach
    public void runBefore() {
        cpsc210 = new Course("CPSC210", 9, 1, "Felix");
        quiz1 = new Task("Quiz 1", cpsc210, false);
        quiz1.setTaskDescription("Basics Quiz");
    }

    @Test
    public void testConstructor() {
        assertEquals("Basics Quiz", quiz1.getTaskDescription());
        assertEquals(cpsc210, quiz1.getCourse());
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
