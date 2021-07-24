package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    TaskList toDoList;
    Course cs210;
    Task task1;
    Task task2;
    Task task3;


    @BeforeEach
    public void runBefore() {
        toDoList = new TaskList();
        cs210 = new Course("CS210", 9, 1, "Felix");
        task1 = new Task("1", cs210, false);
        task2 = new Task("2", cs210, false);
        task3 = new Task("3", cs210, true);
    }

    @Test
    public void testConstructor() {

        assertTrue(toDoList.isEmpty());
    }

    @Test
    public void testAddTaskOnce() {
        toDoList.addTask(task1);
        assertTrue(toDoList.getSize() == 1);
        assertTrue(toDoList.contains(task1));
    }

    @Test
    public void testAddTaskMultipleTimes() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task1);
        toDoList.addTask(task3);
        assertFalse(toDoList.isEmpty());
        assertTrue(toDoList.getSize() == 4);
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
    }

    @Test
    public void testGetTaskFound() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task1);
        toDoList.addTask(task3);

        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));
    }

    @Test
    public void testGetTaskNotFound() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));

        assertFalse(task1 == toDoList.getTask("2"));
        assertEquals(null, toDoList.getTask("5"));
    }

    @Test
    public void testRemoveTaskOnce() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));
        assertEquals(3, toDoList.getSize());

        toDoList.removeTask(task2);
        assertEquals(null, toDoList.getTask("2"));
        assertEquals(2, toDoList.getSize());
    }

    @Test
    public void testRemoveTaskMultipleTimes() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task1);
        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));
        assertEquals(4, toDoList.getSize());

        toDoList.removeTask(task1);
        toDoList.removeTask(task2);
        assertEquals(null, toDoList.getTask("2"));
        assertEquals(2, toDoList.getSize());
        assertEquals(task1, toDoList.getTask("1"));

        toDoList.removeTask(task1);
        assertEquals(1, toDoList.getSize());
        assertTrue(toDoList.contains(task3));
        assertFalse(toDoList.contains(task2));
        assertFalse(toDoList.contains(task1));
    }

    @Test
    public void testCompleteTask() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));
        assertEquals(3, toDoList.getSize());

        toDoList.completeTask(task1);
        toDoList.completeTask(task2);
        assertTrue(toDoList.getTask("1").getStatus());
        assertTrue(toDoList.getTask("2").getStatus());
    }

    @Test
    public void testContainsTrue() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(3, toDoList.getSize());
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
    }

    @Test
    public void testContainsSomeContainsSomeNot() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(task1, toDoList.getTask("1"));
        assertEquals(task2, toDoList.getTask("2"));
        assertEquals(task3, toDoList.getTask("3"));
        assertEquals(3, toDoList.getSize());
        toDoList.removeTask(task1);
        toDoList.removeTask(task2);
        assertEquals(1, toDoList.getSize());
        assertEquals(task3, toDoList.getTask("3"));

        assertFalse(toDoList.contains(task1));
        assertFalse(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
    }



    @Test
    public void testIsEmptyFalse() {
        toDoList.addTask(task2);
        toDoList.addTask(task2);
        toDoList.removeTask(task2);

        assertFalse(toDoList.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() {
        toDoList.addTask(task2);
        toDoList.addTask(task1);
        toDoList.removeTask(task2);
        toDoList.removeTask(task1);

        assertTrue(toDoList.isEmpty());

    }
}
