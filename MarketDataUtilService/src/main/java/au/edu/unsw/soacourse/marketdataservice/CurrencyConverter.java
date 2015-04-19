package au.edu.unsw.soacourse.marketdataservice;

import java.io.IOException;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Converts the currency of the market data.
 * @author stephen
 *
 */
public class CurrencyConverter {
	
	final String xeUrl = "http://www.xe.com/currencytables";
	
	MarketData marketData;
	
	
	public CurrencyConverter(MarketData marketData) {
		this.marketData = marketData;
	}
	
	/**
	 * Convert the input market data to the specified currency and output the new market data.
	 * @param currency
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public MarketData convert(String currency, String date) throws Exception {
		double rate = fetchExchangeRate(currency, date);
		MarketData convertedMarketData = marketData.convert(currency, rate);
		return convertedMarketData;
	}
	
	/**
	 * Fetch the exchange rate for AUD to the input currency on the given date.
	 * @param currency
	 * @param date
	 * @return
	 */
	private double fetchExchangeRate(String currency, String date) throws Exception {
		
		String url = String.format(xeUrl + "/?from=AUDs&date=%s", date);
		double rate = 0;
		String rateText = null;
		
		try {
			// HTTP request
			Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0").get();
			Elements table = doc.select("#historicalRateTbl > tbody > tr");
			
			ListIterator<Element> elements = table.listIterator();
			
			while (elements.hasNext()) {
				Element element = elements.next();
				if (element.text().contains(currency)) {
					final Pattern pattern = Pattern.compile("<td class=\"ICTRate\">(.+)</td>");
					final Matcher matcher = pattern.matcher(element.text());
					matcher.find();
					//rate = matcher.group(1); // TODO find matcher for this!
					rateText = "0.7856932751";
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Failed to access URL: " + url, e);
		}
		
		// parse the rate text to double value if it was found
		if (rateText != null) {
			rate = Double.valueOf(rateText);
		} else {
			throw new Exception("Could not find currency exchange rate for: " + currency);
		}
		
		return rate;
	}

}
