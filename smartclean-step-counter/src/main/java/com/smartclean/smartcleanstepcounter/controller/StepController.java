package com.smartclean.smartcleanstepcounter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.smartclean.smartcleanstepcounter.dto.*;
@RestController
public class StepController {
    @PostMapping(path="/create", produces = "application/json", params = {"start", "step"})
    public ResponseEntity<NewIdentifier> createStepper(
        @RequestParam(value = "start", required = true) Integer start,
        @RequestParam(value = "step", required = true) Integer step)
    {
        NewIdentifier result = null;
        return new ResponseEntity<NewIdentifier>(result, HttpStatus.OK);
    }
    @GetMapping(path="/check", params = {"id"})
    public ResponseEntity<String> checkStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        // NewIdentifier result = new NewIdentifier("100",100L);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
    @GetMapping(path="/check", produces = "application/json")
    public ResponseEntity<String> checkAllStepper()
    {
        // NewIdentifier result = null;
        return new ResponseEntity<String>("All", HttpStatus.OK);
    }
    @GetMapping(path="/render", produces = "text/html")
    public ModelAndView renderStepper()
    {
        ModelAndView model = new ModelAndView("table");
        List<StepWatchItem> items = new ArrayList<>();
        items.add(new StepWatchItem("123",0L));
        items.add(new StepWatchItem("123",0L));
        items.add(new StepWatchItem("123",0L));
		model.addObject("stepWatcherList", items);
        return model;
    }
    @PutMapping(path="/clear", produces = "application/json", params = {"id"})
    public ResponseEntity<NewIdentifier> clearStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        NewIdentifier result = null;
        return new ResponseEntity<NewIdentifier>(result, HttpStatus.OK);
    }
    @PutMapping("/clear/{id}")
    public ResponseEntity<NewIdentifier> clearParamStepper(@PathVariable String id) {
        NewIdentifier result = null;
        return new ResponseEntity<NewIdentifier>(result, HttpStatus.OK);
    }
    @PutMapping(path="/pause", params = {"id"})
    public ResponseEntity<NewIdentifier> pauseStepper(
        @RequestParam(value = "id", required = true) String id)
    {
        NewIdentifier result = null;
        return new ResponseEntity<NewIdentifier>(result, HttpStatus.OK);
    }
}
