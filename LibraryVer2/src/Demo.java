import java.util.HashMap;

import connection.ConnectionString;
import data_access.DAOFactory;
import data_access.DAOFactoryVer2;
import data_access.SQLDAO;
import data_access.SQLDAOImp;
import data_access.person_access.PersonDAO;
import data_access.person_access.imp.PersonDAO_MySQL;
import data_model.MessageTable;
import data_model.PersonTable;
import data_model.RoomTable;

public class Demo {

	public static void main(String[] args) {
//		DAOFactory fac = DAOFactory.getInstance();
//		
//		PersonTable person = fac.getPeronDAO().get(1);
//		
//		System.out.println(person.getName());
//		
//		RoomTable room = fac.getRoomDAO().get(1);
//		
//		System.out.println(room.getName());
//		
//		MessageTable message = fac.getMessageDAO().get(1);
//		
//		System.out.println(message.getMessagecontent());
//		DAOFactoryVer2 fac = new DAOFactoryVer2();
//		fac.Assign(SQLDAO.class, SQLDAOImp.class);
//		SQLDAO dao = fac.get(SQLDAO.class);
//		SQLDAO dao1 = fac.get(SQLDAO.class);
//		System.out.println(dao1.equals(dao));
//		fac.Assign(PersonDAO.class, SQLDAOImp.class);
//		PersonTable person = fac.get(PersonDAO.class).get(1);
//		System.out.println(person.getName());
		DAOFactoryVer2 obj = (DAOFactoryVer2)null;
	}
	
}
