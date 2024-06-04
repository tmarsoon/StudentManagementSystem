import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import files.FileInfoReader;
import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.Student.CourseGrade;


/**
 * @author Tony Marsalla
 * @partner Nicholas Miller
 * @partner Naveen Dasari
 */
public class Controller {

	private List<Course> courses;
	private List<Student> students; 
	private List<Admin> admins;
	private List<Professor> professors;
	
	public Scanner scanner = new Scanner(System.in);
	public boolean flag = true;
    // Constructor to initialize the courses list
	 public Controller(List<Course> courses, List<Student> students, List<Admin> admins, List<Professor> professors) {
	        this.courses = courses;
	        this.students = students;
	        this.admins = admins;
	        this.professors = professors;
	        
			}
	 public boolean timeConflict(String start1, String end1, String start2, String end2) {
	    	// The format will be ##:## & ##:## && ##:## & ##:##
	    	String[] startParts1 = start1.split(":");
	    	String[] endParts1 = end1.split(":");
	    	String[] startParts2 = start2.split(":");
	    	String[] endParts2 = end2.split(":");
	    	
	    	int startPart1Hour = Integer.valueOf(startParts1[0]);
	    	double startPart1Minute = Double.valueOf(startParts1[1])/60;
	    	
	    	int endPart1Hour = Integer.valueOf(endParts1[0]);
	    	double endPart1Minute = Double.valueOf(endParts1[1])/60;
	    	
	    	int startPart2Hour = Integer.valueOf(startParts2[0]);
	    	double startPart2Minute = Double.valueOf(startParts2[1])/60;
	    	
	    	int endPart2Hour = Integer.valueOf(endParts2[0]);
	    	double endPart2Minute = Double.valueOf(endParts2[1])/60;
	    	
	    	double startTime1 = startPart1Hour + startPart1Minute;
	    	double endTime1 = endPart1Hour + endPart1Minute;

	    	double startTime2 = startPart2Hour + startPart2Minute;
	    	double endTime2 = endPart2Hour + endPart2Minute;

	    	//Check if start time of 1 is within 2
	    	if (startTime2 <= startTime1 && startTime1 < endTime2) {
	    		return true;
	    	}
	    	//Check if end time of 1 is within 2
	    	else if (startTime2 < endTime1 && endTime1 <= endTime2){
	    		return true;
	    		
	    	}
	    	//Check if start time of 2 is within 1
	    	else if (startTime1 <= startTime2 && startTime2 < endTime1) {
	    		return true;
	    	}
	    	//Check if end time of 2 is within 1
	    	else if (startTime1 < endTime2 && endTime2 <= endTime1) {
	    		return true;
	    	}
	    	//If no overlap
	    	else {
	    		return false;
	    	}
	    	
	    }
	    
	 public void systemGo() {
		 while(flag) { 
			 System.out.println("| -------------------------");
			 System.out.println("| Student Management System");
			 System.out.println("| --------------------------");
			 System.out.println("| 1 -- Login as a student");
			 System.out.println("| 2 -- Login as a professor");
			 System.out.println("| 3 -- Loign as an admin");
			 System.out.println("| 4 -- Quit the system");
			 System.out.println("|\n| Please enter your option, eg. '1'.");
			 
			 int select = scanner.nextInt();
			 
			 switch(select) {
			 case 1:
				 studentLogin();
				 break;
			 case 2:
				 professorLogin();
				 break;
			 case 3:
				 adminLogin();
				 break;
			 case 4:
				 System.out.println("System exited! Good bye.");
				 scanner.close();
				 return;
			 default:
				 System.out.println("Invalid choice. Please select agian.");
			 }
			 
			 
		 }
		
	 }
	 
