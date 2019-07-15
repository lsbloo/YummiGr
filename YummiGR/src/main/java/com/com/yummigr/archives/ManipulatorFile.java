package com.com.yummigr.archives;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.com.yummigr.models.MyLogger;
import com.com.yummigr.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ManipulatorFile {
	
	private String name_dir;
	private File file;

	public static  String PATH_INITIAL = "";

	public static final String DIR_INITIAL = "/yummi_data";

	public static final String DIR_INITIAl_LOGGER = "/yummi_loggers";

	public static String PATH_USERS = "";

	List<Collection> list_line = new ArrayList<Collection>();
	List<String[]> line = new ArrayList<String[]>();

	
	public ManipulatorFile() {
		try {
			ManipulatorFile.PATH_INITIAL = new File("..").getCanonicalPath();
			ManipulatorFile.PATH_USERS = new File("..").getCanonicalPath() + "/yummi_data/";
		}catch(IOException e){
			e.printStackTrace();
		}
		}
	
	public ManipulatorFile(String name_dir) {
		setName_dir(name_dir);
		this.file=new File(getName_dir());
	}


	/**
	 * Assina o logger no arquivo de logger .....
	 * @param
	 * @param logger
	 * @throws IOException
	 */
	public List<Collection> assineLogger(MyLogger logger) {
		line.add(new String[]{logger.getDate(), logger.getAction(), logger.getUser_identifier()});
		list_line.add(line);
		return list_line;

	}


	public void generateDataLogger(List<Collection> line_logger,String path_csv,String name_arq) throws IOException{
			FileWriter writer = new FileWriter(path_csv,false);
			CSVWriter csvWriter = new CSVWriter(writer);
			csvWriter.writeNext(Constants.HEADER);

			csvWriter.writeAll(line_logger.get(0));
			csvWriter.flush();
			csvWriter.close();
			writer.close();
	}



	public List<String[]> getLastLogger (String path_csv) throws  IOException{
		CSVReader reader = new CSVReader(new FileReader(path_csv),',');
		List<String[]> loggerList = reader.readAll();
		return loggerList;
	}

	public String getPathCSV(String name_csv){
		return  PATH_INITIAL+DIR_INITIAl_LOGGER+"/"+name_csv+Constants.CONFIGURATION_ARCHIVE_FORMATS[2];
	}


	/**
	 *
	 * @param file_name name of action archive create of initial data loader;
	 */
	public void generateCSVFile(String file_name){
		try {
			String result = PATH_INITIAL+DIR_INITIAl_LOGGER+"/"+file_name+Constants.CONFIGURATION_ARCHIVE_FORMATS[2];

			File f = new File(result);
			if(!f.exists()) {
				FileWriter escritor = new FileWriter(result);
				escritor.append(Constants.CONFIGURATIONCSV[0]);
				escritor.append(Constants.CONFIGURATIONCSV[1]);
				escritor.append(Constants.CONFIGURATIONCSV[2]);
				escritor.append(Constants.CONFIGURATIONCSV[3]);
				escritor.append(Constants.CONFIGURATIONCSV[4]);
				escritor.flush();
				escritor.close();
				System.err.println("create csv " + file_name  + " Status OK");
			}
			else{
				System.err.println("exit only  csv with name " + file_name  + " Status FORBBIDEN");
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * user dir;
	 * @param name_dir
	 * @param path_three  = + path_init + dir_name_user;
	 */
	public ManipulatorFile(String name_dir, String path_three) {
		setName_dir(name_dir);
		this.file = new File(path_three+name_dir);
	}
	public boolean checkExistenceDir(String identifier) {
		File f = new File(PATH_USERS+identifier);
		boolean exit = f.exists();
		return exit;
	}
	
	public void generateDirectoryUser() {
		if(!this.file.exists()) {
			boolean exit = this.file.mkdir();
			if(exit) {
				System.err.println("User Directory created");
			}else {
				System.err.println("User Directory not created ");
			}
		}
		
	}


	public void createDirectoryOfLoggersInitialLoader(String path_init, String name_dir_path_init){
		String result = path_init+name_dir_path_init;
		File f =  new File(result);
		if(!f.exists()){
			boolean create = f.mkdir();
			if(create){
				System.err.println("Directory init logger created");
			}else{
				System.err.println("Directory dont init logger created");
			}
		}
	}
	public void createDirectoryInitialLoader(String path_init , String name_dir_init) {
		String result = path_init+name_dir_init;
		File f = new File(result);
		if(!f.exists()) {
			boolean exit = f.mkdir();
			if(exit) {
				System.err.println("Directory init created");
			}else {
				System.err.println("Fail create directory");
			}
		}
	}

	public String getName_dir() {
		return name_dir;
	}

	public void setName_dir(String name_dir) {
		this.name_dir = name_dir;
	}
	

}
