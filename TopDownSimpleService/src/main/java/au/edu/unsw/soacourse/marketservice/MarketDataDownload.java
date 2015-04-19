package au.edu.unsw.soacourse.marketservice;

import java.io.File;

public class MarketDataDownload {
	String eventSetId;
	
	public MarketDataDownload(String eventSetId) {
		this.eventSetId = eventSetId;
	}
	
	public boolean exist() {
		File file = new File(ResourceConstants.getLocalResource(eventSetId));
		return file.exists();
	}
	
	public String getDownloadUrl() {
		// TODO
		String url = null;
		if (exist()) {
			url = ResourceConstants.getPublicResource(eventSetId);
		}
		return url;
	}
}