	 public void studentLogin() {
		Student student = loginStudent(scanner);
		if (student != null) {
			System.out.println("Login successful\n");
			studentMenu(student, scanner);
		} else {
				System.out.println("\n");
			}
		}
	 
	 
	 private Student loginStudent(Scanner scanner) {
	       String input = scanner.nextLine().trim();;
	       
	       if (input.equalsIgnoreCase("q")) {
	    	   System.out.println("\nSystem has been quit.");
	    	   flag=false;
	    	   return null;
	       }
	     while(true) {
	        System.out.println("Please enter your username, or type 'q' to quit");
	        String username = scanner.nextLine().trim();
	        if (username.equalsIgnoreCase("q")) {
	            System.out.println("\nClosing Students Management System: Exiting Program");
	            flag = false;
	            return null;
	        }
	        
	        System.out.println("Please enter your password, or type 'q' to quit");
	        String password = scanner.nextLine();
	        if (username.equalsIgnoreCase("q")) {
	            System.out.println("\nClosing Students Management System: Exiting Program");
	            flag=false;
	            return null;
	        }
	        for (Student student : students) {
	            if (student.getStudentUsername().equals(username) && student.getStudentPassword().equals(password)) {
	                return student; // Found a matching student
	            }
	        }
	        System.out.println("Invalid username or password. Unable to login.\n");
	    }
	 } 
	     
	 
	 public void studentMenu(Student student, Scanner scanner) {
		
		 System.out.println("| -------------------------");
		 System.out.println("| Welcome, " + student.getStudentName());
		 System.out.println("| --------------------------");
		 
		 while(true) {
			
			 System.out.println("| 1 -- View all courses");
			 System.out.println("| 2 -- Add courses to your list");
			 System.out.println("| 3 -- View selected courses");
			 System.out.println("| 4 -- Drop courses in your list");
			 System.out.println("| 5 -- View grades");
			 System.out.println("| 6 -- Return to previous menu");
			 System.out.println("|\n| Please enter your option, eg. '1'.");
			 
		 int option = scanner.nextInt();
		 
		 switch(option) {
		 case 1:
			 displayAllCourses();
			 break;
		 case 2:
			 addCoursesToYourList(student);
             break;
         case 3:
             viewSelectedCourses(student);
             break;
         case 4:
             dropCoursesInYourList(student);
             break;
         case 5:
             viewGrades(student);
             break;
         case 6:
             return; // Exit the menu and return to the previous menu
         default:
             System.out.println("Invalid choice. Please select again.");
     }
 }
}
	 public void displayAllCourses() {
	        System.out.println("\nList of Courses:");
	        for (Course course : courses) {
	            System.out.print(course.getCourseId() + "|");
	            System.out.print(course.getCourseName() + ", ");
	            System.out.print(course.getStartTime() + "-");
	            System.out.print(course.getEndTime());
	            System.out.print(" on " + course.getDays());
	            System.out.print(", with course capacity: " + course.getCapacity());
	            System.out.print(", students: 0,");
	            System.out.print(" lecturer: " + course.getLecturer());
	            System.out.println();
	        }
	         System.out.println("\n");
	 }
 
