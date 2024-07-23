package com.comrade.contrller;

import com.comrade.model.CounterModel;
import com.comrade.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @PostMapping("/add-new-counter")
    public ResponseEntity<CounterModel> createCounter(CounterModel counterModel){
        CounterModel createdCounter = counterService.createCounter(counterModel);
        return new ResponseEntity<>(createdCounter, HttpStatus.CREATED);
    }

    @PutMapping
    public CounterModel increamentCounterByName(CounterModel counterModel){
        return null;
    }

    public ResponseEntity<CounterModel> getCounterByName(String counterName){
        CounterModel counterByName = counterService.getCounterByName(counterName);
      return  new ResponseEntity<>(counterByName, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CounterModel>> getAllConters(){
        List<CounterModel> allConters = counterService.getAllConters();
        return new ResponseEntity<>(allConters, HttpStatus.OK);

    }
}
