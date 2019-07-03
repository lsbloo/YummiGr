package com.com.yummigr.umbrella.core;

public class ConnectorProfiles {
	
	
	private String id_perfil_select;
	
	
	private String manager_identifier;


	public String getId_perfil_select() {
		return id_perfil_select;
	}


	public void setId_perfil_select(String id_perfil_select) {
		this.id_perfil_select = id_perfil_select;
	}


	public String getManager_identifier() {
		return manager_identifier;
	}


	public void setManager_identifier(String manager_identifier) {
		this.manager_identifier = manager_identifier;
	}
	
	

	public ConnectorProfiles(){}

	/**
	 * ? ! yeap
	 * @param id_perfil_select
	 * @param manager_identifier
	 */
	public ConnectorProfiles(String id_perfil_select , String manager_identifier){
		setId_perfil_select(id_perfil_select);
		setManager_identifier(manager_identifier);

	}
}
