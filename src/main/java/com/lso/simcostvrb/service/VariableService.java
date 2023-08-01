package com.lso.simcostvrb.service;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.repository.VariableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VariableService {
    private VariableRepository repository;

    public VariableCost saveVariable(VariableCost variableCost) {
        return repository.save(variableCost);
    }

    public void dropVariable(VariableCost variableCost){
        repository.delete(variableCost);
    }
    public Optional<VariableCost> findDistinctVariable(Integer id_cost, String name_variable) {
        return repository.findDistinctVariable(id_cost, name_variable);
    }

    public List<VariableCost> findVariableByCost(Integer id_cost) {
        return repository.findVariableByCost(id_cost);
    }

    public Optional<VariableCost> findVariableById(Integer id_variable){
        return  repository.findById(id_variable);
    }

    public void setValueVariable(Integer id_cost, VariableCost variableCost) {
        Optional<VariableCost> optionalVariableCost = repository.findDistinctVariable(id_cost, variableCost.getName_variable());
        VariableCost variableTemp;
        System.out.println(variableCost.getValue());
        if (optionalVariableCost.isPresent()) {
            variableTemp = optionalVariableCost.get();
            if (variableTemp.getType_variable().equalsIgnoreCase("Porcentaje")) {
                variableTemp.setValue(variableCost.getValue() / 100);
                repository.save(variableTemp);
            }else {
                variableTemp.setValue(variableCost.getValue());
                repository.save(variableTemp);
            }
        }
    }
}
