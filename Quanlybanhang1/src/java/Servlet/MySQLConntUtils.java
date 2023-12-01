package Servlet;





import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
		
public class MySQLConntUtils {
	
	public static Connection cnn = null;
	
	static Connection getMySQLConnection() throws ClassNotFoundException,SQLException{
			if (cnn == null) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                cnn= DriverManager.getConnection("jdbc:mysql://localhost:3306/quanhlybanhang", "root", "");
	                System.out.println("Connected to the database");
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	                throw e;
	            }
	        }
	        return cnn;
	}
}


//    private static class SQLException extends Exception {
//
//        public SQLException() {
//        }
//    }
//   public class MySQLConntUtils {
//public static Connection getMySQLConnection() throws ClassNotFoundException,
//SQLException,
//        java.sql.SQLException{
//String hostname="localhost";
//String dbName=" Quanlybanhang1";
//String username="root";
//String password="";
//return getMySQLConnection(hostname,dbName,username, password);
//}
//private static Connection getMySQLConnection(String hostname, String dbName,
//String username, String password)
//throws ClassNotFoundException, SQLException, java.sql.SQLException {
//Class.forName("com.mysql.jdbc.Driver");
//String connectionURL="jdbc:mysql://"+hostname+":3306/"+dbName;
//Connection conn=DriverManager.getConnection(connectionURL, username,
//password);
//return conn;
//
//}
//   } }