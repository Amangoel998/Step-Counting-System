package com.smartclean.smartcleanstepcounter.services;

import java.util.List;

import com.smartclean.smartcleanstepcounter.dto.StepCountItem;

public interface ScheduledList {
    public String createCounter(long initialCount, int step) throws Exception;
    public StepCountItem checkCounter(String id) throws Exception;
    public List<StepCountItem> checkAllCounter() throws Exception;
    public Long deleteCounter(String id) throws Exception;
    public Long pauseCounter(String id) throws Exception;
}
