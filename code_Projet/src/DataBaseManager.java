import java.sql.*;

public class DataBaseManager{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/";
   // Database credentials
   static final String USER = "admin";
   static final String PASS = "123";
   Connection conn = null;
   Statement stmt = null;

   public void CreateDataBase(String statement) throws SQLException, ClassNotFoundException {
	   
      Class.forName(JDBC_DRIVER);// Register JDBC driver
      conn = DriverManager.getConnection(DB_URL, USER, PASS);// Open a connection
      stmt = conn.createStatement();// Execute a query to create database         
      stmt.executeUpdate("CREATE DATABASE "+statement+"");// Here is where the database is actually created
      
      } 
   public void ExecuteSql(String DataBaseName,String statement) throws SQLException {
       stmt.executeUpdate("USE " + DataBaseName+";"+statement);
   }
   public void InsertAccount(String DataBaseName,String Table,Account acc) throws SQLException {
	   String statement="INSERT INTO SavingAccounts VALUES "+"("+acc.getNumber()+","+acc.getName()+","+acc.getBalance()+");";
       stmt.executeUpdate("USE " + DataBaseName+";"+statement);
   }
   public String SelectAll(String DataBaseName,String TableName) throws SQLException {
	   ResultSet rs= stmt.executeQuery("USE " + DataBaseName+";"+TableName);
	   String tableInfo="";
	   while (rs.next()) {
	        String id = rs.getString("id");
	        String name = rs.getString("name");
	        double balance = rs.getDouble("balance");
	        double rate = rs.getDouble("rate");
	        tableInfo =tableInfo + String.format("\n %s\t %s\tBalance= %.2f\t(rate =%.2f ) ",id,name,balance,rate);
	    }
	   return tableInfo;
   }
   public void CloseConnection() throws SQLException {
	   stmt.close();
       conn.close();
       stmt =null;
       conn =null;
   }
   public void LinkToDB() throws SQLException {
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);// Open a connection
	      stmt = conn.createStatement();// Execute a query to create database 
   }
   
}