package roles;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


class AdminTest {
	
	@Test
	void getAdminId() {
	 //Creating admin objects with different IDs
		Admin admin0 = new Admin( "004", "Rajiv Gandhi", "Gandhi", "password590");
		Admin admin1 = new Admin( "009", "Sanjeev Khanna","Khanna", "password590");
		Admin admin2 = new Admin( "024", "Eric Noel Fouh Mbindi", "Mbindi", "password590");
		   
		//Check if getAdminId() returns the expected IDs
		assertEquals("004", admin0.getAdminId());
		assertEquals("009", admin1.getAdminId());
		assertEquals("024", admin2.getAdminId());
	}
	
	@Test
	void getAdminName() {
		 //Create admin objects with different names
		Admin admin0 = new Admin( "004", "Rajiv Gandhi", "Gandhi", "password590");
		Admin admin1 = new Admin( "009", "Sanjeev Khanna","Khanna", "password590");
		Admin admin2 = new Admin( "024", "Eric Noel Fouh Mbindi", "Mbindi", "password590");
		 
		//Check if getAdminName() returns the expected names
		assertEquals("Rajiv Gandhi", admin0.getAdminName());
		assertEquals("Sanjeev Khanna", admin1.getAdminName());
		assertEquals("Eric Noel Fouh Mbindi", admin2.getAdminName());
	}
	
	@Test
	void getAdminUsername() {
		 //Create admin objects with different usernames
		Admin admin0 = new Admin( "004", "Rajiv Gandhi", "Gandhi", "password590");
		Admin admin1 = new Admin( "009", "Sanjeev Khanna","Khanna", "password590");
		Admin admin2 = new Admin( "024", "Eric Noel Fouh Mbindi", "Mbindi", "password590");

	 //Check if getAdminUsername() returns the expected usernames
		assertEquals("Gandhi", admin0.getAdminUsername());
		assertEquals("Khanna", admin1.getAdminUsername());
		assertEquals("Mbindi", admin2.getAdminUsername());
	}
	
	@Test
	void getAdminPassword() {
		  //Creating admin objectss with different passwords
		Admin admin0 = new Admin( "004", "Rajiv Gandhi", "Gandhi", "password590");
		Admin admin1 = new Admin( "009", "Sanjeev Khanna","Khanna", "password590");
		Admin admin2 = new Admin( "024", "Eric Noel Fouh Mbindi", "Mbindi", "password590");
		 
		//Check if getAdminPassword() is returning the expected passwords
		assertEquals("password590", admin0.getAdminPassword());
		assertEquals("password590", admin1.getAdminPassword());
		assertEquals("password590", admin2.getAdminPassword());
	}
	
	
}
