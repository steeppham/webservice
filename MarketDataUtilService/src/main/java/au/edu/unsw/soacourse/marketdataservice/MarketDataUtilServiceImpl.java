package au.edu.unsw.soacourse.marketdataservice;

import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.marketdataservice.MarketDataUtilService")
public class MarketDataUtilServiceImpl implements MarketDataUtilService {

	@Override
	public CurrencyConvertMarketDataResponse convertMarketData(
			CurrencyConvertMarketData parameters)
			throws ConvertMarketDataFaultMsg {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SummaryMarketDataResponse summariseMarketData(
			SummaryMarketData parameters) throws SummaryMarketFaultMsg {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DownloadFileResponse visualiseMarketData(DownloadFile parameters)
			throws VisualiseMarketDataFaultMsg {
		// TODO Auto-generated method stub
		return null;
	}

}
