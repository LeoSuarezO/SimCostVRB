package com.lso.simcostvrb.controller;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.service.VariableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/variable")
@AllArgsConstructor
public class VariableController {

    private final VariableService service;

    @PostMapping
    public ResponseEntity<VariableCost> saveVariable(@RequestBody VariableCost variableCost) {
        Optional<VariableCost> optionalVariableCost = service.findDistinctVariable(variableCost.getId_cost(), variableCost.getName_variable());
        if(optionalVariableCost.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.saveVariable(variableCost), HttpStatus.CREATED);
    }

    @GetMapping("/{id_cost}")
    public List<VariableCost> findVariableByCost(@PathVariable Integer id_cost) {
        return service.findVariableByCost(id_cost);
    }

    @PostMapping("/updateValue")
    public void setValueVariable(@RequestBody VariableCost variableCost) {
        Optional<VariableCost> optionalVariableCost = service.findDistinctVariable(variableCost.getId_cost(), variableCost.getName_variable());
        if (optionalVariableCost.isPresent()) {
            service.setValueVariable(variableCost.getId_cost(), variableCost);
            System.out.println(variableCost);
        }
    }

    @PostMapping("/updateVariable")
    public ResponseEntity<VariableCost> updateVariable (@RequestBody VariableCost variableCost){
        Optional<VariableCost> optionalVariableCost = service.findVariableById(variableCost.getId_variable());
        VariableCost variableTemp;
        if (optionalVariableCost.isPresent()){
            if(service.findDistinctVariable(variableCost.getId_cost(), variableCost.getName_variable()).isPresent()){
                return  new ResponseEntity<>(HttpStatus.CONFLICT);
            }
             variableTemp = optionalVariableCost.get();
            if (variableCost.getType_variable()!=null){
                variableTemp.setType_variable(variableCost.getType_variable());
            }
            if(variableCost.getName_variable() != null){
                variableTemp.setName_variable(variableCost.getName_variable());
            }
            if(variableCost.getDefect_value() != null){
                variableTemp.setDefect_value(variableCost.getDefect_value());
            }
            service.saveVariable(variableTemp);
            return new ResponseEntity<>(variableTemp, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/deleteVariable")
    public ResponseEntity<VariableCost> deleteVariable(@RequestBody VariableCost variableCost){
        Optional<VariableCost> optionalVariableCost = service.findVariableById(variableCost.getId_variable());
        if(optionalVariableCost.isPresent()){
            service.dropVariable(optionalVariableCost.get());
            return new ResponseEntity<>(variableCost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
