package com.com.yummigr.archives;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

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
	
	public ManagerFilesGraphics(String identifier, JFreeChart chart, String name_arq) throws IOException {
		this.manipulator = new ManipulatorFile();
		this.jFreeChart=chart;
		this.name_arq=name_arq;
		this.identifier = identifier;
	}
	
	public ManagerFilesGraphics() {}
	

	
	public HashMap<String,Boolean> save() throws IOException {
		if(this.manipulator.checkExistenceDir(this.identifier)) {
			OutputStream file = new FileOutputStream(this.manipulator.PATH_USERS+identifier+"/"+name_arq);
			ChartUtilities.writeChartAsPNG(file, this.jFreeChart, 860, 480);
			String path = this.manipulator.PATH_USERS+identifier+"/"+name_arq;
			HashMap k = new HashMap<String,Boolean>();
			file.close();
			k.put(path,true);
			return k;
		}else {
			String path = this.manipulator.PATH_USERS+identifier+"/"+name_arq;
			System.err.println("Directory User dont create");
			HashMap k = new HashMap<String,Boolean>();
			k.put(path,false);
			return k;
		}
	}
	
	
	
	
	

}
