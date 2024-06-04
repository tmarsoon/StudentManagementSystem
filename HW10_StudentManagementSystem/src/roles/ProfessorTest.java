package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roles.Professor;

class ProfessorTest {
	
	@Test
	void testGetProfessorName() {
		  Professor professor = new Professor("Rajiv Gandhi", "004", "Gandhi", "password590");
	        assertEquals("Rajiv Gandhi", professor.getProfessorName());
	        
	        Professor professor1 = new Professor("Sanjeev Khanna", "009", "Khanna", "password590");
	        assertEquals("Sanjeev Khanna", professor1.getProfessorName());
	        
	        Professor professor2 = new Professor("Eric Noel Fouh Mbindi", "024", "Mbindi", "password590");
	        assertEquals("Eric Noel Fouh Mbindi", professor2.getProfessorName());
	}
	
	@Test
	void testGetProfessorID() {
		 Professor professor = new Professor("Rajiv Gandhi", "004", "Gandhi", "password590");
	        assertEquals("004", professor.getProfessorID());
	        
	        Professor professor1 = new Professor("Sanjeev Khanna", "009", "Khanna", "password590");
	        assertEquals("009", professor1.getProfessorID());
	        
	        Professor professor2 = new Professor("Eric Noel Fouh Mbindi", "024", "Mbindi", "password590");
	        assertEquals("024", professor2.getProfessorID());
	}
	

	
	@Test
	void testGetProfessorUsername() {
		 Professor professor = new Professor("Rajiv Gandhi", "004", "Gandhi", "password590");
	        assertEquals("Gandhi", professor.getProfessorUsername());
	        
	        Professor professor1 = new Professor("Sanjeev Khanna", "009", "Khanna", "password590");
	        assertEquals("Khanna", professor1.getProfessorUsername());
	        
	        Professor professor2 = new Professor("Eric Noel Fouh Mbindi", "024", "Mbindi", "password590");
	        assertEquals("Mbindi", professor2.getProfessorUsername());
	}
	@Test
	void testGetProfessorPassword() {
		 Professor professor = new Professor("Rajiv Gandhi", "004", "Gandhi", "password590");
	        assertEquals("password590", professor.getProfessorPassword());
	        
	        Professor professor1 = new Professor("Sanjeev Khanna", "009", "Khanna", "password590");
	        assertEquals("password590", professor1.getProfessorPassword());
	        
	        Professor professor2 = new Professor("Eric Noel Fouh Mbindi", "024", "Mbindi", "password590");
	        assertEquals("password590", professor2.getProfessorPassword());
	}
	
}
