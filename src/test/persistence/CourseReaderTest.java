package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class for CourseReader
public class CourseReaderTest {

    @Test
    void testCourseReaderNonExistentFile() {
        CourseReader cr = new CourseReader("./data/doesNotExist.json");
        try {
            CourseList cl = cr.read();
            fail("expected IOException was not thrown");
        } catch (IOException e) {
            // good
        }
    }

    @Test
    void testCourseReaderEmptyList() {
        CourseReader cr = new CourseReader("./data/test/testCourseReaderEmptyList.json");
        try {
            CourseList cl = cr.read();
            assertEquals(0, cl.getSize());
        } catch (IOException e) {
            fail("Error: Unable to read from file");
        }
    }

    @Test
    void testCourseReaderGeneral() {
        CourseReader cr = new CourseReader("./data/test/testCourseReaderGeneral.json.");
        try {
            CourseList cl = cr.read();
            List<Course> courses = cl.getCourses();
            assertEquals(2, courses.size());
            assertEquals();
            assertEquals("CS110", courses.get(0).getCode());
            assertEquals("Gregor", courses.get(0).getProfessor());
            assertEquals("3:00PM", courses.get(0).getStartTime());
            assertEquals("4:30PM", courses.get(0).getEndTime());

            assertEquals("CS210", courses.get(1).getCode());
            assertEquals("Felix Grund", courses.get(1).getProfessor());
            assertEquals("9:30AM", courses.get(1).getStartTime());
            assertEquals("1:00PM", courses.get(1).getEndTime());
        } catch (IOException e) {
            fail("Error: Unable to read from file");
        }
    }
}
