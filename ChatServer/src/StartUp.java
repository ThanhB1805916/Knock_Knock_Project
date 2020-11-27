import data_access.DAOFactory;
import data_access.SQLDAO;
import data_access.SQLDAOImp;
import data_access.person_access.PersonDAO;
import data_access.person_access.imp.PersonDAO_MySQL;
import model.converter.MessageConverter;
import model.converter.PersonConverter;
import model.converter.RoomConverter;
import model.sendmodel.Message;
import model.sendmodel.Person;
import model.sendmodel.Room;
import socket.Server;

public class StartUp {

	public static void main(String[] args) {
//		Server.getInstance().connect();
//		Factory.Assign(SQLDAO.class, SQLDAOImp.class);
//		SQLDAO dao = Factory.get(SQLDAO.class);
		try {
			Object a = SQLDAOImp.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
