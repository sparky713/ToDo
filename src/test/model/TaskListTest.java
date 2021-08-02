package model;

import exceptions.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test class for TaskList
public class TaskListTest {

    TaskList toDoList;
    Course cs210;
    Task task1;
    Task task2;
    Task task3;


    @BeforeEach
    public void runBefore() {
        toDoList = new TaskList();
        cs210 = new Course("CS210", "9:00AM", "1:00PM", "Felix");
        task1 = new Task("1", "Monday", false);
        task2 = new Task("2", "Never", false);
        task3 = new Task("3", "2 days later", true);
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
        try {
            toDoList.addTask(task1);
            toDoList.addTask(task2);
            toDoList.addTask(task1);
            toDoList.addTask(task3);

            assertEquals(task1, toDoList.getTask("1"));
            assertEquals(task2, toDoList.getTask("2"));
            assertEquals(task3, toDoList.getTask("3"));

        } catch (TaskNotFoundException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testGetTaskNotFound() {

        try {
            toDoList.addTask(task1);
            toDoList.addTask(task2);
            toDoList.addTask(task3);

            assertEquals(3, toDoList.getSize());

            assertEquals(task1, toDoList.getTask("1"));
            assertEquals(task2, toDoList.getTask("2"));
            assertEquals(task3, toDoList.getTask("3"));

        } catch (TaskNotFoundException e) {
            fail("Exception should not have been thrown");
        }


        try {
            assertFalse(task2 == toDoList.getTask("6"));
            fail ("Expected TaskNotFoundException was not thrown");
        } catch (TaskNotFoundException e) {
            // good
        }
    }

    @Test
    public void testRemoveTaskOnce() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertEquals(3, toDoList.getSize());
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));

        toDoList.removeTask(task2);
        assertFalse(toDoList.contains(task2));
        assertEquals(2, toDoList.getSize());
    }

    @Test
    public void testRemoveTaskMultipleTimes() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task1);
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
        assertEquals(4, toDoList.getSize());

        toDoList.removeTask(task1);
        toDoList.removeTask(task2);

        assertTrue(toDoList.contains(task1));
        assertFalse(toDoList.contains(task2));
        assertEquals(2, toDoList.getSize());

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
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
        assertEquals(3, toDoList.getSize());

        toDoList.completeTask(task1);
        toDoList.completeTask(task2);

        try {
            assertTrue(toDoList.getTask("1").getStatus());
        } catch (TaskNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        try {
            assertTrue(toDoList.getTask("2").getStatus());
        } catch (TaskNotFoundException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testContains() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
        toDoList.removeTask(task3);
        assertEquals(2, toDoList.getSize());
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertFalse(toDoList.contains(task3));
    }


    @Test
    public void testIsEmptyFalse() {
        toDoList.addTask(task2);
        toDoList.addTask(task2);
        assertTrue(toDoList.contains(task2));
        toDoList.removeTask(task2);

        assertFalse(toDoList.isEmpty());
        assertTrue(toDoList.contains(task2));
    }

    @Test
    public void testIsEmptyTrue() {
        toDoList.addTask(task2);
        toDoList.addTask(task1);
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        toDoList.removeTask(task2);
        toDoList.removeTask(task1);

        assertTrue(toDoList.isEmpty());
        assertFalse(toDoList.contains(task1));
        assertFalse(toDoList.contains(task2));
    }

    @Test
    public void testGetTasks() {
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        assertTrue(toDoList.contains(task1));
        assertTrue(toDoList.contains(task2));
        assertTrue(toDoList.contains(task3));
        assertEquals(3, toDoList.getSize());

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        assertEquals(3, tasks.size());

        assertEquals(tasks, toDoList.getTasks());
    }


}
