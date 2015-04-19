package au.edu.unsw.soacourse.marketservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.edu.unsw.soacourse.marketservice.DownloadFileRequest;
import au.edu.unsw.soacourse.marketservice.DownloadFileResponse;
import au.edu.unsw.soacourse.marketservice.ImportMarketDataRequest;
import au.edu.unsw.soacourse.marketservice.ImportMarketDataResponse;
import au.edu.unsw.soacourse.marketservice.TopDownSimpleService;

@Controller
public class MarketServiceController {

   // TODO: Add the TopDownSimpleService interface as a member of the controller
	@Autowired
   private TopDownSimpleService simple; //this is instantiated by jaxws:client id=simple in dispatcher-servlet.xml
   Input importedMarketData = new Input(); //this should be holding the name of all the current files downloaded, but keeps refreshing
   
   
   @RequestMapping("/home") // this is the entrypoint for importmarketdata
   public String intial(ModelMap model) throws Exception {
	  model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      return "processImportMarketData";
   }  
   
   @RequestMapping("/home2") // this is entry point for market utils
   public String intial2(ModelMap model) throws Exception {
	  model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      return "MarketUtils";
   }   
   
@RequestMapping("/importMarket")
   public String processImportMarketData(ModelMap model,String sec, String startMonth,String startDay,String startYear,
		   String endMonth, String endYear, String endDay) throws Exception {
	  System.out.println(sec);
	 // String sec =
//	  String upperSec = null;;
//	  if(!sec.isEmpty()){
//	     upperSec = sec.toUpperCase();
//	  }
	  String startDate;
	  String endDate;
      String startDay1 = null;
      String endDay1 = null;
      String endMonth1 = null;
      String startMonth1 = null;
      String startYear1 = null;
      String endYear1 = null;
      startDay1 = startDay;
      endDay1 = endDay;
      startMonth1 = startMonth;
      endMonth1 = endMonth;
      startYear1 = startYear;
      endYear1 = endYear;
	  //build the date strings
	  startDate = startDay1+"-"+startMonth1+"-"+startYear1;
	  endDate = endDay1+"-"+endMonth1+"-"+endYear1;
	  
	  System.out.println(startDate);
	  System.out.println(endDate);
	  //System.out.println(upperSec);
      // TODO: Add the creation of an impoartMarketData request type and populate it
	  ImportMarketDataRequest request = new au.edu.unsw.soacourse.marketservice.ObjectFactory().createImportMarketDataRequest();
	  request.setSec(sec);
	  request.setStartDate(startDate);	  
	  request.setEndDate(endDate);	
	  request.setDataSource("http://www.example.org.ss/marketfile");
	   
      // TODO: Call the web service      
	  ImportMarketDataResponse response = simple.importMarketData(request);
      importedMarketData.setImportedMarketData(response.getReturnData());
      // TODO: Replace null with the results from the web service response.
      model.addAttribute("returnData", response.getReturnData());
      
      model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      // View we are returning to, in this case processImportMarketData.jsp 
      return "processImportMarketData";
   }

   @RequestMapping("/downloadFile")
   public String processDownloadFile(ModelMap model,String eventSetID) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	  DownloadFileRequest request = new au.edu.unsw.soacourse.marketservice.ObjectFactory().createDownloadFileRequest();
	  request.setEventSetID(eventSetID);

      // TODO: Call the web service 
	  DownloadFileResponse response = simple.downloadFile(request);
      
      // TODO: Replace null with the results from the web service response.
      model.addAttribute("downloadLink", response.getReturnData());
      
      // View we are returning to, in this case processImportMarketData.jsp 
      return "processImportMarketData";
   }
   @RequestMapping("/currencyConvert")
   public String currencyConvert(ModelMap model,String eventSetID) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	  
      // TODO: Call the web service 
	
      // TODO: Replace null with the results from the web service response.
     // model.addAttribute("convertResponse", response.getReturnData());
	   model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      // View we are returning to, in this case processImportMarketData.jsp 
      return "MarketUtils";
   }
   @RequestMapping("/marketDataSummary") 
   public String marketDataSuummary(ModelMap model) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	 
      // TODO: Call the web service 
	 
      
      // TODO: Replace null with the results from the web service response.
     // model.addAttribute("MarketDataResponse", response.getReturnData()); <--this will work after you invoke your service 
	   model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      // View we are returning to, in this case processImportMarketData.jsp 
      return "MarketUtils";
   }

}