package com.com.yummigr.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.LoggerSender;


/**
 * create graphs;
 * @author osvaldoairon
 *
 */
@Component
public class GeneratorFreeChart {
	
	private Set<LoggerSender> s;
	private Set<LoggerSender> keySet;

	public static final String TITTLE_EMAIL_GRAPHS_MONTH= "Emails Sent According to the Month of Submission.";


	public GeneratorFreeChart() {
		
	}
	
	public JFreeChart createGraficEmailsByMonth(String title,DefaultCategoryDataset set) {	
		JFreeChart g = ChartFactory.createBarChart(title, "Contacts","Amount", set, PlotOrientation.VERTICAL, true, true, false);
				
		return g;
	}
	
	
	public DefaultCategoryDataset getDataSet(List<HashMap<LoggerSender,Integer>> list_maps , List<Contacts> cc_related) {
		DefaultCategoryDataset default_category_emails_contact = new DefaultCategoryDataset();
		
		if(list_maps.size() != 0  && list_maps != null) {
		for(int i = 0 ; i < list_maps.size() ; i ++) {
			HashMap<LoggerSender, Integer> m = list_maps.get(i);
			for(LoggerSender s : m.keySet()) {		
				default_category_emails_contact.addValue(m.get(s), 
						"maximum value", cc_related.get(i).getEmail());
			}
		}
		}
		return default_category_emails_contact;
	}
	
	

	public void saveGraphicPNG(JFreeChart g , String name_arq) throws IOException {
		OutputStream file = new FileOutputStream(name_arq);
		ChartUtilities.writeChartAsPNG(file, g, 550, 400);
		file.close();
		
	}

	public Set<LoggerSender> getS() {
		return s;
	}

	public void setS(Set<LoggerSender> s) {
		this.s = s;
	}
}
