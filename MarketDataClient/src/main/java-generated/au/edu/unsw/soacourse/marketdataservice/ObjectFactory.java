
package au.edu.unsw.soacourse.marketdataservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.edu.unsw.soacourse.marketdataservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConvertMarketDataFault_QNAME = new QName("http://marketdataservice.soacourse.unsw.edu.au", "convertMarketDataFault");
    private final static QName _DownloadFileFault_QNAME = new QName("http://marketdataservice.soacourse.unsw.edu.au", "downloadFileFault");
    private final static QName _SummaryMarketDataFault_QNAME = new QName("http://marketdataservice.soacourse.unsw.edu.au", "summaryMarketDataFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.edu.unsw.soacourse.marketdataservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceFaultType }
     * 
     */
    public ServiceFaultType createServiceFaultType() {
        return new ServiceFaultType();
    }

    /**
     * Create an instance of {@link CurrencyConvertMarketData }
     * 
     */
    public CurrencyConvertMarketData createCurrencyConvertMarketData() {
        return new CurrencyConvertMarketData();
    }

    /**
     * Create an instance of {@link CurrencyConvertMarketDataResponse }
     * 
     */
    public CurrencyConvertMarketDataResponse createCurrencyConvertMarketDataResponse() {
        return new CurrencyConvertMarketDataResponse();
    }

    /**
     * Create an instance of {@link DownloadFile }
     * 
     */
    public DownloadFile createDownloadFile() {
        return new DownloadFile();
    }

    /**
     * Create an instance of {@link DownloadFileResponse }
     * 
     */
    public DownloadFileResponse createDownloadFileResponse() {
        return new DownloadFileResponse();
    }

    /**
     * Create an instance of {@link SummaryMarketData }
     * 
     */
    public SummaryMarketData createSummaryMarketData() {
        return new SummaryMarketData();
    }

    /**
     * Create an instance of {@link SummaryMarketDataResponse }
     * 
     */
    public SummaryMarketDataResponse createSummaryMarketDataResponse() {
        return new SummaryMarketDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketdataservice.soacourse.unsw.edu.au", name = "convertMarketDataFault")
    public JAXBElement<ServiceFaultType> createConvertMarketDataFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_ConvertMarketDataFault_QNAME, ServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketdataservice.soacourse.unsw.edu.au", name = "downloadFileFault")
    public JAXBElement<ServiceFaultType> createDownloadFileFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_DownloadFileFault_QNAME, ServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketdataservice.soacourse.unsw.edu.au", name = "summaryMarketDataFault")
    public JAXBElement<ServiceFaultType> createSummaryMarketDataFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_SummaryMarketDataFault_QNAME, ServiceFaultType.class, null, value);
    }

}
