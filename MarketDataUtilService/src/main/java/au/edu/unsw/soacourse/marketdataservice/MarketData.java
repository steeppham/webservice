package au.edu.unsw.soacourse.marketdataservice;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

// Represents a market data
public class MarketData {
	private String sec;
	private String startDate;
	private String endDate;
	private String currencyCode;
	private List<String[]> values;
	
	/**
	 * Initialise market data from a set of values.
	 * @param values
	 */
	public MarketData(List<String[]> values) {
		this.values = values;
		summariseData();
	}
	
	// Process the data and summarise
	private void summariseData() {
		// get the sec, currency code from first data value
		if (values.size() > 1) {
			sec = values.get(1)[0];
			Pattern pattern = Pattern.compile("[a-zA-Z]+");
			Matcher matcher = pattern.matcher(values.get(1)[2]);
			if (matcher.find()) {
				currencyCode = matcher.group();
			}
			endDate = values.get(1)[1];
			startDate = values.get(values.size() - 1)[1];
		}
	}
	
	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getSec() {
		return sec;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public List<String[]> getValues() {
		return values;
	}
	
	private double extractMarketValue(String marketValue) throws Exception {
		double value = 0;
		Pattern pattern = Pattern.compile("[a-zA-Z]+(.*)");
		Matcher matcher = pattern.matcher(marketValue);
		if (matcher.find()) {
			String textValue  = matcher.group(1);
			value = Double.valueOf(textValue);
		} else {
			throw new Exception("Could not extract market value from the text: " + marketValue);
		}
		
		return value;
	}

	/**
	 * Converts the market values to the specified currency and rate. It will return
	 * a new instance of MarketData;
	 * @param currency
	 * @param rate
	 * @return
	 */
	public MarketData convert(String currency, double rate) throws Exception {
		List<String[]> newValues = new ArrayList<String[]>(values.size());
		// initialise with column headers
		newValues.add(values.get(0));
		
		// iterate through values and convert the market values
		for (int i = 1; i < values.size(); i++) {
			String[] row = values.get(i);
			String[] newRow = new String[row.length];
			newRow[0] = row[0];
			newRow[1] = row[1];
			newRow[2] = convertInternal(row[2], currency, rate);
			newRow[3] = convertInternal(row[3], currency, rate);
			newRow[4] = convertInternal(row[4], currency, rate);
			newRow[5] = convertInternal(row[5], currency, rate);
			newRow[6] = row[6];
			newRow[7] = convertInternal(row[7], currency, rate);
			
			newValues.add(newRow);
		}
		
		return new MarketData(newValues);
	}
	
	/**
	 * Map currency conversion to a new string
	 * @param original
	 * @param rate
	 * @return
	 */
	private String convertInternal(String original, String currency, double rate) throws Exception {
		double value = extractMarketValue(original);
		double converted = value * rate;
		DecimalFormat df = new DecimalFormat("#.##");
		String round = df.format(converted);
		return currency + round;
	}
}
