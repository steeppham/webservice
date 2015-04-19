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
		request.setTargetCurrency("USD");
		request.setTargetDate("2015-03-25");
		
		CurrencyConvertMarketDataResponse response = marketDataService.convertMarketData(request);
		
		assertEquals(response.getEventSetId(), "test-345");
	}
	
	@Test
	public void currencyConverterTest() throws Exception {
		String eventSetId = "test-123";
		MarketDataCollector collector = new MarketDataCollector(eventSetId);
		MarketData data = collector.getMarketData();
		
		CurrencyConverter converter = new CurrencyConverter(data);
		data = converter.convert("USD", "2015-03-25");
		
		assertEquals( "USD", data.getCurrencyCode());
		assertEquals( "USD24.12", data.getValues().get(1)[2]);
		assertEquals( "USD24.41", data.getValues().get(1)[3]);
		assertEquals( "USD24.09", data.getValues().get(1)[4]);
		assertEquals( "USD24.16", data.getValues().get(1)[5]);
		assertEquals( "USD24.16", data.getValues().get(1)[7]);
	}
}
