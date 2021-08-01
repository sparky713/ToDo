package persistence;

import model.Course;
import model.CourseList;
import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

// test class for TaskReader
public class TaskReaderTest {

    @Test
    void testTaskReaderNonExistentFile() {
        TaskReader tr = new TaskReader("./data/doesNotExist.json");
        try {
            TaskList tl = tr.read();
            fail("expected IOException was not thrown");
        } catch (IOException e) {
            // good
        }
    }

    @Test
    void testTaskReaderEmptyList() {
        TaskReader tr = new TaskReader("./data/test/testTaskReaderEmptyList.json");
        try {
            TaskList tl = tr.read();
            assertEquals(0, tl.getSize());
        } catch (IOException e) {
            fail("Error: Unable to read from file");
        }
    }

    @Test
    void testTaskReaderGeneral() {
        TaskReader cr = new TaskReader("./data/test/testTaskReaderGeneral.json.");
        try {
            TaskList tl = cr.read();
            List<Task> tasks = tl.getTasks();
            assertEquals(2, tasks.size());
            assertEquals("Lab 7", tasks.get(0).getTaskDescription());
            assertEquals("Friday", tasks.get(0).getDueDate());
            assertTrue(tasks.get(0).getStatus());

            assertEquals("Lab 8", tasks.get(1).getTaskDescription());
            assertEquals("Aug 4", tasks.get(1).getDueDate());
            assertFalse(tasks.get(1).getStatus());
        } catch (IOException e) {
            fail("Error: Unable to read from file");
        }
    }
}


