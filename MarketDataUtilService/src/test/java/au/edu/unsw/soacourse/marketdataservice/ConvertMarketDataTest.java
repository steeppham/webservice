package au.edu.unsw.soacourse.marketdataservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConvertMarketDataTest {

	ObjectFactory factory = new ObjectFactory();
	
	@Test
	public void convertAUDtoUSD() throws Exception {
		MarketDataUtilServiceImpl marketDataService = new MarketDataUtilServiceImpl();
		CurrencyConvertMarketData request = factory.createCurrencyConvertMarketData();
		
		// test with test file in webapp/ROOT/marketdata/123.csv
		String eventSetId = "test-123";
		request.setEventSetID(eventSetId);
		
		CurrencyConvertMarketDataResponse response = marketDataService.convertMarketData(request);
		
		assertEquals(response.getEventSetId(), eventSetId);
	}
	
	@Test
	public void currencyConverterTest() throws Exception {
		String eventSetId = "test-123";
		MarketDataCollector collector = new MarketDataCollector(eventSetId);
		MarketData data = collector.getMarketData();
		
		CurrencyConverter converter = new CurrencyConverter(data);
		data = converter.convert("USD", "2015-03-25");
		
		assertEquals(data.getCurrencyCode(), "USD");
		assertEquals(data.getValues().get(1)[3], "USD24.12");
	}

}
