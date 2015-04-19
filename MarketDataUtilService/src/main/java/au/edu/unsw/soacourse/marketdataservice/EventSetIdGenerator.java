package au.edu.unsw.soacourse.marketdataservice;

/**
 * Generates a unique event set id for a given stock code.
 */
public class EventSetIdGenerator {
	public static String generate(String stockCode, String startDate, String endDate, String currency) {
		StringBuilder sb = new StringBuilder();
		sb.append(stockCode.toLowerCase());
		String s = startDate + endDate;
		sb.append(s.hashCode());
		sb.append(currency.toLowerCase());
		return sb.toString();
	}
}