	// Add courses to the student's list
	 private void addCoursesToYourList(Student student) {
	
		 System.out.println("\nAvailable Courses:");
	     for (Course course : courses) {
	            System.out.print(course.getCourseId() + "|");
	            System.out.print(course.getCourseName() + ", ");
	            System.out.print(course.getStartTime() + "-");
	            System.out.print(course.getEndTime());
	            System.out.print(" on " + course.getDays());
	            System.out.print(", with course capacity: " + course.getCapacity());
	            System.out.print(", students: 0,");
	            System.out.print(" lecturer: " + course.getLecturer());
	            System.out.println();
	     }
	     List<Course> selectedCourses = new ArrayList<>();
	    
	     boolean isFirstIteration = true;
	     boolean isSecondIteration=false;
	     while (true) {
	    	
	    	 if (!isFirstIteration) {
	     System.out.println("\nPlease select the course ID you want to add to your list, e.g., 'CIT590'.\n"
	                + "Or enter 'q' to return to the previous menu.");
	     isSecondIteration=true;
	    	 }else {
	    		 isFirstIteration = false;
	    		 isSecondIteration=false;
	    		
	    	 }
	        String input = scanner.nextLine().trim().toUpperCase();

	        if (input.equalsIgnoreCase("q")) {
	            System.out.println("\nExiting course selection.");
	            return;
	        }

	        // Find the course with the matching ID and perform checks here
	        Course selectedCourse = null;
	        for (Course course : courses) {
	            if (course.getCourseId().equalsIgnoreCase(input)) {
	                selectedCourse = course;
	                break;
	            }
	        }

	        if (selectedCourse != null) {
	            // Check for time conflicts with the student's current schedule
	        	   if (selectedCourses.contains(selectedCourse)) {
	                   System.out.println("The course you selected is already in your list.");
	                   isSecondIteration=false;
	        	   } else {
	            boolean hasTimeConflict = false;
	            for (Course enrolledCourse : selectedCourses) {
	                if (enrolledCourse.getDays().equalsIgnoreCase(selectedCourse.getDays())) {
	                    // Check if there is a time conflict
	                    if (enrolledCourse.getEndTime().compareTo(selectedCourse.getStartTime()) > 0
	                            && enrolledCourse.getStartTime().compareTo(selectedCourse.getEndTime()) < 0) {
	                        // There is a time conflict
	                        System.out.println("Time conflict with course: " + enrolledCourse.getCourseName());
	                        hasTimeConflict = true;
	                        isSecondIteration=false;
	                        break; // No need to continue checking
	                    }
	                }
	            }
	        	   

	            if (!hasTimeConflict) {
	                // Check for overlapping days (e.g., MW and MWF)
	                for (Course enrolledCourse : selectedCourses) {
	                    if (!enrolledCourse.getDays().equalsIgnoreCase(selectedCourse.getDays())) {
	                        for (char day : selectedCourse.getDays().toCharArray()) {
	                            if (enrolledCourse.getDays().contains(String.valueOf(day))) {
	                                // Check if there is an overlap on this day
	                                if (enrolledCourse.getEndTime().compareTo(selectedCourse.getStartTime()) > 0
	                                        && enrolledCourse.getStartTime().compareTo(selectedCourse.getEndTime()) < 0) {
	                                    // There is a time conflict
	                                    System.out.println("Time conflict with course: " + enrolledCourse.getCourseName());
	                                    hasTimeConflict = true;
	                                    isSecondIteration=false;
	                                    break; // No need to continue checking
	                                }
	                            }
	                        }
	                    }
	                    if (hasTimeConflict) {
	                        break; // No need to continue checking
	                    }
	                }
	            }
	            if (!hasTimeConflict) {
	                // Add the selected course to the student's list first
	                student.enrollInCourse(selectedCourse);
	                selectedCourses.add(selectedCourse); 
	               //successfullyAddedCourses.add(selectedCourse);
	                student.addCourseToSuccessfullyAdded(selectedCourse); 
	                // Update the professor's student list array
	                // selectedCourse.getLecturer().enrollStudent(student);

	                // Update the course's enrollment count
	                selectedCourse.incrementEnrollmentCount();

	                System.out.println("Course added successfully.");
	                isSecondIteration=false;
	            }
	        }
	       	
	        } 
	        else if (isFirstIteration){
	        	System.out.println("");
	        	
	        }
	        
	         if (isSecondIteration) {
	        	System.out.println("Invalid course ID. Please try again.");
	        }
	        }
	 }
	        
	    
	 
	 // View the courses selected by the student
	
	 private void viewSelectedCourses(Student student) {
	     List<Course>  successfullyAddedCourses = student.getSuccessfullyAddedCourses();
	     
	     if (successfullyAddedCourses.isEmpty()) {
	         System.out.println("You haven't selected any courses yet.");
	     } else {
	         System.out.println("\nThe courses in your list:");
	         for (Course course :  successfullyAddedCourses) { 	 
	        	 	System.out.print(course.getCourseId() + "|");
		            System.out.print(course.getCourseName() + ", ");
		            System.out.print(course.getStartTime() + "-");
		            System.out.print(course.getEndTime());
		            System.out.print(" on " + course.getDays());
		            System.out.print(", with course capacity: " + course.getCapacity());
		            System.out.print(", students: 0,");
		            System.out.print(" lecturer: " + course.getLecturer());
		            System.out.println();
	         }
	     }
	 
	  
	     boolean flag2 = true;
	     while (true) {
	         if (!flag2) {
	             System.out.println("\nPlease enter 'q' to return to the previous menu.");
	         }

	         String input = scanner.nextLine().trim();

	         if (input.equalsIgnoreCase("q")) {
	             System.out.println("\nExiting course view.");
	             break;
	         } else  if (flag2){
	             System.out.print("");
	             flag2=false;
	         } else if (!flag2) {
	        	 System.out.println("Invalid input.");
	         }
	     }
	 }
	 
