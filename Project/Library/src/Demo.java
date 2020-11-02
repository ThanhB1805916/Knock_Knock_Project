
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data_access.*;
import data_access.person_access.IPersonDAO;
import data_access.person_access.PersonDAO;
import data_model.PersonTable;

public class Demo {

	public static void main(String[] args) {

		PersonDAO dao = new PersonDAO(new SQLDAO());
	
		@SuppressWarnings("deprecation")
		Date date = new Date("10/05/2013");
		
		PersonTable person = new PersonTable(0, "user200", "1234", "User200", true, "1123456798", date, null);
		
		
		
//		System.out.println(dao.add(person)); 
		//thanh
		person.setDateofbirth(date);			
		System.out.println(dao.update(person)); 
	}

	
}
