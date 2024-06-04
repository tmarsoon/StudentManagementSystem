package files;

//must import the java io to access the input/output stream for file reaidng.
import java.io.*;

import java.util.ArrayList;
//import the list interface so we can keep an ordered collection of the file data.
import java.util.List;
import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;


public class FileInfoReader {

	//we use all upper case and underscores when defining static variables. 
	private static final String COURSE_INFO_FILE = "courseInfo.txt";
	private static final String STUDENT_INFO_FILE = "studentInfo.txt";
	private static final String PROFESSOR_INFO = "profInfo.txt";
	private static final String ADMIN_INFO = "adminInfo.txt";
	
	//create a method to read the files now.
	//this method can be static since it will belong to the class itself
	//List<Course> is the return data type for the method as we return a list of course objects.
	public static List<Course> readCourseInfo() {
		// Use a list to store the ordered collection
	    List<Course> courses = new ArrayList<>();
	    BufferedReader myCourseBuffer = null; // Declare the buffer

	    try {
	        // Create the file object
	        File myCourseFile = new File(COURSE_INFO_FILE);
	        // Read the file object
	        FileReader myCourseReader = new FileReader(myCourseFile);
	        // Buffer the data
	        myCourseBuffer = new BufferedReader(myCourseReader);
	        String line;

	        // Here we want to iterate through each line of the file
	        // != null here means as long as there is content on each line and is not empty
	        while ((line = myCourseBuffer.readLine()) != null) {
	            // Split the line
	            String[] lineSplit = line.split(";");
	            
	            // Each line contains 7 different parts in the text file
	            if (lineSplit.length == 7) {
	                // Parse the new format and trim whitespace
	                String courseId = lineSplit[0].trim();
	                String courseName = lineSplit[1].trim();
	                String professorName = lineSplit[2].trim();
	                String days = lineSplit[3].trim();
	                String startTime = lineSplit[4].trim();
	                String endTime = lineSplit[5].trim();
	                String capacity = lineSplit[6].trim();
	                
	                // Now create a course with all of its attributes
	                Course course = new Course(courseId, courseName, professorName, days, startTime, endTime, capacity);
	                courses.add(course);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (myCourseBuffer != null) {
	                myCourseBuffer.close(); // Close the buffer in a finally block
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return courses;
	}


	
	public static List<Admin> readAdminInfo() {
		  // Using list to store ordered collection
	    List<Admin> admins = new ArrayList<>();
	    BufferedReader myAdminBuffer = null; // Declare the buffer
	    
	    try {
	        // Create admin object
	        File myAdminFile = new File(ADMIN_INFO);
	        // Read the file object
	        FileReader myAdminReader = new FileReader(myAdminFile);
	        // Buffer the data
	        myAdminBuffer = new BufferedReader(myAdminReader);
	        String line2;
	        
	        while ((line2 = myAdminBuffer.readLine()) != null) {
	            String[] lineSplit2 = line2.split(";");
	            
	            if (lineSplit2.length == 4) {
	                String adminId = lineSplit2[0].trim();
	                String adminName = lineSplit2[1].trim();
	                String adminUsername = lineSplit2[2].trim();
	                String adminPassword = lineSplit2[3].trim();
	                // Take each line of info and create an object
	                Admin admin = new Admin(adminId, adminName, adminUsername, adminPassword);
	                // Take each object and add it to the admin ordered list
	                admins.add(admin);
	            }       
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (myAdminBuffer != null) {
	                myAdminBuffer.close(); // Close the buffer in a finally block
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return admins;
	}
	
	
	public static List<Professor> readProfInfo() {
		 // Create an ordered list to store professors
	    List<Professor> profs = new ArrayList<>();
	    BufferedReader myProfBuffered = null; // Declare the buffer

	    try {
	        // Create a file object
	        File myProfsFile = new File(PROFESSOR_INFO);
	        // Read the file object
	        FileReader myProfReader = new FileReader(myProfsFile);
	        // Buffer the data
	        myProfBuffered = new BufferedReader(myProfReader);
	        String line3;

	        // Read each line and store it in line3
	        while ((line3 = myProfBuffered.readLine()) != null) {
	            String[] lineSplit3 = line3.split(";");

	            if (lineSplit3.length == 4) {
	                String profName = lineSplit3[0].trim();
	                String profId = lineSplit3[1].trim();
	                String profUsername = lineSplit3[2].trim();
	                String profPassword = lineSplit3[3].trim();

	                // Create an object from each line
	                Professor prof = new Professor(profName, profId, profUsername, profPassword);
	                // Add each object to the list
	                profs.add(prof);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (myProfBuffered != null) {
	                myProfBuffered.close(); // Close the buffer in a finally block
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return profs;
	}
	
	public static List<Student> readStudentInfo() {
		 // Create an ordered list to store students
		 // Create an ordered list to store students
	    List<Student> students = new ArrayList<>();
	    try (BufferedReader myStudentBuffered = new BufferedReader(new FileReader(STUDENT_INFO_FILE))) {
	        // Create a variable to store each readLine of text
	        String line4;
	        while ((line4 = myStudentBuffered.readLine()) != null) {
	            String[] lineSplit4 = line4.split(";");
	            if (lineSplit4.length >= 4) { // Updated condition to match the new constructor
	                String studentID = lineSplit4[0].trim();
	                String studentName = lineSplit4[1].trim();
	                String studentUsername = lineSplit4[2].trim();
	                String studentPassword = lineSplit4[3].trim();
					String[] classesAndGrades = lineSplit4[4].trim().split(",");

	                // Create a list of courses
	                List<Course> courses = new ArrayList<>();
	                // Create the student object with the list of courses
	                Student student = new Student(studentID, studentName, studentUsername, studentPassword, courses);
	                // Here, I need to loop through the courses to add them to the student's object
	                // Starting at index 4, we increment to get to the total number of index values in the line
	                if (lineSplit4.length > 4) {
						for (int i = 0; i<classesAndGrades.length; i++) {
							//split each grade after index 4 by ,
							String[] gradeSplit = classesAndGrades[i].trim().split(":");
							if (gradeSplit.length == 2) {
								String courseId = gradeSplit[0].trim();
								String courseGrade = gradeSplit[1].trim();
	                        
		                        // Define courseName and studentId based on your data source or logic
		                        String courseName = "Placeholder Course Name";
		                        String studentId = "Placeholder Student ID";
		                        
		                        // Create a CourseGrade object with the appropriate parameters
		                        Student.CourseGrade courseGrade1 = new Student.CourseGrade(studentId, courseId, courseName, courseGrade);
		                        student.addCourseGrade(courseGrade1);
							}
						}
	                }
	                
	                
	                // Add the student object to the ordered list
	                students.add(student);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return students;
	}
}