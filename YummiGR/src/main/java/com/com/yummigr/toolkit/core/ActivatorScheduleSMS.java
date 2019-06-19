package com.com.yummigr.toolkit.core;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.services.MessengerService;
import com.com.yummigr.toolkit.models.AuthorizationSMSFacilita;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ActivatorScheduleSMS extends Thread implements Runnable, Future {


    /**
     * Constants
     */
    protected static final String PREFIX="Schedule Execution Send SMS";
    protected static final Integer POOL_SIZE = 1;
    private ThreadPoolTaskScheduler pooltaskScheduler;
    protected String password;

    protected String message_customize;
    protected String subject_message;
    protected Schedule schedule;
    private String response;
    private boolean activate;
    private MessengerService messengerService;
    private Messenger messengerConnector;
    protected HandlerSMSV1 handlerSMSV1;
    private String username;

    private  AuthorizationSMSFacilita auth;
    public ActivatorScheduleSMS(){ }

    public ActivatorScheduleSMS(Schedule sh, MessengerService msn, Messenger conector, AuthorizationSMSFacilita auth, boolean activate,String subject_message, String message) throws IOException {

        this.schedule=sh;
        this.messengerService=msn;
        this.messengerConnector= conector;
        this.activate=activate;
        this.auth=auth;
        this.message_customize=message;
        this.subject_message = subject_message;
        this.handlerSMSV1= new HandlerSMSV1();
        this.response = this.initiateTasks();

    }
    public void run(){
        List<Contacts> receiver = this.messengerService.getAllContactsForMessenger(this.messengerConnector);


        List<String> receiver_phone_number = new ArrayList<String>();
        List<String> receiver_messages = new ArrayList<String>();
        for(Contacts c :receiver) {
            receiver_phone_number.add(c.getPhone_number());
            receiver_messages.add(c.getMessage());
        }
        try {
        	if(this.message_customize!=null) {
            this.handlerSMSV1.setUsername(auth.getUsername());
            this.handlerSMSV1.setPassword(auth.getPassword());
            this.handlerSMSV1.sendSMS(receiver_phone_number,null,this.message_customize);
        	}else {
        	
        		this.handlerSMSV1.setUsername(auth.getUsername());
                this.handlerSMSV1.setPassword(auth.getPassword());
                this.handlerSMSV1.sendSMSPre(receiver_phone_number, null,receiver_messages);
                
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            initiateTasks();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public String getResponseExecution() {
        if(this.response!=null) return this.response;
        return null;
    }

    public void destroyActivator(){
        this.schedule=null;
        this.messengerService=null;
        this.messengerConnector= null;
        this.activate=false;
        this.auth=null;
        this.message_customize=null;
        this.subject_message = null;
        this.handlerSMSV1=null;
    }
    /**
     *
     * @return
     * @throws IOException
     */
    public String initiateTasks() throws IOException {
        if (this.pooltaskScheduler != null) {
            this.pooltaskScheduler.initialize();
            this.pooltaskScheduler.setPoolSize(POOL_SIZE);
            this.pooltaskScheduler.setThreadNamePrefix(PREFIX);
            this.pooltaskScheduler.schedule(this, new Date(System.currentTimeMillis()
                    + this.schedule.getTime()));

            String thread = "Thread Init time: " + this.schedule.getTime();
            return thread;
        }
        return null;
    }

    public HandlerSMSV1 getHandlerSMSV1() {
        return handlerSMSV1;
    }

    public Messenger getMessengerConnector() {
        return messengerConnector;
    }

    public MessengerService getMessengerService() {
        return messengerService;
    }

    public void setHandlerSMSV1(HandlerSMSV1 handlerSMSV1) {
        this.handlerSMSV1 = handlerSMSV1;
    }

    public Schedule getSchedule() {
        return schedule;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }


    public void setPassword(String password){
        this.password =password;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


}
