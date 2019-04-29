package commonfunctions;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import org.testng.annotations.Test;
//import oracle.jdbc.driver.OracleDriver;



//This test is designed to get the Query Result Sets. You can call  Function "getDBQueryResult" for that
//To use this we need to declare & assign  Connection string in Env variables properties file and  read the same in EnvironmentVariables.java
//Query String we need to pass from the script itself
public  class QueryDB {
	public static Connection conn=null;	
	public static Statement stmt=null;
	public static ResultSet rs=null;
	//the following commented function is a sample that can be used to call the "getDBQueryResult" function

  @Test
  public static ResultSet getDBQueryResult(String queryString , String ConString) {
	  try{
	  System.out.println("inside QueryDB");
	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  conn= DriverManager.getConnection(ConString);
	  //stmt=(  State ment) conn.createStatement();
	  stmt= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	  stmt.setQueryTimeout(60);
	  rs = stmt.executeQuery(queryString);
	  System.out.println(rs.toString());
	  //System.out.println("Checking getString method"+rs.getString(1));
	  }
	  catch (Exception e){ System.out.println(e.getMessage());
		  
	  }
	  return rs;
  }
  
  
}

