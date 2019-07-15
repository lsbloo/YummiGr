package com.com.yummigr.archives.logs;


import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.com.yummigr.archives.ManipulatorFile;
import com.com.yummigr.models.MyLogger;
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

    public LoggerYummi(){
        this.manipulator = new ManipulatorFile();
        this.slf4jLogger= LoggerFactory.getLogger(LoggerYummi.class);
    }

    public ManipulatorFile getManipulator (){
        return this.manipulator;
    }

    public void generateLoggerByAction(MyLogger logger , String path_csv,boolean generate,String name_arq){
        try {
            List<Collection> dList = this.manipulator.assineLogger(logger);

            if(generate){
                this.manipulator.generateDataLogger(dList,path_csv,name_arq);
                this.slf4jLogger.info("Logger Insert !");
            }else{
                this.slf4jLogger.info("Logger signed");
            }
        }catch(IOException e){
            this.slf4jLogger.error("Logger don't insert");
            e.printStackTrace();
        }

    }




}
