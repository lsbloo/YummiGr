package com.com.yummigr.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.com.yummigr.models.Messenger;
import com.com.yummigr.stack.core.ThreadActvator;
import com.com.yummigr.toolkit.core.ActivatorScheduleEmail;

/**
 * stack of threads activator schedule;
 * @author osvaldoairon
 *
 * @param <ActivatorScheduleEmail>
 */
public class StackActivator implements ThreadActvator{

	public List<ActivatorScheduleEmail> list;
	protected  Integer capacity;
	protected List<ActivatorScheduleEmail> stack= new Stack<ActivatorScheduleEmail>();
	
	public StackActivator(Integer capacity) {
		this.list = new ArrayList<ActivatorScheduleEmail>();
		this.capacity=capacity;
	}
	
	
	@Override
	public void push(ActivatorScheduleEmail act) throws Exception {
		// TODO Auto-generated method stub
		this.stack.add(act);
	}
	
	
	public ActivatorScheduleEmail getSchedule(Messenger u ) {
		for(int i = 0 ; i < this.stack.size() ; i ++) {
			if(this.stack.get(i).getMessenger().getAccount_sid().equals(u.getAccount_sid())) {
				return this.stack.get(i);
			}
		}
		return null;
	}
	public boolean stop(ActivatorScheduleEmail act) {
		for(int i = 0 ; i < this.stack.size() ; i ++) {
			if(act.equals(this.stack.get(i))) {
				this.stack.get(i).cancel(true);
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		System.err.println("Size Stack : " + size());
	}
	@Override
	public ActivatorScheduleEmail pop(ActivatorScheduleEmail act) {
		// TODO Auto-generated method stub
		this.stack.remove(act);
		return act;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.stack.size();
	}
	@Override
	public String notfiy(ActivatorScheduleEmail act) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

}
