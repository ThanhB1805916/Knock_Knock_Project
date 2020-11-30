package data_access.model_access.imp;

import data_access.SQLDAO;

public abstract class ModelDAOImp<T> {

	// SQL Data Access Object
	protected SQLDAO dao;

	protected ModelDAOImp(SQLDAO dao) {
		this.dao = dao;
	}
	
	public int booleanToBit(boolean value)
	{
		return value == true ? 1 : 0;
	}
}
