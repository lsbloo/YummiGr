package com.com.yummigr.archives;

import java.io.File;

public class ManipulatorFile {
	
	private String name_dir;
	private File file;
	public static final String PATH_INITIAL = "/home/osvaldoairon/";
	public static final String DIR_INITIAL = "yummi_data";
	public static final String PATH_USERS = "/home/osvaldoairon/yummi_data/";
	
	public ManipulatorFile() {}
	
	public ManipulatorFile(String name_dir) {
		setName_dir(name_dir);
		this.file=new File(getName_dir());
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
