package au.edu.unsw.soacourse.marketservice;

import java.util.Date;

public class MarketDataSource {
	private final String sourceUrlFormat = "http://real-chart.finance.yahoo.com/table.csv?s=%s.AX&a=%s&b=%s&c=%s&d=%s&e=%s&f=%s&g=d&ignore=.csv";

	public void fetch(String stockCode, Date startDate, Date endDate) {
		
	}
	
}
