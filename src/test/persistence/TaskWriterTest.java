package persistence;

import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test class for TaskWriter
public class TaskWriterTest {

    @Test
    void testTaskWriterInvalidFile() {
        try {
            TaskList tl = new TaskList();
            TaskWriter tw = new TaskWriter("./data/my\12illegalFileTester.json");
            tw.open();
            fail("IOException was expected but not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testTaskWriterEmpty() {
        try {
            TaskList tl = new TaskList();
            TaskWriter tw = new TaskWriter("./data/test/testTaskWriterEmptyList.json");
            tw.open();
            tw.write(tl);
            tw.close();

            TaskReader tr = new TaskReader("./data/test/testTaskWriterEmptyList.json");
            tl = tr.read();
            assertEquals(0, tl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testTaskWriterGeneral() {
        try {
            TaskList tl = new TaskList();
            tl.addTask(new Task("Lab 7", "Friday", true));
            tl.addTask(new Task("Lab 8", "Aug 4", false));;
            TaskWriter writer = new TaskWriter("./data/test/testTaskWriterGeneral.json");
            writer.open();
            writer.write(tl);
            writer.close();

            TaskReader reader = new TaskReader("./data/test/testTaskWriterGeneral.json");
            tl = reader.read();
            List<Task> tasks = tl.getTasks();
            assertEquals(2, tasks.size());
            assertEquals("Lab 7", tasks.get(0).getTaskDescription());
            assertEquals("Friday", tasks.get(0).getDueDate());
            assertTrue(tasks.get(0).getStatus());

            assertEquals("Lab 8", tasks.get(1).getTaskDescription());
            assertEquals("Aug 4", tasks.get(1).getDueDate());
            assertFalse(tasks.get(1).getStatus());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}

