package au.edu.unsw.soacourse.marketservice;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImportMarketDataTest {
	
	ObjectFactory factory = new ObjectFactory();

	@Test
	public void importMarketData() throws Exception {
		ImportMarketDataRequest request = factory.createImportMarketDataRequest();
		request.setSec("BHP");
		request.setStartDate("01-01-2014");
		request.setEndDate("31-12-2015");
		request.setDataSource("https://au.finance.yahoo.com");
		
		TopDownSimpleServiceImpl simpleService = new TopDownSimpleServiceImpl();
		ImportMarketDataResponse response = simpleService.importMarketData(request);
		
		String expectedId = EventSetIdGenerator.generate("BHP", "01-01-2014", "31-12-2015");
		assertEquals(expectedId, response.getReturnData());
	}

}
