package au.edu.unsw.soacourse.marketdataservice;

import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.marketdataservice.MarketDataUtilService")
public class MarketDataUtilServiceImpl implements MarketDataUtilService {
	// factory for web service classes
	ObjectFactory factory = new ObjectFactory();
	
	@Override
	public CurrencyConvertMarketDataResponse convertMarketData(
			CurrencyConvertMarketData parameters)
			throws ConvertMarketDataFaultMsg {
		
		String eventId = parameters.getEventSetID();
		
		// check if eventId is valid
		// TODO remove dummy variable
		if (!eventId.equals("123")) {
			throw new ConvertMarketDataFaultMsg("CONVERT MARKET FAULT MESSAGE");
		}
		
		// read data from file??
		
		// unpack file and create response
		CurrencyConvertMarketDataResponse response = factory.createCurrencyConvertMarketDataResponse();
		response.setEventSetId("345");
		return response;
	}

	@Override
	public SummaryMarketDataResponse summariseMarketData(
			SummaryMarketData parameters) throws SummaryMarketFaultMsg {
		
		String eventId = parameters.getEventSetId();

		// check if eventId is valid
		// TODO remove dummy variable
		if (!eventId.equals("123")) {
			throw new SummaryMarketFaultMsg("SUMMARY MARKET FAULT MESSAGE");
		}

		// read data from file??
		
		
		// unpack data

		SummaryMarketDataResponse response = factory.createSummaryMarketDataResponse();
		response.setEventSetId(eventId);
		response.setSec("BHP");
		response.setStartDate("16/02/1999");
		response.setEndDate("23/05/2001");
		response.setCurrencyCode("AUD");
		response.setCurrencyCode("16K");
		return response;
	}

	@Override
	public DownloadFileResponse visualiseMarketData(DownloadFile parameters)
			throws VisualiseMarketDataFaultMsg {
		String eventId = parameters.getEventSetId();

		// check if eventId is valid
		// TODO remove dummy variable
		if (!eventId.equals("123")) {
			throw new VisualiseMarketDataFaultMsg("VISUALISE MARKET FAULT MESSAGE");
		}
		
		// generate html from market data
		
		DownloadFileResponse response = factory.createDownloadFileResponse();
		response.setDataURL("some/url");
		return response;
	}
}
