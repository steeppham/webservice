package au.edu.unsw.soacourse.marketdataservice;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
