package com.lso.simcostvrb.controller;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.service.VariableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variable")
@AllArgsConstructor
public class VariableController {

    private final VariableService service;

    @PostMapping
    public VariableCost saveVariable(@RequestBody VariableCost variableCost){
        return service.saveVariable(variableCost);
    }

    @GetMapping("/{id_cost}")
    public List<VariableCost> findVariableByCost(@PathVariable Integer id_cost){
        return service.findVariableByCost(id_cost);
    }
}
