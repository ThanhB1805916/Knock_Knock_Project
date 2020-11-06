package libraryTest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_access.DAOFactory;
import data_access.SQLDAO;

public class SQLDAO_Test {

	private SQLDAO dao;

	@Before
	public void setUp() {
		dao = DAOFactory.getSQLDAO();
	}

	// ----------------------------------------------------------------createCallableStatement
	@Test(expected = NullPointerException.class)
	public void connectionStringQueryParameters_isNull_when_createCallableStatement() {
		dao.createCallableStatement(null, null, null);
	}

	// ----------------------------------------------------------------executeQuery
	@Test(expected = NullPointerException.class)
	public void query_isNull_when_executeQuery() {
		dao.executeQuery(null, null);
	}

	@Test
	public void query_isEmpty_when_executeQuery() {
		try {
			dao.executeQuery("", null);
		} catch (Exception e) {
			Assert.assertTrue(e.getClass().equals(NullPointerException.class));
		}
	}

	@Test
	public void parameters_isMissing_when_executeQuery() {
		Assert.assertNull(dao.executeQuery("EXEC spPerson_GetPerson_ByID(?)", null));
	}

	@Test
	public void errorSyntax_executeQuery() {
		Assert.assertNull(dao.executeQuery("CALL spPerson_GetPerson_Byid((?);", new Object[] { 1 }));
	}
	
	@Test
	public void executeQuery() {
		Assert.assertNotNull(dao.executeQuery("CALL spPerson_GetPerson_Byid(?);", new Object[] { 1 }));
	}

	// ----------------------------------------------------------------executeNonQuery
	
	@Test(expected = NullPointerException.class)
	public void query_isNull_when_executeNonQuery() {
		dao.executeNonQuery(null, null);
	}

	@Test
	public void query_isEmpty_when_executeNonQuery() {
		try {
			dao.executeNonQuery("", null);
		} catch (Exception e) {
			Assert.assertTrue(e.getClass().equals(NullPointerException.class));
		}
	}

	@Test
	public void parameters_isMissing_when_executeNonQuery() {
		assertEquals(dao.executeNonQuery("CALL spPerson_GetPerson_Byid(?);", null), 0);
	}

	@Test
	public void errorSyntax_executeNonQuery() {
		assertEquals(dao.executeNonQuery("CALL spPerson_GetPerson_Byid((?);", new Object[] { 1 }), 0);
	}
	
	@Test
	public void executeNonQuery() {
		assertEquals(dao.executeNonQuery("CALL spPerson_GetPerson_Byid(?);", new Object[] { 1 }), 1);
	}
	
	// ----------------------------------------------------------------
	// convertResultSetToList

	@Test(expected = NullPointerException.class)
	public void resultSet_IsNull_When_convertResultSetToList() {
		dao.convertResultSetToList(null);
	}

	@Test
	public void resultSet_Normal_When_convertResultSetToList() {

	}
}
