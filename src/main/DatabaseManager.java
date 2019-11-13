/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jai
 */
public class DatabaseManager {
	public static final String DB_NAME = "UserDB";
	private static final String CONNECTION_STRING = "jdbc:sqlite:"+new File(CustomSettings.getDestinationFolder().getAbsolutePath(),DB_NAME+".db").getAbsolutePath();
	private static Connection connection;
	private static Statement statement;
	private static final String TABLE_NAME ="USERDETAILS";
	private static final String COLUMN_USERID = "USERID";
	private static final String COLUMN_PASSWORD = "PASSWORD";
        private static boolean isShutDown;

        //one thing reamining ton che check if database file exists or not ot then create it with the table 
	private DatabaseManager() throws SQLException{
		connection = DriverManager.getConnection(CONNECTION_STRING);
		statement = connection.createStatement();
	}
        
        public static void createDatabaseManager() throws SQLException{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
            createTable();
        }

	private static void createTable() throws SQLException{
		statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+COLUMN_USERID+","+COLUMN_PASSWORD+")");
	}
	
        
        public static boolean recordExists(String userID,String password){
		try(ResultSet result = statement.executeQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_USERID+" = '"+userID+"' AND "+COLUMN_PASSWORD+" = '"+password+"'")){
			if (result.next()) return true;
		}catch(SQLException e){
			System.out.println(e);
		}
            return false;
	}

	private static synchronized void insertRecord(String userID,String password) throws SQLException{
            //check whether the statement returns is scuuessful;
		statement.execute("INSERT INTO "+TABLE_NAME+"("+COLUMN_USERID+","+COLUMN_PASSWORD+") values(\""+userID+"\",\""+password+"\")");	
	}

	public synchronized void deleteRecord(String userID,String password) throws SQLException{
		statement.execute("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_USERID+" = '"+userID+"' AND "+COLUMN_PASSWORD+"= '"+password+"'");
	}

//	public void dropTable()throws SQLException{
//		statement.execute("DROP TABLE "+TABLE_NAME);
//	}

//	public void displayTable()throws SQLException{
//		ResultSet results = statement.executeQuery("SELECT * FROM USERDETAILS");
//		while (results.next()) {
//			System.out.println("Name:"+results.getString("USERID")
//				+" Password:"+results.getString("PASSWORD"));
//		}
//		results.close();
//	}
        
        public static boolean processUserLogin(String name,String password){
            if(isShutDown) return false; 
            if(recordExists(name, password))
                return true;
            return false;
        }
        
        
        public static boolean processUserRegistration(String name,String password){
            if(isShutDown) return false;
            try {
                insertRecord(name, password);
                return true;
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

	public static void close() throws SQLException{
                isShutDown = true;
		statement.close();
		connection.close();
	}
}