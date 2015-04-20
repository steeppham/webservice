package au.edu.unsw.soacourse.marketservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.edu.unsw.soacourse.marketdataservice.*;



@Controller
public class MarketServiceController {

   // TODO: Add the TopDownSimpleService interface as a member of the controller
	@Autowired
   private TopDownSimpleService simple; //this is instantiated by jaxws:client id=simple in dispatcher-servlet.xml
   private MarketDataUtilService data; 
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
	  try{
		  ImportMarketDataResponse response = simple.importMarketData(request);
	      importedMarketData.setImportedMarketData(response.getReturnData());
	  
      // TODO: Replace null with the results from the web service response.
    
      model.addAttribute("returnData", response.getReturnData());
      
      model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
	  }
	  catch(Exception e){
		  model.addAttribute("returnData", e.toString());
	      
	      model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
      }
	  
	  
      // View we are returning to, in this case processImportMarketData.jsp 
      return "processImportMarketData";
   } 

   @RequestMapping("/downloadFile")
   public String processDownloadFile(ModelMap model,String eventSetID) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	  
	  DownloadFileRequest request = new au.edu.unsw.soacourse.marketservice.ObjectFactory().createDownloadFileRequest();
	  request.setEventSetID(eventSetID);
     try{
      // TODO: Call the web service 
	  DownloadFileResponse response = simple.downloadFile(request);
      
      // TODO: Replace null with the results from the web service response.
      model.addAttribute("downloadLink", response.getReturnData());
      model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
     }
     catch(Exception e){
    	 model.addAttribute("downloadLink", e.toString());
    	 model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
     }
      // View we are returning to, in this case processImportMarketData.jsp 
      return "processImportMarketData";
   }
   @RequestMapping("/currencyConvert")
   public String currencyConvert(ModelMap model,String eventSetIDCC,String currency,String targetDate) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	  CurrencyConvertMarketData request = new au.edu.unsw.soacourse.marketdataservice.ObjectFactory().createCurrencyConvertMarketData();
      // TODO: Call the web service 
	 request.setEventSetID(eventSetIDCC);
	 request.setTargetCurrency(currency);
	 request.setTargetDate(targetDate);
	 try{
	 CurrencyConvertMarketDataResponse response = data.convertMarketData(request);
	 //Currency 
      // TODO: Replace null with the results from the web service response.
     
	  model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
	  model.addAttribute("convertResponse", response.getEventSetId());
	 }catch(Exception e){
		 model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
		  model.addAttribute("convertResponse", e.toString());
	 }
      // View we are returning to, in this case processImportMarketData.jsp 
      return "MarketUtils";
   }
   @RequestMapping("/marketDataSummary") 
   public String marketDataSuummary(ModelMap model,String eventSetIDMU) throws Exception {
      // TODO: Add the creation of a DownloadFile request type and populate it
	  System.out.println(eventSetIDMU);
	   SummaryMarketData request = new au.edu.unsw.soacourse.marketdataservice.ObjectFactory().createSummaryMarketData();
      // TODO: Call the web service 
	 try{
	 request.setEventSetId(eventSetIDMU);
     
	 SummaryMarketDataResponse response = data.summariseMarketData(request);
      // TODO: Replace null with the results from the web service response.
      model.addAttribute("MarketDataResponse", response.getCurrencyCode());  
	   model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData());
	 } catch(Exception e){
		 model.addAttribute("MarketDataResponse",e.toString() );  
		   model.addAttribute("importedMarketData",importedMarketData.getImportedMarketData()); 
	 }
      // View we are returning to, in this case processImportMarketData.jsp 
      return "MarketUtils";
   }

}