package au.edu.unsw.soacourse.marketservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class MarketDataSource {
	private final String sourceUrlFormat = "http://real-chart.finance.yahoo.com/table.csv?s=%s.AX&a=%s&b=%s&c=%s&d=%s&e=%s&f=%s&g=d&ignore=.csv";

	public void fetch(String stockCode, Date startDate, Date endDate, String eventSetId) throws Exception {
		URL yahoo;
		String url = String.format(sourceUrlFormat, stockCode,
				startDate.getMonth(), startDate.getDate(), startDate.getYear() + 1900,
				endDate.getMonth(), endDate.getDate(), endDate.getYear() + 1900);
		
		try {
			yahoo = new URL(url);
			System.out.println("Accessing url: " + url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));
			
			String inputLine;
			boolean first = true;
			File localFile = File.createTempFile(eventSetId, ".txt");
			FileWriter fileWriter = new FileWriter(localFile, true);
			
			while ((inputLine = in.readLine()) != null) {
				String temp[];
				String formatted;
				if (first == true) {
					// System.out.println(inputLine);
					temp = inputLine.split(",");
					formatted = "Sec" + "," + temp[0] + "," + temp[1] + ","
							+ temp[2] + "," + temp[3] + "," + temp[4] + ","
							+ temp[5] + "," + temp[6] + "\n";
					System.out.println(formatted);
					fileWriter.write(formatted);

					first = false;
				} else {
					temp = inputLine.split(",");
					formatted = stockCode + "," + temp[0] + ",AUD" + temp[1] + ",AUD"
							+ temp[2] + ",AUD" + temp[3] + ",AUD" + temp[4]
							+ "," + temp[5] + ",AUD" + temp[6] + "\n";
					fileWriter.write(formatted);
				}
			}
			
			// close streams
			in.close();
			fileWriter.close();
			
			String path = ResourceConstants.getLocalResource(eventSetId);
			System.out.println(path);
			File source = localFile;
			File dest = new File(path);
			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("Failed to copy resource to public directory", e);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to fetch market source data", e);
		}
	}	
}
