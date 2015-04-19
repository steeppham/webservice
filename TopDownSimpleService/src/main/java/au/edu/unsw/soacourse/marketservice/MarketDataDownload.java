package au.edu.unsw.soacourse.marketservice;

import java.io.File;

public class MarketDataDownload {
	String eventSetId;
	
	public MarketDataDownload(String eventSetId) {
		this.eventSetId = eventSetId;
	}
	
	public boolean exist() {
		// TODO
		File file = new File("/Library/Tomcat/webapps/ROOT/marketdata/" + eventSetId + ".csv");
		return file.exists();
	}
	
	public String getDownloadUrl() {
		// TODO
		String url = null;
		if (exist()) {
			url = "http://localhost:8080/marketservice/" + eventSetId + ".csv";
		}
		return url;
	}
}
