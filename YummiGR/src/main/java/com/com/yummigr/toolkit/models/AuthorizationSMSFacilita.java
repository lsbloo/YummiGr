package com.com.yummigr.toolkit.models;

public class AuthorizationSMSFacilita {

    private String username;

    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     */
    public AuthorizationSMSFacilita(){}

    public AuthorizationSMSFacilita(String username,String password){
        setUsername(username);
        setPassword(password);
    }

}
