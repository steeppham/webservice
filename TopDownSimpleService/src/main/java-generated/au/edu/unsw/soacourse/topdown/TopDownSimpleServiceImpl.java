package au.edu.unsw.soacourse.topdown;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.WebService;

import org.apache.commons.io.FileUtils;

@WebService(endpointInterface = "au.edu.unsw.soacourse.topdown.TopDownSimpleService")
public class TopDownSimpleServiceImpl implements TopDownSimpleService {

	ObjectFactory factory = new ObjectFactory();

	public ImportMarketDataResponse importMarketData(
			ImportMarketDataRequest parameters) throws ImportMarketFaultMsg {

		String eventId = null;
		
		if (parameters.getSec().length() != 3) {
			// assume that we only want a SEC value of length 3
			String msg = "SEC code should be exactly 3 characters longskugflksnfksj";
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

		String startdate = parameters.startDate;
		String datetemp[] = startdate.split("-");
		int startMonthTemp;
		String startDay = datetemp[0];
		String startMonth = datetemp[1];
		String startYear = datetemp[2];

		startMonthTemp = Integer.parseInt(startMonth);
		startMonthTemp = startMonthTemp - 1;
		startMonth = Integer.toString(startMonthTemp);

		String enddate = parameters.endDate;
		String datetemp2[] = startdate.split("-");
		int endMonthTemp;
		String endDay = datetemp2[0];
		String endMonth = datetemp2[1];
		String endYear = datetemp2[2];

		endMonthTemp = Integer.parseInt(endMonth);
		endMonthTemp = endMonthTemp - 1;
		endMonth = Integer.toString(endMonthTemp);

		String url = "http://real-chart.finance.yahoo.com/table.csv?s="
				+ parameters.sec + ".AX&a=" + startMonth + "&b=" + startDay
				+ "&c=" + startYear + "&d=" + endMonth + "&e=" + endDay + "&f="
				+ endYear + "&g=&ignore=.csv ";
		URL yahoo;

		try {
			yahoo = new URL(url);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));

			String inputLine;
			boolean first = true;
			String tmpdir = System.getProperty("java.io.tmpdir");
			System.out.println(tmpdir);

			File localFile = null;
			// File dir = new File("/Rashim/Users/rashim/");
			localFile = File.createTempFile("blahblah", ".txt");

			FileWriter fileWriter = new FileWriter(localFile, true);

			while ((inputLine = in.readLine()) != null) {
				String temp[];
				String formatted;
				if (first == true) {
					// System.out.println(inputLine);
					temp = inputLine.split(",");
					formatted = "sec" + "," + temp[0] + "," + temp[1] + ","
							+ temp[2] + "," + temp[3] + "," + temp[4] + ","
							+ temp[5] + "," + temp[6] + "\n";
					System.out.println(formatted);
					fileWriter.write(formatted);

					first = false;
				} else {
					temp = inputLine.split(",");
					formatted = "sec," + temp[0] + ",AUD" + temp[1] + ",AUD"
							+ temp[2] + ",AUD" + temp[3] + ",AUD" + temp[4]
							+ "," + temp[5] + ",AUD" + temp[6] + "\n";
					fileWriter.write(formatted);

					System.out.println(formatted);

				}

			}
			
			eventId = localFile.getName();
			
			fileWriter.close();
		    in.close();
	        String path = "/Users/rashim/Documents/Tomcat/apache-tomcat-7.0.42/webapps/ROOT/"+localFile.getName();
	        System.out.println(path);
	        File source = localFile;
	        File dest = new File(path);
	        try{
	        	FileUtils.copyFile(source, dest);
	        	//copyDirectory(source,dest);
	        } catch(IOException e){
	        	e.printStackTrace();
	        }

		}// try

		catch (Exception e) {
			e.printStackTrace();
		}

		ImportMarketDataResponse res = factory.createImportMarketDataResponse();
		res.returnData = eventId;
		return res;
	}

	public DownloadFileResponse downloadFile(DownloadFileRequest parameters)
			throws DownloadFileFaultMsg {

		if (!parameters.getEventSetID().equals("abc-abc-111")) {
			// assume that we only know about an eventSetId abc-abc-111
			String msg = "Unknonw eventSetId was given";
			String code = "ERR_EVENT";

			// TODO: SOAP Fault handling should come here ...
			// TODO: create a ServieFaultType 'fault' from ObjectFactory
			// TODO: prepare 'fault' object: set errorcode and errortext
			// TODO: throw new DownloadFaultMsg(msg,fault)
			ServiceFaultType sft = factory.createServiceFaultType();
			sft.setErrcode(code);
			sft.setErrtext(msg);
			DownloadFileFaultMsg dfm = new DownloadFileFaultMsg(msg, sft);
		}

		DownloadFileResponse res = factory.createDownloadFileResponse();
		res.returnData = "EventSet Id: " + parameters.eventSetID;
		return res;
	}
}