	 private void dropCoursesInYourList(Student student) {
		 List<Course>  successfullyAddedCourses = student.getSuccessfullyAddedCourses();
		    
		    if (successfullyAddedCourses.isEmpty()) {
		        System.out.println("You haven't selected any courses to drop.");
		        return;
		    }
		    boolean droppedCourse = false; 
		   while (true) {
		    System.out.println("\nThe courses in your list:");
	         for (Course course :  successfullyAddedCourses) { 	 
	        	 	System.out.print(course.getCourseId() + "|");
		            System.out.print(course.getCourseName() + ", ");
		            System.out.print(course.getStartTime() + "-");
		            System.out.print(course.getEndTime());
		            System.out.print(" on " + course.getDays());
		            System.out.print(", with course capacity: " + course.getCapacity());
		            System.out.print(", students: 0,");
		            System.out.print(" lecturer: " + course.getLecturer());
		            System.out.println();
	         }

		    System.out.print("\nPlease enter the ID of the course you want to drop, eg. 'CIT591'.\n"
		    				+ "Or enter 'q' to return to the previous menu.");
		    System.out.println();
		    String input = scanner.nextLine();
		    
		    if (input.equalsIgnoreCase("q")) {
		    	if (droppedCourse) {
	                System.out.println("Exiting course drop.\n");
	            } else {
	                System.out.println("No courses were dropped. Exiting course drop.\n");
	            }
	            return;
	        }
		  
		    
		    input = scanner.nextLine();
		    // Find the course with the matching ID
		    Course courseToRemove = null;
		    for (Course course : successfullyAddedCourses) {
		        if (course.getCourseId().equalsIgnoreCase(input)) {
		            courseToRemove = course;
		            break;
		        }
		    }

		    if (courseToRemove != null) {
		        // Remove the selected course from the student's list
		        student.unenrollFromCourse(courseToRemove);
		        successfullyAddedCourses.remove(courseToRemove);
		        droppedCourse = true;
		        System.out.println("Course dropped successfully");
		    } else {
		        System.out.println("Invalid course ID. Course not removed.");
		    }
		}
	 }

