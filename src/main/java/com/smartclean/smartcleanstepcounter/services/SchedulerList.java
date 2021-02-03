package com.smartclean.smartcleanstepcounter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.smartclean.smartcleanstepcounter.dto.StepCountItem;
import com.smartclean.smartcleanstepcounter.dao.CounterDataStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerList {
    private static Map<String, List<Object>> map = new ConcurrentHashMap<String, List<Object>>();

    @Autowired
    private GenerateUniqueId guid;
    @Autowired
    private CounterDataStore dataStore;

    public String createCounter(long initialCount, int step) throws Exception {
        String id = guid.generateUniqueId();
        StepCountItem item = new StepCountItem(id, initialCount, step);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final Runnable worker = ()->{
            item.incrementCounter();
        };
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(worker,0, (long)step,
                TimeUnit.SECONDS);

        // System.out.println("result = " + scheduledFuture.get());
        List<Object> ls = new ArrayList<>();
        ls.add(scheduledExecutorService);
        ls.add(scheduledFuture);
        ls.add(item);
        map.put(id, ls);
        return id;
    }

    public StepCountItem checkCounter(String id) throws Exception {
        return (StepCountItem)map.get(id).get(2);
    }

    public List<StepCountItem> checkAllCounter() throws Exception {
        List<StepCountItem> result = new ArrayList<>();
        for(Entry<String,List<Object>> en: map.entrySet()){
            result.add((StepCountItem)en.getValue().get(2));
        }
        result.addAll(dataStore.allCounters());
        return result;
    }

    public synchronized Long deleteCounter(String id) throws Exception {
        List<Object> result = map.remove(id);
        if(result==null){
            throw new Exception("Object Not Found");
        }
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService)result.get(0);
        ScheduledFuture<Long> scheduledFuture = (ScheduledFuture<Long>)result.get(1);
        StepCountItem item = (StepCountItem)result.get(2);
        item.setIsStopped(true);
        scheduledExecutorService.submit(()->{
            scheduledFuture.notify();
            scheduledFuture.cancel(true);
        });
        scheduledExecutorService.shutdown();
        dataStore.persistCounter(item);
        return item.getStepCount();
    }
    
    public synchronized Long pauseCounter(String id) throws Exception {
        List<Object> result = map.get(id);
        if(result==null){
            throw new Exception("Object Not Found");
        }
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService)result.get(0);
        ScheduledFuture<Long> scheduledFuture = (ScheduledFuture<Long>)result.get(1);
        StepCountItem item = (StepCountItem)result.get(2);
        item.setModifiedDate();
        item.setIsStopped(true);
        scheduledExecutorService.submit(()->{
            try{
                scheduledFuture.cancel(true);
            }catch(Exception e){}
        });
        return item.getStepCount();
    }

}
