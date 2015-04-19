package au.edu.unsw.soacourse.marketdataservice;

public class ResourceConstants {

	public static final String DATA_DIR = "marketdata";
	
	public static final String PUBLIC_DIR = System.getProperty("catalina.home") + "/webapps/ROOT";
	
	public static final String CSV_EXT = ".csv";
	
	public static final String HTML_EXT = ".html";
	
	public static final String HOST_ADDRESS = "http://localhost:8080";
	
	public static String getLocalResource(String id) {
		return PUBLIC_DIR + "/" + DATA_DIR + "/" + id + CSV_EXT;
	}
	
	public static String getPublicResource(String id) {
		return HOST_ADDRESS + "/" + DATA_DIR + "/" + id + CSV_EXT;
	}
	
	public static String getLocalResourceHtml(String id) {
		return PUBLIC_DIR + "/" + DATA_DIR + "/" + id + HTML_EXT;
	}
	
	public static String getPublicResourceHtml(String id) {
		return HOST_ADDRESS + "/" + DATA_DIR + "/" + id + HTML_EXT;
	}
}
