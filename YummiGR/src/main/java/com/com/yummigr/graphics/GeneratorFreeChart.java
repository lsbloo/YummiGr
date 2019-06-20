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

import com.com.yummigr.archives.ManagerFilesGraphics;
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
	
	
	/**
	 * Define o tipo de grafico a ser gerado.
	 *  Usuario pode escolher o tipo de grafico.
	 * @param title
	 * @param set
	 * @param option
	 * @return
	 */
	public JFreeChart CustomizeGraph(String title , DefaultCategoryDataset set, Integer option) {
		switch(option) {
		case 1:
			return createGraficEmailsBarChart(title,set);
		case 2:
			return createGraficEmailsLineChart(title,set);
		default:
			return createGraficEmailsBarChart(title,set);
		}	
	}
	
	public JFreeChart createGraficEmailsLineChart(String title , DefaultCategoryDataset set) {
		JFreeChart g = ChartFactory.createLineChart(title, "Contacts","Amount", set, PlotOrientation.VERTICAL, true, true, false);
		return g;
	}
	public JFreeChart createGraficEmailsBarChart(String title,DefaultCategoryDataset set) {	
		JFreeChart g = ChartFactory.createBarChart(title, "Contacts","Amount", set, PlotOrientation.VERTICAL, true, true, false);
		return g;
	}
	
	
	public DefaultCategoryDataset getDataSet(List<HashMap<LoggerSender,Integer>> list_maps , List<Contacts> cc_related) {
		DefaultCategoryDataset default_category_emails_contact = new DefaultCategoryDataset();
		
		if(list_maps.size() != 0  && list_maps != null) {
		for(int i = 0 ; i < list_maps.size() ; i ++) {
			HashMap<LoggerSender, Integer> m = list_maps.get(i);
			for(LoggerSender s : m.keySet()) {		
				default_category_emails_contact.addValue(m.get(s), "maximum value", cc_related.get(i).getEmail());
			}
		}
		}
		return default_category_emails_contact;
	}
	
	
	public void saveGraphicPNG(JFreeChart g , String name_arq, String identifier) throws IOException {
		ManagerFilesGraphics manager = new ManagerFilesGraphics(identifier,g,name_arq);
		manager.save();
	}
	
	public Set<LoggerSender> getS() {
		return s;
	}

	public void setS(Set<LoggerSender> s) {
		this.s = s;
	}
}
