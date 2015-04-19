package au.edu.unsw.soacourse.marketservice;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.marketservice.TopDownSimpleService")
public class TopDownSimpleServiceImpl implements TopDownSimpleService {

	ObjectFactory factory = new ObjectFactory();

	@Override
	public ImportMarketDataResponse importMarketData(
			ImportMarketDataRequest parameters) throws ImportMarketFaultMsg {

		String sec = parameters.getSec();
		String startDateText = parameters.getStartDate();
		String endDateText = parameters.getEndDate();
		
		if (!Validator.validateSec(sec)) {
			// assume that we only want a SEC value of length 3
			String msg = "SEC code should be exactly 3 characters longs";
			String code = "ERR_SEC";

			// TODO: SOAP Fault handling should come here ...
			// TODO: create a ServieFaultType 'fault' from ObjectFactory
			ServiceFaultType sft = factory.createServiceFaultType();
			sft.setErrcode(code);
			sft.setErrtext(msg);
			ImportMarketFaultMsg imfm = new ImportMarketFaultMsg(msg, sft);
			// TODO: prepare 'fault' object: set errorcode and errortext
			// TODO: throw new importMarketFaultMsg(msg,fault)
			throw imfm;
		}
		
		if (!Validator.validateDate(startDateText)) {
			throw new ImportMarketFaultMsg("Start date incorrect format");
		}
		Date startDate = Validator.parseDate(startDateText);
		
		
		if (!Validator.validateDate(endDateText)) {
			throw new ImportMarketFaultMsg("End date incorrect format");
		}
		Date endDate = Validator.parseDate(endDateText);
		
		// generate unique event set id
		String eventSetId = EventSetIdGenerator.generate(sec, startDateText, endDateText, "AUD");
		
		MarketDataSource dataSource = new MarketDataSource();
		try {
			dataSource.fetch(sec, startDate, endDate, eventSetId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ImportMarketFaultMsg("Failed to fetch market data source", e);
		}
		
		ImportMarketDataResponse res = factory.createImportMarketDataResponse();
		res.setReturnData(eventSetId);
		return res;
	}

	@Override
	public DownloadFileResponse downloadFile(DownloadFileRequest parameters)
			throws DownloadFileFaultMsg {
		String eventSetId = parameters.getEventSetID();
		
		MarketDataDownload download = new MarketDataDownload(eventSetId);
		if (!download.exist()) {
			throw new DownloadFileFaultMsg("Unknonw eventSetId was given: " + eventSetId);
		}
		
		String url = download.getDownloadUrl();
		DownloadFileResponse res = factory.createDownloadFileResponse();
		res.setReturnData(url);
		return res;
	}
}
