package com.com.yummigr.archives.logs;


import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.com.yummigr.archives.ManipulatorFile;
import com.com.yummigr.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * creation of logs related to umbrella and yummigr
 * @author osvaldoairon
 */
public class LoggerYummi implements Serializable {


    private ManipulatorFile manipulator;
    private  Logger slf4jLogger;

    public LoggerYummi() throws IOException {
        this.manipulator = new ManipulatorFile();
        this.slf4jLogger= LoggerFactory.getLogger(LoggerYummi.class);
    }

    public void generateLoggerByAction(String action , Object object , Date action_date){


    }




}
