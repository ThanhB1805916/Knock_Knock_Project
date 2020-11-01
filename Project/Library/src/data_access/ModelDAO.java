package data_access;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ModelDAO<T>{

	// SQL Data Access Object
	protected ISQLDAO dao;

	protected ModelDAO(ISQLDAO dao) {
		this.dao = dao;
	}

	// Convert Date to format (yyyy/MM/dd) String
	// If Date is null return current date
	protected String convertDate(Date date) {

		Date curDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String dateofbirth = date == null ? formatter.format(curDate) : formatter.format(date);

		return dateofbirth;
	}
}
