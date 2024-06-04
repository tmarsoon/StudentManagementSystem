package roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    void setUp() {
        // Initialize some sample courses for testing
        course1 = new Course("CIT590", "Programming", "Professor A", "Mon", "09:00", "10:30", "50");
        course2 = new Course("CIT591", "Software Dev", "Professor B", "Tue", "10:00", "11:30", "60");
        course3 = new Course("CIT592", "Data Structures", "Professor A", "Mon", "09:00", "10:30", "70");
    }

    @Test
    void testGetCourseId() {
        assertEquals("CIT590", course1.getCourseId());
        assertEquals("CIT591", course2.getCourseId());
    }

    @Test
    void testGetCourseName() {
        assertEquals("Programming", course1.getCourseName());
        assertEquals("Software Dev", course2.getCourseName());
    }

    @Test
    void testGetLecturer() {
        assertEquals("Professor A", course1.getLecturer());
        assertEquals("Professor B", course2.getLecturer());
    }

    @Test
    void testGetDays() {
        assertEquals("Mon", course1.getDays());
        assertEquals("Tue", course2.getDays());
    }

    @Test
    void testGetStartTime() {
        assertEquals("09:00", course1.getStartTime());
        assertEquals("10:00", course2.getStartTime());
    }

    @Test
    void testGetEndTime() {
        assertEquals("10:30", course1.getEndTime());
        assertEquals("11:30", course2.getEndTime());
    }

    @Test
    void testGetCapacity() {
        assertEquals("50", course1.getCapacity());
        assertEquals("60", course2.getCapacity());
    }

    @Test
    void testHasTimeConflict() {
        // course1 and course3 have the same day and overlapping time, so there should be a conflict
        assertTrue(course1.hasTimeConflict(course3));
        // course1 and course2 have different days and no overlapping time, so there should be no conflict
        assertFalse(course1.hasTimeConflict(course2));
    }

    @Test
    void testEnrollmentCount() {
        assertEquals(0, course1.getEnrollmentCount());
        course1.incrementEnrollmentCount();
        assertEquals(1, course1.getEnrollmentCount());
        course1.decrementEnrollmentCount();
        assertEquals(0, course1.getEnrollmentCount());
    }
}