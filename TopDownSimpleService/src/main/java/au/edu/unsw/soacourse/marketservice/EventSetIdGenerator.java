package au.edu.unsw.soacourse.marketservice;

/**
 * Generates a unique event set id for a given stock code.
 */
public class EventSetIdGenerator {
	public static String generate(String stockCode, String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(stockCode.toLowerCase());
		sb.append("-");
		String s = startDate + endDate;
		sb.append(s.hashCode());
		return sb.toString();
	}
}
