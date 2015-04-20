package au.edu.unsw.soacourse.marketdataservice;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class VisualiseMarketDataTest {
	ObjectFactory factory = new ObjectFactory();

	@Test
	public void visualiseMarketData() throws Exception {
		DownloadFile request = factory.createDownloadFile();
		request.setEventSetId("test-123");
		
		MarketDataUtilServiceImpl marketDataService = new MarketDataUtilServiceImpl();
		DownloadFileResponse response = marketDataService.visualiseMarketData(request);
		
		assertEquals("http://localhost:8080/marketdata/test-123.html", response.getDataURL());
	}

}
