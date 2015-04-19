package au.edu.unsw.soacourse.marketservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-20T02:18:35.302+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://marketservice.soacourse.unsw.edu.au", name = "TopDownSimpleService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TopDownSimpleService {

    @WebResult(name = "downloadFileResponse", targetNamespace = "http://marketservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://marketservice.soacourse.unsw.edu.au/downloadFile")
    public DownloadFileResponse downloadFile(
        @WebParam(partName = "parameters", name = "downloadFileRequest", targetNamespace = "http://marketservice.soacourse.unsw.edu.au")
        DownloadFileRequest parameters
    ) throws DownloadFileFaultMsg;

    @WebResult(name = "importMarketDataResponse", targetNamespace = "http://marketservice.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://marketservice.soacourse.unsw.edu.au/importMarketData")
    public ImportMarketDataResponse importMarketData(
        @WebParam(partName = "parameters", name = "importMarketDataRequest", targetNamespace = "http://marketservice.soacourse.unsw.edu.au")
        ImportMarketDataRequest parameters
    ) throws ImportMarketFaultMsg;
}
