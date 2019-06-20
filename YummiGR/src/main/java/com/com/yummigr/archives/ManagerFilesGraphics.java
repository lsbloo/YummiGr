package com.com.yummigr.archives;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Component;

/**
 * save archives from graphs messenger_connector;(user);
 * @author osvaldoairon
 *
 */
@Component
public class ManagerFilesGraphics{
	
	private ManipulatorFile manipulator;
	private JFreeChart jFreeChart;
	private String name_arq;
	private String identifier;
	
	public ManagerFilesGraphics(String identifier, JFreeChart chart, String name_arq) {
		this.manipulator = new ManipulatorFile();
		this.jFreeChart=chart;
		this.name_arq=name_arq;
		this.identifier = identifier;
	}
	
	public ManagerFilesGraphics() {}
	

	
	public void save() throws IOException {
		if(this.manipulator.checkExistenceDir(this.identifier)) {
			OutputStream file = new FileOutputStream(this.manipulator.PATH_USERS+identifier+"/"+name_arq);
			ChartUtilities.writeChartAsPNG(file, this.jFreeChart, 550, 400);
			file.close();
		}else {
			System.err.println("Directory User dont create");
		}
	}
	
	
	
	
	

}
