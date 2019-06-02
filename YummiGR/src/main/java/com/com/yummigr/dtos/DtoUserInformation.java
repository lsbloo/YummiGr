package com.com.yummigr.dtos;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;

import java.util.List;

public class DtoUserInformation {

    private String username;

    private boolean status_account;

    private String identifier;

    private List<Contacts> contatos;

    private Messenger messenger;

    private Schedule scheduleTime;

    public Schedule getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Schedule scheduleTime) {
        this.scheduleTime = scheduleTime;
    }


    public DtoUserInformation(){}

    public DtoUserInformation(String username,boolean actived,String identifier, List<Contacts> contacts, Messenger messenger){
        setUsername(username);
        setStatus_account(actived);
        setIdentifier(identifier);
        setContatos(contacts);
        setMessenger(messenger);

    }


    public boolean isStatus_account() {
        return status_account;
    }

    public List<Contacts> getContatos() {
        return contatos;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUsername() {
        return username;
    }

    public void setContatos(List<Contacts> contatos) {
        this.contatos = contatos;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public void setStatus_account(boolean status_account) {
        this.status_account = status_account;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
