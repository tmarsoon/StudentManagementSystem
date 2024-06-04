package courses;





import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Course {

		private String courseId;
	    private String courseName;
	    private String lecturer;
	    private String days;
	    private String startTime;
	    private String endTime;
	    private String capacity;
	    private int enrollmentCount = 0;
	    
	    /**
	     * Constructing a course with the provided information.
	     *
	     * @param courseId  The unique identifier of the course.
	     * @param courseName The name of the course.
	     * @param lecturer   The name of the lecturer.
	     * @param days       The days on which the course is scheduled.
	     * @param startTime  The start time of the course.
	     * @param endTime    The end time of the course.
	     * @param capacity   The maximum capacity of the course.
	     */
		

	   
	    public Course(String courseId, String courseName, String lecturer, String days, String startTime, String endTime, String capacity) {
	        this.courseId = courseId;
	        this.courseName = courseName;
	        this.lecturer = lecturer;
	        this.days = days;
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.capacity = capacity;
	       
	    }
	    /**
	     * Gets the id of the course.
	     
	     * @return The course's id
	     */
	    public String getCourseId() {
	        return courseId;
	    }
	    /**
	     * name of the course.
	     * @return The name of the course.
	     */
	    public String getCourseName() {
	        return courseName;
	    }
	    /**
	     * name of the lecturer.
	     * @return The name of the lecturer.
	     */
	    public String getLecturer() {
	        return lecturer;
	    }
	    
	    /**
	     *Gets the days on which the course is scheduled.
	     * @return The days.
	     */
	    public String getDays() {
	        return days;
	    }
	    /**
	     * Gets the start time of the course.
	     * @return The start time in HH:mm format.
	     */
	    public String getStartTime() {
	        return startTime;
	    }
	    /**
	     * Gets the end time of the course.
	     *
	     * @return The end time in HH:mm format.
	     */
	    public String getEndTime() {
	        return endTime;
	    }
	    /**
	     * capacity of the course.
	     *
	     * @return capacity of the course.
	     */
	    public String getCapacity() {
	        return capacity;
	    }
	   
	    /**
	     * Checks if there is a time conflict between this course and another course.
	     *
	     * @param otherCourse The other course to check for time conflict.
	     * @return True if there is a time conflict, false otherwise.
	     */
	   
	    public boolean hasTimeConflict(Course otherCourse) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        // Parsing the start and end times into LocalTime objects for comparison
	        LocalTime thisStartTime = LocalTime.parse(this.getStartTime(), formatter);
	        LocalTime thisEndTime = LocalTime.parse(this.getEndTime(), formatter);
	        LocalTime otherStartTime = LocalTime.parse(otherCourse.getStartTime(), formatter);
	        LocalTime otherEndTime = LocalTime.parse(otherCourse.getEndTime(), formatter);

	       
	        
	        if (this.getDays().equalsIgnoreCase(otherCourse.getDays())) {
	            if (!(thisEndTime.isBefore(otherStartTime) || thisStartTime.isAfter(otherEndTime))) {
	            	return true; // There is a time conflict
	            }
	        }
	        return false; // No time conflict
	    }
	
	    /**
	     * Gets the current enrollment count for the course.
	     * @return The current enrollment count.
	     */
	    public int getEnrollmentCount() {
	    	return enrollmentCount;
	    }
//increments enrollment count. 
	    public void incrementEnrollmentCount() {
	    	enrollmentCount++;
	    }

	    //Decrementing the enrollment count for the course
	    public void decrementEnrollmentCount() {
	    	if (enrollmentCount > 0) {
	    		enrollmentCount--;
	    	}
	     
	}

}


