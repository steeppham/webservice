
package au.edu.unsw.soacourse.marketservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-20T10:56:47.362+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "importMarketFault", targetNamespace = "http://marketservice.soacourse.unsw.edu.au")
public class ImportMarketFaultMsg extends Exception {
    
    private au.edu.unsw.soacourse.marketservice.ServiceFaultType importMarketFault;

    public ImportMarketFaultMsg() {
        super();
    }
    
    public ImportMarketFaultMsg(String message) {
        super(message);
    }
    
    public ImportMarketFaultMsg(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportMarketFaultMsg(String message, au.edu.unsw.soacourse.marketservice.ServiceFaultType importMarketFault) {
        super(message);
        this.importMarketFault = importMarketFault;
    }

    public ImportMarketFaultMsg(String message, au.edu.unsw.soacourse.marketservice.ServiceFaultType importMarketFault, Throwable cause) {
        super(message, cause);
        this.importMarketFault = importMarketFault;
    }

    public au.edu.unsw.soacourse.marketservice.ServiceFaultType getFaultInfo() {
        return this.importMarketFault;
    }
}
