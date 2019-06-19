package com.com.yummigr.stack;

import com.com.yummigr.models.Messenger;
import com.com.yummigr.stack.core.ThreadActSMS;
import com.com.yummigr.toolkit.core.ActivatorScheduleEmail;
import com.com.yummigr.toolkit.core.ActivatorScheduleSMS;

import java.util.List;
import java.util.Stack;

public class StackActivatorSMS  implements ThreadActSMS {

    public List<ActivatorScheduleSMS> list;
    protected  Integer capacity;
    protected List<ActivatorScheduleSMS> stack= new Stack <ActivatorScheduleSMS>();
   
    public ActivatorScheduleSMS getSchedule(Messenger u ) {
		for(int i = 0 ; i < this.stack.size() ; i ++) {
			if(this.stack.get(i).getMessengerConnector().getAccount_sid().equals(u.getAccount_sid())) {
				return this.stack.get(i);
			}
		}
		return null;
	}
    

    
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
