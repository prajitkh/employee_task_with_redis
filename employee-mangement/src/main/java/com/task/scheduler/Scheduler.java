package com.task.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Scheduler {

//	@Scheduled(fixedRate = 2000) // every 2 sec call runScheduler method
	public void runScheduler() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		System.out.println("Scheduler method " + dateFormat.format(new Date()));

	}

}
