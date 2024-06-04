package roles;



import java.util.ArrayList;
import java.util.List;


import courses.Course;



public class Professor {
	
	// instance variables
	private String professorName;
	private String professorID;
	private String professorUsername;
	private String professorPassword;
	

	 /**
     * Constructor for creating a Professor object with the provided information.
     *
     * @param name      The name of the professor.
     * @param id        The unique ID of the professor.
     * @param username  The professor's username for authentication.
     * @param password  The professor's password for authentication.
     */
	public Professor(String name, String id, String username, String password) {
		this.professorName = name;
		this.professorID = id;
		this.professorUsername = username;
		this.professorPassword = password;
		
	}
	
	 /**
     * Returning the name of the professor
     *
     * @return The name of the professor.
     */
	public String getProfessorName() {
		return this.professorName;
	}
		/**
	     * ID of the professor.
	     *
	     * @return id
	     */
	public String getProfessorID() {
		return this.professorID;
	}

    /**
     * Sets the name of the professor
     
     * @param name The new name of the professor as a String.
     */
	public void setProfessorName(String name) {
		this.professorName = name;
	}
	
	  /**
     * Sets id of  professor.
     
     * @param professorID The new ID of the professor
     */
	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}
	/**
     * Returning the username of the professor.
     * @return The professor's username.
     */
	public String getProfessorUsername() {
		return this.professorUsername;
	}
	 /**
     * Returns the password of the professor.
     
     * @return The professor's password for authentication.
     */
	public String getProfessorPassword() {
		return this.professorPassword;
	}
	
	 /**
     * Prints out the courses taught by the professor.
     *
     * @param courses A List of Course objects representing all available courses.
     */
	public void viewGivenCourses(List<Course> courses) {
		List<Course> taughtCoursesList = new ArrayList<>();
		// iterate through list of Course objects
		// for each Course in the list of all courses:
		for (int i=0; i<courses.size(); i++) {
			// if the professor for the course has the name of this professor, add it to taughtCoursesList
			if (courses.get(i).getLecturer().equals(this.getProfessorName())) {
				taughtCoursesList.add(courses.get(i));
			}
		}
		
		System.out.println("----------------The course list----------------");
		// loop through the taught courses
		for (int i=0; i<taughtCoursesList.size(); i++ ) {
			// print out necessary information
			System.out.println(taughtCoursesList.get(i).getCourseId() + "|" + taughtCoursesList.get(i).getCourseName() + ", " + taughtCoursesList.get(i).getStartTime() + "-" + taughtCoursesList.get(i).getEndTime() + " on " + taughtCoursesList.get(i).getDays() + ", with course capacity: " + taughtCoursesList.get(i).getCapacity() + ", students: 0, lecturer: Professor " + taughtCoursesList.get(i).getLecturer());
		}
		System.out.println();
	}
	
	 /**
     * Prints out the students enrolled in courses taught by the professor.
     *
     * @param givenCourseID The ID of the course to view enrolled students.
     * @param students      AList of Student objects representing all students.
     */
	public void viewEnrolledStudents(String givenCourseID, List<Student> students) {
		List<Student> enrolledStudentsList = new ArrayList<>();
		
		// iterate through list of Student objects
		// for each student in the list of all students in Penn CS:
		for (int i=0; i<students.size(); i++) {
			if (students.get(i).getSelectedCourses().size() != 0) {
				// loop through all of the selected student's courses
				for (int j=0; j<students.get(i).getSelectedCourses().size(); j++) {
					// if the student has a course ID matching the given courseID, add student to list
					if (students.get(i).getSelectedCourses().get(j).getCourseId().equals(givenCourseID)) {
						enrolledStudentsList.add(students.get(i));
					}
				}
			}
		}
		
		System.out.println("Students in your course " + givenCourseID + ": ");
		// iterate through enrolledStudentsList to print to console
		for (int m=0; m<enrolledStudentsList.size(); m++) {
			System.out.println(enrolledStudentsList.get(m).getStudentId() + " " + enrolledStudentsList.get(m).getStudentName());
		}
	}
	
}