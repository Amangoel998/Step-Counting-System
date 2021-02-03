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

    public StepCountItem(String serviceIdentifier, Long stepCount) {
        this.serviceIdentifier = serviceIdentifier;
        this.stepCount = new AtomicLong(stepCount);
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
        this.isStopped = false;
    }    

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public Long getStepCount() {
        return stepCount.get();
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

    public StepCountItem(String serviceIdentifier, Long stepCount, Timestamp createdDate, Timestamp modifiedDate,
            Boolean isStopped) {
        this.serviceIdentifier = serviceIdentifier;
        this.stepCount = new AtomicLong(stepCount);
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isStopped = isStopped;
    }

}
