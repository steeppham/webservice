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
	
	public MarketData convert(String currency, String date) throws Exception {
		
		String url = String.format(xeUrl + "/?from=AUDs&date=%s", date);
		try {
			// HTTP request
			Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0").get();
			Elements table = doc.select("#historicalRateTbl > tbody > tr");
			
			ListIterator<Element> elements = table.listIterator();
			String rate = null;
			while (elements.hasNext()) {
				Element element = elements.next();
				if (element.text().contains(currency)) {
					final Pattern pattern = Pattern.compile("<td class=\"ICTRate\">(.+)</td>");
					final Matcher matcher = pattern.matcher(element.text());
					matcher.find();
					rate = matcher.group(1);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
