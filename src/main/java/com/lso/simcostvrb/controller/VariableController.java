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
        return service.saveVariable(variableCost);
    }

    @GetMapping("/{id_cost}")
    public List<VariableCost> findVariableByCost(@PathVariable Integer id_cost) {
        return service.findVariableByCost(id_cost);
    }

    @PostMapping("/updateValue")
    public ResponseEntity<VariableCost> setValueVariable(@RequestBody VariableCost variableCost) {
        Optional<VariableCost> optionalVariableCost = service.findDistinctVariable(variableCost.getId_cost(), variableCost.getName_variable());
        if (optionalVariableCost.isPresent()) {
            return service.setValueVariable(variableCost.getId_cost(), variableCost);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
