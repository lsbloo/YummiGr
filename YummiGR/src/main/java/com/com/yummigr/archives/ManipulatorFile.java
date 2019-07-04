package com.com.yummigr.archives;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManipulatorFile {
	
	private String name_dir;
	private File file;
	public static  String PATH_INITIAL = "";
	public static final String DIR_INITIAL = "/yummi_data";
	public static final String DIR_INITIAl_LOGGER = "/yummi_loggers";
	public static String PATH_USERS = "";

	public static final String[] CONFIGURATION_ARCHIVE_CSV_ACTION_NAMES =
			{

					"action_create_user_log",
					"action_desative_user_log",
					"action_create_contact_log",
					"action_update_contact_log",
					"action_delete_contact_log",
					"action_connect_profile_umbrella_log",
					"action_create_messenger_connector_log"
			};

	public static final String[] CONFIGURATION_ARCHIVE_FORMATS =
			{
					".png",
					".jpeg",
					".csv",
					".txt",
					".pdf"
			};

	public static final String[] CONFIGURATIONCSV =
			{

					"Date",",","Action",",","User"

			};
	
	
	public ManipulatorFile() throws IOException {
		ManipulatorFile.PATH_INITIAL = new File("..").getCanonicalPath();
		ManipulatorFile.PATH_USERS = new File("..").getCanonicalPath() + "/yummi_data/";
	}
	
	public ManipulatorFile(String name_dir) {
		setName_dir(name_dir);
		this.file=new File(getName_dir());
	}

	/**
	 *
	 * @param file_name name of action archive create of initial data loader;
	 */
	public void generateCSVFile(String file_name){
		try {
			String result = PATH_INITIAL+DIR_INITIAl_LOGGER+"/"+file_name+CONFIGURATION_ARCHIVE_FORMATS[2];

			File f = new File(result);
			if(!f.exists()) {
				FileWriter escritor = new FileWriter(result);
				escritor.append(CONFIGURATIONCSV[0]);
				escritor.append(CONFIGURATIONCSV[1]);
				escritor.append(CONFIGURATIONCSV[2]);
				escritor.append(CONFIGURATIONCSV[3]);
				escritor.append(CONFIGURATIONCSV[4]);
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
