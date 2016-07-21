package com.CommonAction.Test;

import java.io.IOException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Timer 
{
	private static Logger _logger;

	//Singleton Logger
	public static Logger log()
	{
		 if(_logger == null)
		   		_logger = LogManager.getLogger("utility.Navigation.BaseLogger");
		   return _logger;
	}
private long startTime = 0;
private long endTime = 0;
private String LOG_FILE;


 
public Timer() 
{
	
  
//LOG_FILE = aLOG_FILE;
}
 
public void start() throws IOException {
this.startTime = System.currentTimeMillis();

log().info(LOG_FILE, "TIMER START -> "+this.dateParser(this.endTime));
}
 
public void end() throws IOException {
this.endTime = System.currentTimeMillis();
log().info(LOG_FILE, "TIMER END -> "+this.dateParser(this.endTime));
}
 
public Date getStartTime() throws IOException {
log().info(LOG_FILE, "GET START -> "+this.dateParser(this.startTime));
return this.dateParser(this.startTime);
}
 
public Date getEndTime() throws IOException {
log().info(LOG_FILE, "GET END -> "+this.dateParser(this.endTime));
return this.dateParser(this.endTime);
}
 
public long getTotalTime() throws IOException {
long deltaTime = this.endTime - this.startTime;
log().info(LOG_FILE, "GET TOTAL -> "+deltaTime+" ms");
return deltaTime;
}
 
public Date dateParser(long unixTime){
Date date = new Date ();
date.setTime(unixTime);
return date;
}

}
