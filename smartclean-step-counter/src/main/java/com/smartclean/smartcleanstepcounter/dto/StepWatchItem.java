package com.smartclean.smartcleanstepcounter.dto;

public class StepWatchItem {
    private Integer id;
    private String serviceIdentifier;
    private Long stepCount;
    
    public static int index = 0;

    public StepWatchItem(String serviceIdentifier, Long stepCount) {
        this.id=index++;
        this.serviceIdentifier = serviceIdentifier;
        this.stepCount = stepCount;
    }    

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public Long getStepCount() {
        return stepCount;
    }

    public void setStepCount(Long stepCount) {
        this.stepCount = stepCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
