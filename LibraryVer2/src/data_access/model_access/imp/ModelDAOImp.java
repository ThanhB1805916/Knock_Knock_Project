package data_access.model_access.imp;

import java.util.List;

import data_access.SQLDAO;
import data_access.model_access.ModelDAO;
import data_access.model_access.ModelDAO_Query;
import data_model.ModelTable;

public abstract class ModelDAOImp<T extends ModelTable<T>> implements ModelDAO<T>, ModelDAO_Query {

	// SQL Data Access Object
	protected SQLDAO dao;
	protected Class<T> currentClass;

	protected ModelDAOImp(SQLDAO dao, Class<T> currentClass) {
		this.dao = dao;
		this.currentClass = currentClass;
	}

	public int booleanToBit(boolean value) {
		return value == true ? 1 : 0;
	}

	@Override
	public T get(int id) {
		List<T> dataTable = dao.executeQuery(currentClass, getQuery(), new Object[] { id });
		T model = null;
		if (dataTable != null) {
			model = dataTable.get(0);
		}
		return model;
	}
}
