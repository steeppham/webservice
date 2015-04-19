package au.edu.unsw.soacourse.marketdataservice;

import java.io.IOException;
import java.util.List;

import org.rendersnake.HtmlCanvas;

public class MarketDataPresentation {
	MarketData data;
	
	public MarketDataPresentation(MarketData data) {
		this.data = data;
	}
	
	public String outputHtml() throws IOException {
		HtmlCanvas html = new HtmlCanvas();
		
		// output column
		String[] columnHeaders = data.getValues().get(0);
		html.html().body().table().tr();
		for (String column : columnHeaders) {
			html.th().content(column);
		}
		html._tr();
		
		List<String[]> values = data.getValues();
		// output rows for market data
		html.tbody();
		for (int i = 1; i < values.size(); i++) {
			html.tr();
			String[] columnValues = values.get(i);
			for (String column : columnValues) {
				html.td().content(column);
			}
			html._tr();
		}
		
		// close table
		html._tbody()._table()._body()._html();
		
		return html.toHtml();
	}
}
