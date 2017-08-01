package ftp;

import ftp.util.FetchData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;




public class MainEnter {
	
	public static void main(String[] args) {
		
		ScheduledExecutorService schService = Executors.newScheduledThreadPool(10);
		schService.scheduleWithFixedDelay(new FetchData(), 7L, 150L, TimeUnit.MILLISECONDS);
		
	}

}
