package au.edu.unsw.soacourse.marketdataservice;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

// Handles the retrieval of market data file from the system
public class MarketDataCollector {
	String id;
	String filePath;
	
	/**
	 * Initialise MarketeDataCollector
	 * @param id
	 */
	public MarketDataCollector(String id) {
		this.id = id;
		this.filePath = ResourceConstants.getLocalResource(id);
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
				reader.close();
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
		return String.valueOf(size);
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
	public void write(MarketData data) throws Exception {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(filePath), ',', CSVWriter.NO_QUOTE_CHARACTER);
			writer.writeAll(data.getValues());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Failed to write market data to local");
		}
	}

	/**
	 * 
	 * @param html
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public String writeHtml(String html) throws UnsupportedEncodingException, IOException {
		String htmlPath = ResourceConstants.getLocalResourceHtml(id);
		File output = new File(htmlPath);
		Files.write(output.toPath(), html.getBytes("UTF-8"));
		return ResourceConstants.getPublicResourceHtml(id);
	}
	
}
