package data_access;

public abstract class ModelDAOImp<T> {

	// SQL Data Access Object
	protected SQLDAO dao;

	protected ModelDAOImp(SQLDAO dao) {
		this.dao = dao;
	}
}
