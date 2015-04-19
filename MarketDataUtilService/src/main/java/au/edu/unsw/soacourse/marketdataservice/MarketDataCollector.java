package au.edu.unsw.soacourse.marketdataservice;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

// Handles the retrieval of market data file from the system
public class MarketDataCollector {

	final String DIRECTORY = "marketdata";
	final String HOME = "/Library/Tomcat"; //System.getProperty("catalina.home"); // TODO
	final String FILE_EXT = ".csv";
	String id;
	String filePath;
	
	/**
	 * Initialise MarketeDataCollector
	 * @param id
	 */
	public MarketDataCollector(String id) {
		this.id = id;
		this.filePath = HOME +"/webapps/ROOT/" + DIRECTORY + "/" + id + FILE_EXT;
	}
	
	/**
	 * Collect the market data given the id
	 * @return
	 * @throws Exception 
	 */
	public MarketData getMarketData() throws Exception {
		MarketData data = null;
		
		// check if file exist
		if (exist()) {
			try {
				CSVReader reader = new CSVReader(new FileReader(filePath));
				List<String[]> values = reader.readAll();
				data = new MarketData(values);
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("Failed to read file: " + filePath, e);
			}
		}
		
		return data;
	}
	
	/**
	 * Get the file size of market data
	 * @return
	 */
	public String size() {
		File file = new File(filePath);
		long size = file.length();
		// TODO format file size
		return String.valueOf(size);
	}
	
	/**
	 * Comma separate a string into a list of values.
	 * @param line
	 * @return
	 */
	private List<String> commaSeparateValues(String line) {
		List<String> values = new ArrayList<String>();
		
		if (line != null) {
			String[] splitData = line.split("\\s,\\s*");
			for (String value : splitData) {
				values.add(value);
			}
		}
		return values;
	}
	
	/**
	 * Check if market data exist for the id.
	 * @return
	 */
	public boolean exist() {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 
	 * @param data
	 */
	public void write(MarketData data) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(filePath), ',', CSVWriter.NO_QUOTE_CHARACTER);
			writer.writeAll(data.getValues());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
