package WorkingWithDataBase;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static String url = "jdbc:mysql://localhost:3306/myfutureadventures";
	private static String username = "root";
	private static String password = "root";
	
    public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
       Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		return DriverManager.getConnection(url, username, password);
	    }
}
