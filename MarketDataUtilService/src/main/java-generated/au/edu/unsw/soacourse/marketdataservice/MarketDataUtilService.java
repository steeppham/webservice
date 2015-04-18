package au.edu.unsw.soacourse.marketdataservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-18T19:58:50.828+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au", name = "MarketDataUtilService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MarketDataUtilService {

    @WebResult(name = "currencyConvertMarketDataResponse", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://marketdataservice.soacourse.unsw.edu.au/convertMarketData")
    public CurrencyConvertMarketDataResponse convertMarketData(
        @WebParam(partName = "parameters", name = "currencyConvertMarketData", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au")
        CurrencyConvertMarketData parameters
    ) throws ConvertMarketDataFaultMsg;

    @WebResult(name = "summaryMarketDataResponse", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://marketdataservice.soacourse.unsw.edu.au/summariseMarketData")
    public SummaryMarketDataResponse summariseMarketData(
        @WebParam(partName = "parameters", name = "summaryMarketData", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au")
        SummaryMarketData parameters
    ) throws SummaryMarketFaultMsg;

    @WebResult(name = "downloadFileResponse", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://marketdataservice.soacourse.unsw.edu.au/visualiseMarketData")
    public DownloadFileResponse visualiseMarketData(
        @WebParam(partName = "parameters", name = "downloadFile", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au")
        DownloadFile parameters
    ) throws VisualiseMarketDataFaultMsg;
}
