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
			throw new ConvertMarketDataFaultMsg("EVENTSETID: " + eventId + " is unknown");
		}
		
		// get the market data
		MarketData data = null;
		try {
			data = collector.getMarketData();
		} catch (Exception e) {
			throw new ConvertMarketDataFaultMsg("Failed to load market data for: " + eventId, e);
		}
		
		String currency = parameters.getTargetCurrency();
		String date = parameters.getTargetDate();
		
		CurrencyConverter converter = new CurrencyConverter(data);
		MarketData convertedData;
		try {
			convertedData = converter.convert(currency, date);
			
		} catch(Exception e) {
			throw new ConvertMarketDataFaultMsg("Failed to convert market data for: " + eventId, e);
		}
		
		// generate new id
		String newId = "test-345";
		collector = new MarketDataCollector(newId);
		collector.write(convertedData);
		
		// generate summary response
		CurrencyConvertMarketDataResponse response = factory.createCurrencyConvertMarketDataResponse();
		response.setEventSetId(newId);
		return response;
	}

	@Override
	public SummaryMarketDataResponse summariseMarketData(
			SummaryMarketData parameters) throws SummaryMarketFaultMsg {
		
		String eventId = parameters.getEventSetId();
		MarketDataCollector collector = new MarketDataCollector(eventId);
		
		// check if eventId is valid
		if (!collector.exist()) {
			throw new SummaryMarketFaultMsg("EVENTSETID: " + eventId + " is unknown");
		}

		
		// get market data
		MarketData data = null; 
		
		try {
			data = collector.getMarketData();
		} catch (Exception e) {
			throw new SummaryMarketFaultMsg("Failed to load market data for: " + eventId, e);
		}
		
		// generate summary response
		SummaryMarketDataResponse response = factory.createSummaryMarketDataResponse();
		
		if (data != null) {
			response.setEventSetId(eventId);
			response.setSec(data.getSec());
			response.setStartDate(data.getStartDate());
			response.setEndDate(data.getEndDate());
			response.setCurrencyCode(data.getCurrencyCode());
			response.setFileSize(collector.size());
		}
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
