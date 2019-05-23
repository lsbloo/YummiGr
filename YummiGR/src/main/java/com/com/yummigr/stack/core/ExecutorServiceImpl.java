package com.com.yummigr.stack.core;


import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class ExecutorServiceImpl 
{

	
	private ExecutorService service;
	
	
	public ExecutorServiceImpl () {
		
	}
	
	public void initate() {
		this.service = Executors.newCachedThreadPool();

	}


}
