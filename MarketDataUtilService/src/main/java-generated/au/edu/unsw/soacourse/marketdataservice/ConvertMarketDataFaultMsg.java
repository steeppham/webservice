
package au.edu.unsw.soacourse.marketdataservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-20T16:50:08.782+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "convertMarketDataFault", targetNamespace = "http://marketdataservice.soacourse.unsw.edu.au")
public class ConvertMarketDataFaultMsg extends Exception {
    
    private au.edu.unsw.soacourse.marketdataservice.ServiceFaultType convertMarketDataFault;

    public ConvertMarketDataFaultMsg() {
        super();
    }
    
    public ConvertMarketDataFaultMsg(String message) {
        super(message);
    }
    
    public ConvertMarketDataFaultMsg(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertMarketDataFaultMsg(String message, au.edu.unsw.soacourse.marketdataservice.ServiceFaultType convertMarketDataFault) {
        super(message);
        this.convertMarketDataFault = convertMarketDataFault;
    }

    public ConvertMarketDataFaultMsg(String message, au.edu.unsw.soacourse.marketdataservice.ServiceFaultType convertMarketDataFault, Throwable cause) {
        super(message, cause);
        this.convertMarketDataFault = convertMarketDataFault;
    }

    public au.edu.unsw.soacourse.marketdataservice.ServiceFaultType getFaultInfo() {
        return this.convertMarketDataFault;
    }
}
