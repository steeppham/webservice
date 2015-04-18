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
		MarketDataCollector collector = new MarketDataCollector(eventId);
		
		// check if eventId is valid
		if (!collector.exist()) {
			throw new ConvertMarketDataFaultMsg("CONVERT MARKET FAULT MESSAGE");
		}
		
		// get the market data
		MarketData data = collector.getMarketData();
		
		// generate summary response
		CurrencyConvertMarketDataResponse response = factory.createCurrencyConvertMarketDataResponse();
		response.setEventSetId("345");
		return response;
	}

	@Override
	public SummaryMarketDataResponse summariseMarketData(
			SummaryMarketData parameters) throws SummaryMarketFaultMsg {
		
		String eventId = parameters.getEventSetId();
		MarketDataCollector collector = new MarketDataCollector(eventId);
		
		// check if eventId is valid
		if (!collector.exist()) {
			throw new SummaryMarketFaultMsg("SUMMARY MARKET FAULT MESSAGE");
		}

		// get market data
		MarketData data = collector.getMarketData();
		
		// generate summary response
		SummaryMarketDataResponse response = factory.createSummaryMarketDataResponse();
		response.setEventSetId(eventId);
		response.setSec(data.getSec());
		response.setStartDate(data.getStartDate());
		response.setEndDate(data.getEndDate());
		response.setCurrencyCode(data.getCurrencyCode());
		response.setFileSize(collector.size());
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
