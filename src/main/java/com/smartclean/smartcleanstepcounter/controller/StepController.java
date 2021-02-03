package com.smartclean.smartcleanstepcounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.smartclean.smartcleanstepcounter.dto.*;
import com.smartclean.smartcleanstepcounter.services.ScheduledList;

@RestController
public class StepController {

    @Autowired
    private ScheduledList scheduler;

    @PostMapping(path="/create", produces = "application/json", params = {"start", "step"})
    public ResponseEntity<String> createStepper(
        @RequestParam(value = "start", required = true) Integer start,
        @RequestParam(value = "step", required = true) Integer step)
    {
        String result = "";
        try{
            result = scheduler.createCounter(start, step);
        }catch(Exception e){
            result = "Error Ocurred";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    @GetMapping(path="/check", params = {"id"})
    public ResponseEntity<StepCountItem> checkStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        StepCountItem result = null;
        try{
            result = scheduler.checkCounter(id);
        }catch(Exception e){}
        return new ResponseEntity<StepCountItem>(result, HttpStatus.OK);
    }
    @GetMapping(path="/check", produces = "application/json")
    public ResponseEntity<List<StepCountItem>> checkAllStepper()
    {
        List<StepCountItem> items;
        try{
            items = scheduler.checkAllCounter();
        }catch(Exception e){
            items = null;
        }
        return new ResponseEntity<List<StepCountItem>>(items, HttpStatus.OK);
    }
    @GetMapping(path="/render", produces = "text/html")
    public ModelAndView renderStepper()
    {
        ModelAndView model = new ModelAndView("table");
        List<StepCountItem> items;
        try{
            items = scheduler.checkAllCounter();
        }catch(Exception e){
            items = null;
        }
		model.addObject("stepWatcherList", items);
        return model;
    }
    @PutMapping(path="/clear", produces = "application/json", params = {"id"})
    public ResponseEntity<String> clearStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        String result = null;
        try{
            result = scheduler.deleteCounter(id)+", Successfully Deleted";
        }catch(Exception e){
            result = "Cannot be Deleted";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    @PutMapping("/clear/{id}")
    public ResponseEntity<String> clearParamStepper(@PathVariable String id) {
        String result = null;
        try{
            result = scheduler.deleteCounter(id)+", Successfully Deleted";
        }catch(Exception e){
            e.printStackTrace();
            result = "Cannot be Deleted";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    @PutMapping(path="/pause", params = {"id"})
    public ResponseEntity<String> pauseStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        String result = null;
        try{
            result = scheduler.pauseCounter(id)+", Successfully Paused";
        }catch(Exception e){
            e.printStackTrace();
            result = "Cannot be Paused";
        }
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
