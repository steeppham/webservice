package au.edu.unsw.soacourse.marketdataservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {

	public static boolean validateCurrencyCode(String input) {
		return input.length() == 3;
	}
	
	public static boolean validateDate(String input) {
		return parseDate(input) != null;
	}
	
	public static Date parseDate(String input) {
		DateFormat format = new SimpleDateFormat("yyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			// do nothing
		}
		return date;
	}
}
