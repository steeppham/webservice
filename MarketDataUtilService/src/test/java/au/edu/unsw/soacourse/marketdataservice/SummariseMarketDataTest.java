package au.edu.unsw.soacourse.marketdataservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class SummariseMarketDataTest {

	ObjectFactory factory = new ObjectFactory();
	
	@Test
	public void validEventSetIdTest() throws SummaryMarketFaultMsg {
		MarketDataUtilServiceImpl marketDataService = new MarketDataUtilServiceImpl();
		SummaryMarketData request = factory.createSummaryMarketData();
		
		// test with test file in webapp/ROOT/marketdata/123.csv
		String eventSetId = "test-123";
		request.setEventSetId(eventSetId);
		
		SummaryMarketDataResponse response = marketDataService.summariseMarketData(request);
		assertEquals(response.getEventSetId(), eventSetId);
		assertEquals(response.getSec(), "BHP");
		assertEquals(response.getStartDate(), "2015-03-25");
		assertEquals(response.getEndDate(), "2015-03-27");
		assertEquals(response.getCurrencyCode(), "AUD");
		assertEquals(response.getFileSize(), "251");
	}

}
