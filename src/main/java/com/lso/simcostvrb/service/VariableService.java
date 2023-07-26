package com.lso.simcostvrb.service;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.exception.VariableCostNotFound;
import com.lso.simcostvrb.repository.VariableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Boolean variableExist(Integer id_variable){
        Optional<VariableCost> optionalVariableCost = repository.findById(id_variable);
        return optionalVariableCost.isPresent();
    }

    public VariableCost setValueVariable(String name_variable, Double value) {
        VariableCost variableCost = repository.findVariableByName(name_variable);
        if (variableExist(variableCost.getId_variable())) {
            variableCost.setValue(value);
            repository.save(variableCost);
        } else {
            throw new VariableCostNotFound("La variable  " + name_variable + "no existe");
        }
        return variableCost;
    }

}
