package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ServiceProvider {

	private static final Logger log = Logger.getLogger(ServiceProvider.class);

	private static String URL = Parameters.db_url;
	private static String DRIVER = Parameters.db_driver;
	private static String USERNAME = Parameters.db_username;
	private static String PASSWORD = Parameters.db_password;

	public static synchronized Connection openConnection() throws SQLException {
		Connection conexion = null;
		try {
			Class.forName(DRIVER).newInstance();
			conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			log.error("Error al obtener conexion postgres: " + e);
		}
		return conexion;
	}
	
}
