package au.edu.unsw.soacourse.marketservice;

import java.util.ArrayList;

public class Input {
	public ArrayList<String> importedMarketData = new ArrayList<String>();

	public ArrayList<String> getImportedMarketData() {
		return importedMarketData;
	}

	public void setImportedMarketData(String input) {
		importedMarketData.add(input);
	}
}
