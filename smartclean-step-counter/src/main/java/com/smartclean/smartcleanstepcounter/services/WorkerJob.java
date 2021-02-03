package com.smartclean.smartcleanstepcounter.services;

import com.smartclean.smartcleanstepcounter.dto.StepCountItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WorkerJob implements Runnable{
    private StepCountItem item;
    public WorkerJob(StepCountItem item){
        this.item = item;
    }
    public void run(){
        item.incrementCounter();
    }
    public Long getCount(){
        return this.item.getStepCount();
    }
}
