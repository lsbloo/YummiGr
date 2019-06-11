package com.com.yummigr.stack;

import com.com.yummigr.stack.core.ThreadActSMS;
import com.com.yummigr.toolkit.core.ActivatorScheduleSMS;

import java.util.List;
import java.util.Stack;

public class StackActivatorSMS  implements ThreadActSMS {

    public List<ActivatorScheduleSMS> list;
    protected  Integer capacity;
    protected List<ActivatorScheduleSMS> stack= new Stack <ActivatorScheduleSMS>();
    @Override
    public void push(ActivatorScheduleSMS act) throws Exception {
        this.stack.add(act);
    }
    @Override
    public ActivatorScheduleSMS pop(ActivatorScheduleSMS act)  {
        for(int i = 0 ; i < stack.size(); i ++){
            if(act.equals(stack.get(i))){
                return stack.get(i);
            }
        }
        return act;
    }
    public void print(){
            System.err.println("Size Stack : SMS " + size());
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public String notfiy(ActivatorScheduleSMS act) {
        return null;
    }
}
