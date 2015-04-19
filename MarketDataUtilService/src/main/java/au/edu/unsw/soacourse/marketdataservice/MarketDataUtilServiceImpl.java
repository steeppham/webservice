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
		
		// validate inputs
		String eventId = parameters.getEventSetID();
		MarketDataCollector collector = new MarketDataCollector(eventId);
		
		// check if eventId is valid
		if (!collector.exist()) {
			throw new ConvertMarketDataFaultMsg("EVENTSETID: " + eventId + " is unknown");
		}
		
		String currency = parameters.getTargetCurrency();
		if (!Validator.validateCurrencyCode(currency)) {
			throw new ConvertMarketDataFaultMsg("Currency code: " + currency + " is invalid");
		}
		
		String date = parameters.getTargetDate();
		if (!Validator.validateDate(date)) {
			throw new ConvertMarketDataFaultMsg("Date: " + date + " is incorrect format");
		}
		
		// get the market data
		MarketData data = null;
		try {
			data = collector.getMarketData();
		} catch (Exception e) {
			throw new ConvertMarketDataFaultMsg("Failed to load market data for: " + eventId, e);
		}
		
		CurrencyConverter converter = new CurrencyConverter(data);
		MarketData convertedData;
		try {
			convertedData = converter.convert(currency, date);
			
		} catch(Exception e) {
			throw new ConvertMarketDataFaultMsg("Failed to convert market data for: " + eventId, e);
		}
		
		// generate new id
		String newId = EventSetIdGenerator.generate(convertedData.getSec(), convertedData.getStartDate(), convertedData.getEndDate(), convertedData.getCurrencyCode());
		System.out.println("Converted market data new id: " + newId);
		collector = new MarketDataCollector(newId);
		try {
			collector.write(convertedData);
		} catch(Exception e) {
			throw new ConvertMarketDataFaultMsg("Failed to write converted market data: " + eventId, e);
		}
		
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
		MarketDataCollector collector = new MarketDataCollector(eventId);
		
		// check if eventId is valid
		if (!collector.exist()) {
			throw new VisualiseMarketDataFaultMsg("EVENTSETID: " + eventId + " is unknown");
		}
		
		// fetch the market data
		// get market data
		MarketData data = null;

		try {
			data = collector.getMarketData();
		} catch (Exception e) {
			throw new VisualiseMarketDataFaultMsg(
					"Failed to load market data for: " + eventId, e);
		}
		
		// generate html from market data
		String uri = null;
		try {
			MarketDataPresentation presentation = new MarketDataPresentation(data);
			String html = presentation.outputHtml();
			
			// write it to file
			uri = collector.writeHtml(html);
		} catch (Exception e) {
			throw new VisualiseMarketDataFaultMsg("Failed to generate html output for: " + eventId, e);
		}
		
		DownloadFileResponse response = factory.createDownloadFileResponse();
		response.setDataURL(uri);
		return response;
	}
}
