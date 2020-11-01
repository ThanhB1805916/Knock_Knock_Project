//package DataAccessAssurance;
//
//import org.junit.Test;
//
////import DataAccess.*;
////import StopWatchPack.*;
//
//public class SQLResponse {
//	
////	public void CheckStatus()
////	{
////
////		ISQLDAO dao = SQLDAO.getInstance();
////		
////		String query = "CALL spDataBase_GetStatus;";
////		
////		Thread thread = new Thread(()->
////		{
////			dao.ExecuteScalar(query, null);
////		});
////	
////		System.out.println("Time is "+StopWatch.getExecuteTime(thread) +" ms");
////	}
//	
//	@Test
//	public static void myTest()
//	{
//		System.out.println("My first test");
//	}
//	
////	public static void main(String[] args) {
////		myTest();
////	}
//}
