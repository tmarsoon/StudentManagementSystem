package roles;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import courses.Course;

public class StudentTest {
    private Student student;
    
    @Before
    public void setUp() {
        // Initialize a student object for testing
        student = new Student("12345", "Tony Marsalla", "tonymarsalla", "password", new ArrayList<>());
    }
    @Test
    public void testGetStudentId() {
        assertEquals("12345", student.getStudentId());
    }

    @Test
    public void testGetStudentName() {
        assertEquals("Tony Marsalla", student.getStudentName());
    }

    @Test
    public void testGetStudentUsername() {
        assertEquals("tonymarsalla", student.getStudentUsername());
    }

    @Test
    public void testGetStudentPassword() {
        assertEquals("password", student.getStudentPassword());
    }

    @Test
    public void testEnrollStudent() {
        // Create another student
        Student anotherStudent = new Student("67890", "William Smith", "willsmith", "password", new ArrayList<>());
        
        // Enroll another student
        student.enrollStudent(anotherStudent);
        
        // Check if the student is enrolled
        assertTrue(student.getEnrolledStudents().contains(anotherStudent));
    }

    @Test
    public void testUnenrollStudent() {
        // Create another student
        Student anotherStudent = new Student("18497", "Andrew Garfield", "andrewgarfield", "password", new ArrayList<>());
        
        // Enroll another student
        student.enrollStudent(anotherStudent);
        
        // Unenroll the student
        student.unenrollStudent(anotherStudent);
        
        // Check if the student is unenrolled
        assertFalse(student.getEnrolledStudents().contains(anotherStudent));
    }

    @Test
    public void testEnrollInCourse() {
        // Create a course
        Course course = new Course("CIS101", "Introduction to Programming", "Lecturer Name", "Mon-Wed-Fri", "9:00 AM", "10:30 AM", "30");
        
        
        // Enroll in a course
        student.enrollInCourse(course);
        
        // Check if the student is enrolled in the course
        assertTrue(student.isEnrolledInCourse(course));
    }

    @Test
    public void testUnenrollFromCourse() {
        // Create a course
        Course course = new Course("CIS101", "Introduction to Programming", "Lecturer Name", "Mon-Wed-Fri", "9:00 AM", "10:30 AM", "30");
        
        // Enroll in a course
        student.enrollInCourse(course);
        
        // Unenroll from the course
        student.unenrollFromCourse(course);
        
        // Check if the student is unenrolled from the course
        assertFalse(student.isEnrolledInCourse(course));
    }

    @Test
    public void testAddAndGetCourseGrade() {
        // Create a course grade
        Student.CourseGrade courseGrade = new Student.CourseGrade("12345", "CIS101", "Introduction to Programming", "A");
        
        // Add the course grade
        student.addCourseGrade(courseGrade);
        
        // Get the course grade
        Student.CourseGrade retrievedGrade = student.getCourseGrades().get(0);
        
        // Check if the retrieved grade matches the added grade
        assertEquals(courseGrade, retrievedGrade);
    }
    @Test
    public void testAddCourseToSuccessfullyAdded() {
        // Create a course
        Course course = new Course("CIS101", "Introduction to Programming", "Lecturer Name", "Mon-Wed-Fri", "9:00 AM", "10:30 AM", "30");
        
        // Add the course to successfully added courses
        student.addCourseToSuccessfullyAdded(course);
        
        // Check if the course is in the list of successfully added courses
        assertTrue(student.hasCourseSuccessfullyAdded(course));
    }
    @Test
    public void testGetSuccessfullyAddedCourses() {
        // Create a list of courses
        Course course1 = new Course("CIS101", "Introduction to Programming", "Lecturer Name", "Mon-Wed-Fri", "9:00 AM", "10:30 AM", "30");
        Course course2 = new Course("CIS201", "Data Structures", "Lecturer Name", "Tue-Wed-Thu", "11:00 AM", "12:30 PM", "70");
        
        // Add courses to successfully added courses
        student.addCourseToSuccessfullyAdded(course1);
        student.addCourseToSuccessfullyAdded(course2);
        
        // Get the list of successfully added courses
        List<Course> successfullyAddedCourses = student.getSuccessfullyAddedCourses();
        
        // Check if the list contains the added courses
        assertTrue(successfullyAddedCourses.contains(course1));
        assertTrue(successfullyAddedCourses.contains(course2));
    }
    @Test
    public void testAddCourseGrade() {
        // Create a CourseGrade instance
        Student.CourseGrade courseGrade = new Student.CourseGrade("12345", "CIS101", "Intro to Programming", "A");

        // Add the course grade to the student
        student.addCourseGrade(courseGrade);

        // Check if the course grade is in the list of course grades
        assertTrue(student.getCourseGrades().contains(courseGrade));
    }

    @Test
    public void testGetGradeForStudent() {
        // Create a CourseGrade instance
        Student.CourseGrade courseGrade = new Student.CourseGrade("12345", "CIS101", "Intro to Programming", "A");

        // Add the course grade to the student
        student.addCourseGrade(courseGrade);

        // Check if the student's grade for a specific course is as expected
        assertEquals("A", student.getGradeForStudent("12345"));
    }

    @Test
    public void testGetStudentCourseGrades() {
        // Create CourseGrade instances
        Student.CourseGrade courseGrade1 = new Student.CourseGrade("12345", "CIS101", "Intro to Programming", "A");
        Student.CourseGrade courseGrade2 = new Student.CourseGrade("12345", "CIS102", "Advanced Programming", "B");

        // Add the course grades to the student
        student.addCourseGrade(courseGrade1);
        student.addCourseGrade(courseGrade2);

        // Check if the list of course grades for the student contains both grades
        assertEquals(2, student.getStudentCourseGrades("12345").size());
    }
}
