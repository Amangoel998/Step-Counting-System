package com.smartclean.smartcleanstepcounter.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class StepCountItem {
    private String serviceIdentifier;
    private AtomicLong stepCount;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Boolean isStopped;
    private int stepTime;

    public StepCountItem(String serviceIdentifier, Long stepCount, int step) {
        this.serviceIdentifier = serviceIdentifier;
        this.stepCount = new AtomicLong(stepCount);
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
        this.stepTime = step;
        this.isStopped = false;
    }    

    public StepCountItem(Timestamp createdDate, String serviceIdentifier,  Timestamp modifiedDate,
            Boolean isStopped) {
        
    }

    public StepCountItem(String serviceIdentifier, Long stepCount, int step, Timestamp createdDate, Timestamp modifiedDate, boolean isStopped) {
        this.serviceIdentifier = serviceIdentifier;
        this.stepCount = new AtomicLong(stepCount);
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.stepTime = step;
        this.isStopped = isStopped;
    }


	public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public Long getStepCount() {
        return stepCount.get();
    }

    public Integer getStepTime() {
        return this.stepTime;
    }

    public Long incrementCounter() {
        return stepCount.incrementAndGet();
    }

    public void setModifiedDate(){
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "StepCountItem [ServiceIdentifier=" + serviceIdentifier + ", stepCount=" + stepCount
                + "]";
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public Boolean getIsStopped() {
        return isStopped;
    }

    public void setIsStopped(Boolean isStopped) {
        this.isStopped = isStopped;
    }


}
