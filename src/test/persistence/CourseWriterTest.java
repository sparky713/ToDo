package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class for CourseWriter
public class CourseWriterTest {

    @Test
    void testCourseWriterInvalidFile() {
        try {
            CourseList cl = new CourseList();
            CourseWriter cw = new CourseWriter("./data/my\12illegalFileName.json");
            cw.open();
            fail("expected IOException was not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testCourseWriterEmpty() {
        try {
            CourseList cl = new CourseList();
            CourseWriter writer = new CourseWriter("./data/test/testCourseWriterEmptyList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            CourseReader cr = new CourseReader("./data/test/testCourseWriterEmptyList.json");
            cl = cr.read();
            assertEquals(0, cl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testCourseWriterGeneral() {
        try {
            CourseList cl = new CourseList();
            cl.addCourse(new Course("CS110", "3:00PM", "4:30PM", "Gregor"));
            cl.addCourse(new Course("CS210", "9:30AM", "1:00PM", "Felix Grund"));
            CourseWriter writer = new CourseWriter("./data/test/testCourseWriterGeneral.json");
            writer.open();
            writer.write(cl);
            writer.close();

            CourseReader reader = new CourseReader("./data/test/testCourseWriterGeneral.json");
            cl = reader.read();
            List<Course> courses = cl.getCourses();
            assertEquals(2, courses.size());
            assertEquals("CS110", courses.get(0).getCode());
            assertEquals("Gregor", courses.get(0).getProfessor());
            assertEquals("3:00PM", courses.get(0).getStartTime());
            assertEquals("4:30PM", courses.get(0).getEndTime());

            assertEquals("CS210", courses.get(1).getCode());
            assertEquals("Felix Grund", courses.get(1).getProfessor());
            assertEquals("9:30AM", courses.get(1).getStartTime());
            assertEquals("1:00PM", courses.get(1).getEndTime());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
