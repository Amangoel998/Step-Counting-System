package com.smartclean.smartcleanstepcounter;

import com.smartclean.smartcleanstepcounter.dto.StepCountItem;
import com.smartclean.smartcleanstepcounter.services.ScheduledList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartcleanStepCounterApplicationTests {

	@Autowired
	private ScheduledList scheduler;
	
	@Test
	void contextLoads() {
		String newId = "";
		long start = 5;
		int step = 2;
        try{
            newId = scheduler.createCounter(start, step);
        }catch(Exception e){
            newId = "Error Ocurred";
        }
        System.out.println(newId);
		StepCountItem result = null;
        try{
			result = scheduler.checkCounter(newId);
			System.out.println(scheduler.checkCounter(newId));
        }catch(Exception e){}
		try{
			Thread.sleep(3000);
			System.out.println(scheduler.deleteCounter(newId));
		}catch(Exception e){}
		System.out.println(result);
	}

}
