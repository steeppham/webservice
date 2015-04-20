package au.edu.unsw.soacourse.marketservice;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class DownloadFileTest {

	ObjectFactory factory = new ObjectFactory();
	
	@Test
	public void downloadFile() throws Exception {
		DownloadFileRequest request = factory.createDownloadFileRequest();
		String id = EventSetIdGenerator.generate("BHP", "01-01-2014", "31-12-2015", "AUD");
		request.setEventSetID(id);
		
		TopDownSimpleServiceImpl simpleService = new TopDownSimpleServiceImpl();
		DownloadFileResponse response = simpleService.downloadFile(request);
		
		assertEquals("http://localhost:8080/marketdata/" + id + ".csv", response.getReturnData());
	}

}