		// View grades for courses
		private void viewGrades(Student student) {
			 try (BufferedReader reader = new BufferedReader(new FileReader("studentInfo.txt"))) {
			        String line;
			        String studentId = student.getStudentId();

			        while ((line = reader.readLine()) != null) {
			            String[] parts = line.split(";");
			            if (parts.length == 5 && parts[0].equals(studentId)) {
			                String[] gradeInfo = parts[4].split(", ");
			                List<CourseGrade> grades = new ArrayList<>(); // Create a list to collect grades for this student
			                boolean flag3 = false; // Flag to track the first iteration
			               
			                for (String grade : gradeInfo) {
			                    String[] courseGrade = grade.split(": ");
			                    if (courseGrade.length == 2) {
			                        String courseId = courseGrade[0].trim();
			                        String gradeValue = courseGrade[1].trim();
			                        String courseName = "";

			                        // Attempt to find the course name in your courses list
			                        for (Course course : courses) {
			                            if (course.getCourseId().equals(courseId)) {
			                                courseName = course.getCourseName();
			                                break;
			                            }
			                        }

			                        // Use the course ID as the name for the first iteration
			                        if (flag3) {
			                            courseName = courseId;
			                            flag3 = true; // Set the flag to false after the first iteration
			                        }

			                        // Now, you can create a CourseGrade with the correct course name
			                        CourseGrade courseGradeObj = new CourseGrade(studentId, courseId, courseName, gradeValue);
			                        grades.add(courseGradeObj);
			                    }
			                }

			                if (!grades.isEmpty()) {
			                    System.out.println("\nHere are the courses you have taken, with your grades in letter format:");
			                    for (CourseGrade grade : grades) {
			                        System.out.println("Grade of " + grade.getCourseId() + " " + grade.getCourseName() + ": " + grade.getGrade());
			                    }
			                } else {
			                    System.out.println("You don't have any grades available.");
			                }
			                System.out.println();
			                break; // No need to continue searching for grades after finding the student's data
			            }
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
	 
	 public void professorLogin() {
		 Professor professor = loginProfessor(scanner);
		 if (professor != null) {
			 System.out.println("Login successful\n");
			 professorMenu(professor, scanner);
		 } 
		 else {
			 System.out.println("\n");
		 }
	 }
	
	public Professor loginProfessor(Scanner scanner) {
		String input = scanner.nextLine().trim();
		if (input.equalsIgnoreCase("q")) {
	    	   System.out.println("\nSystem has been quit.");
	    	   flag=false;
	    	   return null;
	   }
	     while(true) {
	        System.out.println("Please enter your username, or type 'q' to quit");
	        String username = scanner.nextLine().trim();
	        
	        if (username.equalsIgnoreCase("q")) {
	            System.out.println("\nClosing Student Management System: Exiting Program");
	            flag = false;
	            return null;
	        }
	        
	        System.out.println("Please enter your password, or type 'q' to quit");
	        String password = scanner.nextLine();
	        
	        if (password.equalsIgnoreCase("q")) {
	            System.out.println("\nClosing Students Management System: Exiting Program");
	            flag=false;
	            return null;
	        }
	        for (Professor professor : this.professors) {
	            if (professor.getProfessorUsername().equals(username) && professor.getProfessorPassword().equals(password)) {
	                return professor; // Found a matching professor
	            }
	        }
	        System.out.println("Invalid username or password. Unable to login.\n");
	    }
	 } 
	
	
	 public void professorMenu(Professor professor, Scanner scanner) {
			
		 System.out.println("| -------------------------");
		 System.out.println("| Welcome, " + professor.getProfessorName());
		 System.out.println("| --------------------------");
		 
		 while(true) {
			
			 System.out.println("| 1 -- View given courses");
			 System.out.println("| 2 -- View student list of the given course");
			 System.out.println("| 3 -- Return to previous menu");
			 System.out.println("|\n| Please enter your option, eg. '1'.");
			 
			 int option = scanner.nextInt();
			 
			 switch(option) {
				 case 1:
					 professor.viewGivenCourses(this.courses);
					 break;
				 case 2:
					 System.out.println();
					 System.out.println("Please enter the course ID, eg. 'CIT590'");
					 String courseIDChosen = scanner.next().trim().toUpperCase();
					 professor.viewEnrolledStudents(courseIDChosen, this.students);
		             break;
		         case 3:
		             return; // Exit the menu and return to the previous menu
		         default:
		             System.out.println("Invalid choice. Please select again.");
			 }
		 }
	 }
		 
	 public int validAdminLogin(String username, String password) {
			for (int i = 0; i < this.admins.size(); i++) {
				if (username == this.admins.get(i).getAdminName() && password == this.admins.get(i).getAdminPassword()) {
					return i;
				}
			}   
			return 0;
	    }
	 
//----------------------------------------------------------------------------------------	    
	 
	 public void adminLogin() {

		   String input = scanner.nextLine();
		   
		   if (input.equalsIgnoreCase("q")) {
		   System.out.println("System has been quit.");
			   flag=false;
		   }
		   System.out.println("Please enter your username, or type 'q' to quit");
		   String username = scanner.nextLine();
		   if (username.equalsIgnoreCase("q")) {
			   System.out.println("System has been quit.");
			   flag = false;
		   }
		   
		   System.out.println("Please enter your password, or type 'q' to quit");
		   String password = scanner.nextLine();
		   if (username.equalsIgnoreCase("q")) {
			   System.out.println("System has been quit.");
			   flag=false;
		   }
		   
		   // Checking if username and password is valid
		   int indexOfValidUsername = -1;
		   for (int i = 0; i < this.admins.size(); i++) {
			   if (username.equals(this.admins.get(i).getAdminUsername())  && password.equals(this.admins.get(i).getAdminPassword())) {
				   indexOfValidUsername = i;
			   }
		   }   
		   
		   String subInput = "";
		   boolean subInputBreakFlag = false;
		   String[] option2Questions = {
				   "Please enter the course ID, or type 'q' to end.", 
				   "Please enter the course name, or type 'q' to end.",
				   "Please enter the course start time, or type 'q' to end. eg. '19:00'",
				   "Please enter the course end time, or type 'q' to end. eg. '20:00'",
				   "Please enter the course date, or type 'q' to end. eg. 'MW'",
				   "Please enter the course capacity, or type 'q' to end. eg. '72'",
				   "Please enter the course lecturer's id, or type 'q' to end. eg. '001'"};
		   String[] option2Answers = new String[7];
		   Professor professorIndexID = null;
		   
		   
		   if (indexOfValidUsername != -1) {
			   while(true) {
				   System.out.println("| -------------------------");
				   System.out.println("| Welcome, " + this.admins.get(indexOfValidUsername).getAdminName());
				   System.out.println("| --------------------------");
				   System.out.println("| 1 -- View all courses");
				   System.out.println("| 2 -- Add new courses");
				   System.out.println("| 3 -- Delete courses");
				   System.out.println("| 4 -- Add new professor");
				   System.out.println("| 5 -- Delete professor");
				   System.out.println("| 6 -- Add new student");
				   System.out.println("| 7 -- Delete student");
				   System.out.println("| 8 -- Return to previous menu");
				   System.out.println("|\n| Please enter your option, eg. '1'.");
				   input = scanner.nextLine();
				   if (input.equals("1")) {
					   displayAllCourses();
				   }
				   else if (input.equals("2")) {
					   //Asking user questions to fill answer
					   String profName = null;
					   for (int i = 0; i < option2Questions.length; i++) {
						   boolean subSubInputBreakFlag = false;
						   while (true) {
							   if (subSubInputBreakFlag == true) {
								   break;
							   }
							   subInputBreakFlag = true;
							   System.out.println(option2Questions[i]);
							   subInput = scanner.nextLine();
							   if (subInput.equals("q")) {
								   break;
							   }
							   //Checking if course is already in system
							   if (i == 0) {
								   for (Course j : this.courses) {
									   if (subInput.equals(j.getCourseId())) {
										   subInputBreakFlag = false;
										   System.out.println("The course already exists");
										   break;
									   }
								   }
								   if (subInputBreakFlag == true) {
									   break;
								   }
							   }
							   //Checking if professor is in the system
							   else if (i == 6){
								   boolean checkIfProfessorInSystem = false;
								   for (Professor j : this.professors) {
									   if (subInput.equals(j.getProfessorID())) {
										   checkIfProfessorInSystem = true;
										   professorIndexID = j;
										   profName = professorIndexID.getProfessorName();
										   break;
									   }
									   
								   }
								   if (checkIfProfessorInSystem == false) {
									   System.out.println("The professor isn't in the system, please add this professor first");
									   String[] option2SubQuestions = {
											   "Please enter the professor's ID, or type 'q' to quit",
											   "Please enter professor's name, or type 'q' to end",
											   "Please enter a username",
											   "Please enter a password"};
									   String[] option2SubAnswers = new String[4];
									   String subSubInput;
									   for (int j = 0; j < option2SubQuestions.length; j++) {
										   System.out.println(option2SubQuestions[j]);
										   subSubInput = scanner.nextLine();
										   option2SubAnswers[j] = subSubInput;
									   }
									   Professor newProfessor = new Professor(option2SubAnswers[0], option2SubAnswers[1], option2SubAnswers[2], option2SubAnswers[3]);
									   this.professors.add(newProfessor);
									   System.out.println("Successfully added the new professor: " + option2SubAnswers[0] + " " + option2SubAnswers[1]);
									   profName = option2SubAnswers[1];

								   }
								   // Checking for time conflict
								   else {
									   //Using professorName and searching through all courses to extract courses professor is in.
									   //Then extracting the ID, Number, Days, Start time, End time of course
									   //If conflict with one of the classes during comparison, then throw message saying conflict with that course
									   char[] desiredDays = option2Answers[4].toCharArray();
									   boolean dayBreakFlag = false;
									   //Looping through each course
									   for (int j = 0; j < this.courses.size(); j++) {
										   //Getting Days for the first step of finding time conflict
										   if (professorIndexID.getProfessorName().equals(this.courses.get(j).getLecturer())) {											 
											   char[] comparisonDays = this.courses.get(j).getDays().toCharArray();
											   for (int k = 0; k < desiredDays.length; k++) {
												   for (int l = 0; l < comparisonDays.length; l++) {
													   if (desiredDays[k] == comparisonDays[l]) {
														   dayBreakFlag = true;
														   break;
														   
													   }
													   if (dayBreakFlag == true) {
														   break;
													   }
												   }
											   }
											   //If there is a day of same operation, then check the times on those days
											   if (dayBreakFlag == true) {
												   if (timeConflict(option2Answers[2], option2Answers[3], this.courses.get(j).getStartTime(), this.courses.get(j).getEndTime()) == true) {
													   System.out.println("The new added course has a time conflict with course: " + 
															   this.courses.get(j).getCourseId() + "|" + this.courses.get(j).getCourseName() + ", " + this.courses.get(j).getStartTime() + 
																  "-" + this.courses.get(j).getEndTime() + " on " + this.courses.get(j).getDays() + ", with course capacity: " + 
																  this.courses.get(j).getCapacity() + ", students: 0, lecturer: Professor " + this.courses.get(j).getLecturer());
													   String professorName = "";
													   
													   //Getting professors name based on given ID
													   for (Professor d : this.professors) {
														   if (d.getProfessorID().equals("029")) {
															   professorName = d.getProfessorName();
															   break;
														   }
													   }
													   System.out.println("Unable to add new course: " + option2Answers[0] + "|" + option2Answers[1] + ", " + option2Answers[2] + 
																  "-" + option2Answers[3] + " on " + option2Answers[4] + ", with course capacity: " + option2Answers[5] + 
																  ", students: 0, lecturer: Professor " + professorName);
												   }  

											   }
											   //If no days match, then it's good to use.  Professor has no say here.  Hard times.
											   subSubInputBreakFlag = true;
											   break;
										   }
									   }
								   }
							   }
							   else {
								   break;
							   }
						   }
						   option2Answers[i] = subInput;
						   if (subInput.equals("q")) {
							   break;
						   }
					   }
					   Course newCourse = new Course(option2Answers[0], option2Answers[1], option2Answers[2],
							   option2Answers[3],option2Answers[4], option2Answers[5], option2Answers[6]);
					   this.courses.add(newCourse);
					   System.out.println("Successfully added the course: " + option2Answers[0] + "|" + option2Answers[1] + ", " + option2Answers[2] + 
							  "-" + option2Answers[3] + " on " + option2Answers[4] + ", with course capacity: " + option2Answers[5] + 
							  ", students: 0, lecturer: Professor " + profName);
						   

				   }
				   else if (input.equals("3")) {
					   System.out.println("Please enter the course ID you want to delete, or type 'q' to end");
					   subInput = scanner.nextLine();
					   if (subInput.equals("q")) {
						   continue;
					   }
					   else {
						   int lengthOfCourseList = this.courses.size();
						   for (int j = 0; j < this.courses.size(); j++) {
							   if (this.courses.get(j).getCourseId().equals(subInput)) {
								   this.courses.remove(j);
								   System.out.println("Successfully removed the class with the ID: " + subInput);
								   break;
							   }
						   }
						   if (lengthOfCourseList == this.courses.size()) {
							   System.out.println("The class ID: " + subInput + " is not in the system.");

						   }
						   
					   }

				   }
				   else if (input.equals("4")) {
					   String[] option4Questions = {
						   "Please enter a name for the professor, or type 'q' to end",
						   "Please enter an ID for the professor, or type 'q' to end",
						   "Please enter an username for the professor, or type 'q' to end",
						   "Please enter a password for the professor, or type 'q' to end"};
					   String[] option4Answers = new String[4];

					   for (int j = 0; j < option4Questions.length; j++) {
						   while (true) {
							   boolean errorID = false;
							   boolean errorUsername = false;
							   System.out.println(option4Questions[j]);
							   subInput = scanner.nextLine();
							   option4Answers[j] = subInput;
							   if (subInput.equals("q")) {
								   break;
							   }
							   if (j == 1) {
								   for (int k = 0; k < this.professors.size(); k++) {
									   if (option4Answers[1].equals(this.professors.get(k).getProfessorID())) {
										   System.out.println("The ID you entered is not available");
										   errorID = true;
										   break;
									   }
								   }
								   if (errorID == false) {
									   break;
								   }
								   
							   }
							   else if (j == 2) {
								   for (int k = 0; k < this.professors.size(); k++) {
									   if (option4Answers[2].equals(this.professors.get(k).getProfessorUsername())) {
										   System.out.println("The username you entered is not available");
										   errorUsername = true;
										   break;
									   }
								   }
								   if (errorUsername == false) {
									   break;
								   }
							   }
							   else {
								   break;
							   }
	
						   } 
					   }
					   if (subInput.equals("q")){
						   continue;
					   }
					   else {
						   Professor newProf = new Professor(option4Answers[0], option4Answers[1], option4Answers[2], option4Answers[3]);
						   this.professors.add(newProf);
						   System.out.println("Successfully added the new professor: " + option4Answers[1] + " " + option4Answers[0]);

					   }
	
				   }
				   else if (input.equals("5")) {
					   System.out.println("Please enter the professor ID you want to delete, or type 'q' to end");
					   subInput = scanner.nextLine();
					   if (subInput.equals("q")) {
						   continue;
					   }
					   else {
						   int lengthOfProfessorList = this.professors.size();
						   for (int j = 0; j < this.professors.size(); j++) {
							   if (this.professors.get(j).getProfessorID().equals(subInput)) {
								   this.professors.remove(j);
								   System.out.println("Successfully removed the professor with the ID: " + subInput);
								   break;
							   }
						   }
						   if (lengthOfProfessorList == this.courses.size()) {
							   System.out.println("The professor ID: " + subInput + " is not in the system.");

						   }
						   
					   }
				   }
				   else if (input.equals("6")) {
					   
					   String[] option6Questions = {
							   "Please enter an ID for the student, or type 'q' to end",
							   "Please enter a name for the student, or type 'q' to end",
							   "Please enter an username for the student, or type 'q' to end",
							   "Please enter a password for the student, or type 'q' to end",
							   "Please enter ID of a course which this student already took, one at a time \nType 'q' to quit, typ 'n' to stop adding"};
						   String[] option6Answers = new String[4];
						   List<String> courseIDs = new ArrayList<String>();
						   List<String> courseGrades = new ArrayList<String>();
						   List<String> studentIDs = new ArrayList<String>();
						   List<String> courseName = new ArrayList<String>();
						   //Looping through each question
						   for (int j = 0; j < option6Questions.length; j++) {
							  
							   //Continue looping until appropriate input
							   while (true) {
								   boolean errorID = false;
								   boolean errorUsername = false;
								   //Getting input
								   System.out.println(option6Questions[j]);
								   subInput = scanner.nextLine();
								   //store answers from user in this array only up to j=3 and have different arrays to store the course IDs and grades
								   if (j < 4 ) {
									   option6Answers[j] = subInput;
								   }
								   //If wanting to exit
								   if (subInput.equals("q")) {
									   break;
								   }

								   //Checking if ID number is available
								   if (j == 0) {
									   for (int k = 0; k < this.students.size(); k++) {
										   if (option6Answers[0].equals(this.students.get(k).getStudentId())) {
											   System.out.println("The ID you entered is not available");	
											   errorID = true;
											   break;
										   }

									   }
									   if (errorID == false) {
										   break;
									   }
									   
								   }
								   //Checking if Username is available
								   else if (j == 2) {
									   for (int k = 0; k < this.students.size(); k++) {
										   if (option6Answers[2].equals(this.students.get(k).getStudentUsername())) {
											   System.out.println("The username you entered is not available");
											   errorUsername = true;
											   break;
										   }
									   }
									   if (errorUsername == false) {
										   break;
									   }
								   }
								   //Getting previous classes and grades of those classes
								   else if (j == 4){
									   
									   //When user is done adding classes
									   if (subInput.equals("n")) {
										   break;
									   }
									   //When user wants to continue adding classes
									   else {
										   courseIDs.add(subInput);
										   System.out.println("Please enter the grade, eg 'A'");
										   courseGrades.add(scanner.nextLine());
									   }
								   }
								   else {
									   break;
								   }
		
							   } 
						   }
						   //Checking if user wanted to quit again, but outside the while loop in order to return to admin menu
						   if (subInput.equals("q")){
							   continue;
						   }
						   //Adding new student
						   else {
							   Student newStudent = new Student(option6Answers[0], option6Answers[1], option6Answers[2], option6Answers[3], this.courses);

							   for (int k = 0; k < courseIDs.size(); k++) {
								   Student.CourseGrade newCourseGradeInstance = new Student.CourseGrade(studentIDs.get(k), courseIDs.get(k), courseGrades.get(k), courseName.get(k));
								  newStudent.addCourseGrade(newCourseGradeInstance);
							   }
							   this.students.add(newStudent);
							   System.out.println("Successfully added the new student: " + option6Answers[0] + " " + option6Answers[1]);


						   }
				   }
				   else if (input.equals("7")) {
					   System.out.println("Please enter the student ID you want to delete, or type 'q' to end");
					   subInput = scanner.nextLine();
					   if (subInput.equals("q")) {
						   continue;
					   }
					   else {
						   int lengthOfProfessorList = this.students.size();
						   for (int j = 0; j < this.students.size(); j++) {
							   if (this.students.get(j).getStudentId().equals(subInput)) {
								   this.students.remove(j);
								   System.out.println("Successfully removed the student with the ID: " + subInput);
								   break;
							   }
						   }
						   if (lengthOfProfessorList == this.courses.size()) {
							   System.out.println("The student ID: " + subInput + " is not in the system.");

						   }
						   
					   }
				   }
				   else if (input.equals("8")) {
					   break;
				   }
				   else {
					   System.out.println("Invalid choice. Please select agian.");
				   }

		 }
	   }
	   else {
		   System.out.println("Invalid username and/or password.");

	   }
	}
    

    
    
   
	 public static void main(String[] args) {
		   // You need to read and parse course information here using FileInfoReader
	        // For demonstration purposes, let's assume you have a List<Course> named "coursesList" already populated
	        List<Course> coursesList = FileInfoReader.readCourseInfo();
	        List<Student> studentList = FileInfoReader.readStudentInfo();
	        List<Admin> adminList = FileInfoReader.readAdminInfo();
	        List<Professor> professorList = FileInfoReader.readProfInfo();
	        // Create an instance of the Controller and pass the courses list
	        Controller controller = new Controller(coursesList, studentList, adminList, professorList);
	        controller.systemGo();

		
	}
}


