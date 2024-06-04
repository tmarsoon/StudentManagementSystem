package roles;

import java.util.ArrayList;
import java.util.List;
import courses.Course;



public class Student {

	private String studentId;
	private String studentName;
	private String studentUsername;
	private String studentPassword;


	private List<Student> enrolledStudents = new ArrayList<>();
	private List<CourseGrade> courseGrades = new ArrayList<>();
	private List<Course> selectedCourses = new ArrayList<>();
	private List<Course> successfullyAddedCourses = new ArrayList<>();
	
	public Student(String studentId, String studentName, String studentUsername, String studentPassword,List<Course> courses ) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentUsername = studentUsername;
		this.studentPassword = studentPassword;
		 this.selectedCourses = courses;
	}
	public String getStudentId() {
		return studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public String getStudentUsername() {
		return studentUsername;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	  public List<Student> getEnrolledStudents() {
	        return enrolledStudents;
	    }
	  /**
	   * Enrolls a student in a course.
	   *
	   * @param student The student to be enrolled in the course.
	   */
	  public void enrollStudent(Student student) {
	        enrolledStudents.add(student);
	    }
	  /**
	   * Unenrolls a student from a course.
	   *
	   * @param student The student to be unenrolled from the course.
	   */
	    public void unenrollStudent(Student student) {
	        enrolledStudents.remove(student);
	    }
	    /**
	     * Gets the grade for a specific student in a course.
	     *
	     * @param studentId The unique identifier of the student.
	     * @return The grade of the student in the course, or null if the student is not found.
	     */
	    public String getGradeForStudent(String studentId) {
	        for (CourseGrade courseGrade : courseGrades) {
	            if (courseGrade.getStudentId().equalsIgnoreCase(studentId)) {
	                return courseGrade.getGrade();
	            }
	        }
	        return null; // Student not found
	    }
	    /**
	     * Gets a list of course grades for a specific student.
	     *
	     * @param studentId The unique identifier of the student.
	     * @return A list of course grades for the specified student.
	     */
	    public List<CourseGrade> getStudentCourseGrades(String studentId) {
	        List<CourseGrade> studentGrades = new ArrayList<>();
	        for (CourseGrade courseGrade : courseGrades) {
	            if (courseGrade.getStudentId().equalsIgnoreCase(studentId)) {
	                studentGrades.add(courseGrade);
	            }
	        }
	        return studentGrades;
	    }
	    /**
	     * Gets a list of all course grades for all students.
	     *
	     * @return A list of all course grades.
	     */
	    public List<CourseGrade> getCourseGrades() {
	        return courseGrades;
	    }

	    /**
	     * Adds a course grade to the student's record.
	     *
	     * @param courseGrade The course grade to be added.
	     */
	    public void addCourseGrade(CourseGrade courseGrade) {
	        courseGrades.add(courseGrade);
	    }
	    /**

	     * represents a course grade for a student.
	     */
	    public static class CourseGrade {
	        private String studentId;
	        private String courseId;
	        private String courseName;
	        private String grade;
	        
	        /**
	         * Constructs a course grade for a student.
	         * @param studentId  The unique identifier of the student.
	         * @param   courseId The unique identifier of the course.
	         * @param courseName The name of the course.
	         * @param  grade      The grade achieved by the student in the course.
	         */
	        public CourseGrade(String studentId, String courseId, String courseName, String grade) {
	            this.studentId = studentId;
	            this.courseId = courseId;
	            this.courseName = courseName;
	            this.grade = grade;
	        }
	        /**
	         * Gets the student's id associated with this course grade.
	         *
	         * @return The student's id.
	         */
	        public String getStudentId() {
	            return studentId;
	        }
	        /**
	         * Sets the id of the student associated with this course grade.
	         *
	         * @param studentId The student's id to be set.
	         */
	        public void setStudentId(String studentId) {
	            this.studentId = studentId;
	        }
	        /**
	         * Gets the id fromcourse associated with this grade.
	         * @return The id of the course.
	         */
	        public String getCourseId() {
	            return courseId;
	        }
	        /**
	         * Sets the id of the course associated with this grade.
	         * @param courseId The course's id to be set.
	         */
	        public void setCourseId(String courseId) {
	            this.courseId = courseId;
	        }
	        
	        
	        /**
	         *Gets the name of the course associated with this grade.
	         
	         * @return the name of the course
	         */
	        public String getCourseName() {
	            return courseName;
	        }
	        /**
	         * Sets the name of the course associated with grade
	         * @param courseName The name of the course to be set.
	         */
	        public void setCourseName(String courseName) {
	            this.courseName = courseName;
	        }
	        /**
	         * Gets the grade of student fromm this specific course. 
	         *
	         * @return The grade achieved.
	         */
	        public String getGrade() {
	            return grade;
	        }
	        
	        /**
	         * Sets the grade achieved by the student in this course.	         
	         * @param grade  The grade to be set
	         */
	        public void setGrade(String grade) {
	            this.grade = grade;
	        }
	    }
	    /**
	     * Retrieves a list of courses that the student has selected.
	     *
	     * @return A list of courses selected by the student */
	     
	    public List<Course> getSelectedCourses() {
	        
	        // Iterate through the enrolled students to find the current student
	        for (Student enrolledStudent : enrolledStudents) {
	            if (enrolledStudent.getStudentId().equals(this.getStudentId())) {
	                // Found the current student, now retrieve their selected courses
	                for (Course course : selectedCourses) {
	                    if (enrolledStudent.isEnrolledInCourse(course)) {
	                        selectedCourses.add(course);
	                    }
	                }
	                break; // No need to continue searching
	            }
	        }
	        
	        return selectedCourses;
	    }
	  
	    /**
	     * Enrolls the student in a course.
	     *
	     * @param course  course to be enrolled
	     */
	    public void enrollInCourse(Course course) {
	        selectedCourses.add(course);
	    }
	    /**
	     * Unenrolls the student from a course.
	     *
	     * @param course  The course to be unenrolled from.
	     */
	    public void unenrollFromCourse(Course course) {
	        selectedCourses.remove(course);
	    }
	    /**
	     * Checks if the student is enrolled in a specific course.
	     *
	     * @param course The course to check for enrollment.
	     * @return True if the student is enrolled in the course, false otherwise.
	     */
	    public boolean isEnrolledInCourse(Course course) {
	        return selectedCourses.contains(course);
	    }
	    /**
	     * Adds a course to the list of successfully added courses for the student.
	     * @param course The course to be added as successfully added.
	     */
	    public void addCourseToSuccessfullyAdded(Course course) {
	        successfullyAddedCourses.add(course);
	    }
	    
	    /**
	     *etrieves a list of courses that the student has successfully added.
	      @return A list of courses successfully added by the student.
	     */
	    public List<Course> getSuccessfullyAddedCourses() {
	        return successfullyAddedCourses;
	    }
	    /**
	     * Removes a course from the list of successfully added courses for the student.
	     * @param course The course to be removed from the list of successfully added courses.
	     */
	    public void removeCourseFromSuccessfullyAdded(Course course) {
	        successfullyAddedCourses.remove(course);
	    }
	    /**
	     * Checks if the student has successfully added a specific course.
	     
	     * @param course The course to check for successful addition.
	     * @return True if the student has successfully added the course, false otherwise.
	     */
	    public boolean hasCourseSuccessfullyAdded(Course course) {
	        return successfullyAddedCourses.contains(course);
	    }
	    
	}
	
	    

	

