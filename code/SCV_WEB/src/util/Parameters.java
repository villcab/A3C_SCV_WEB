package util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Parameters {

	protected final static String configFile = "scv_web-config";
	private static final Logger log = Logger.getLogger(Parameters.class);

	static {
		init();
	}
	
	public static String db_url;
	public static String db_driver;
	public static String db_username;
	public static String db_password;
	
	private static void init() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle(configFile);

			Parameters.db_url = rb.getString("db.url");
			Parameters.db_driver = rb.getString("db.driver");
			Parameters.db_username = rb.getString("db.username");
			Parameters.db_password = rb.getString("db.password");

		} catch (Exception e) {
			log.error("Fallo cargar el archivo de propiedades: ", e);
		}
	}
	
}
