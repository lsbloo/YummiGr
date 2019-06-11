package com.com.yummigr.stack.core;

import com.com.yummigr.toolkit.core.ActivatorScheduleSMS;

public interface ThreadActSMS {

    public void push(ActivatorScheduleSMS act) throws Exception;


    public ActivatorScheduleSMS pop(ActivatorScheduleSMS act);

    public int size();


    public String notfiy(ActivatorScheduleSMS act);
}
