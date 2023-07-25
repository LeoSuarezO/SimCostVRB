package com.lso.simcostvrb.service;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.repository.VariableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VariableService {
    private VariableRepository repository;

    public VariableCost saveVariable(VariableCost variableCost){
        return repository.save(variableCost);
    }

    public List<VariableCost> findVariableByCost(Integer id_cost){
        return repository.findVariableByCost(id_cost);
    }

}
