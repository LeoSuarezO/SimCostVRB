package com.lso.simcostvrb.service;

import com.lso.simcostvrb.entities.VariableCost;
import com.lso.simcostvrb.repository.VariableRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VariableService {
    private VariableRepository repository;

    public ResponseEntity<VariableCost> saveVariable(VariableCost variableCost) {
        if (variableExist(repository.findDistinctVariable(variableCost.getId_cost(), variableCost.getName_variable()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(repository.save(variableCost), HttpStatus.CREATED);
    }

    public Optional<VariableCost> findDistinctVariable(Integer id_cost, String name_variable){
        return repository.findDistinctVariable(id_cost, name_variable);
    }

    public Boolean variableExist(Optional<VariableCost> optionalVariableCost) {
        return optionalVariableCost.isPresent();
    }

    public List<VariableCost> findVariableByCost(Integer id_cost) {
        return repository.findVariableByCost(id_cost);
    }

    public ResponseEntity<VariableCost> setValueVariable(Integer id_cost, VariableCost variableCost) {
        Optional<VariableCost> optionalVariableCost = repository.findDistinctVariable(id_cost, variableCost.getName_variable());
        VariableCost variableTemp;
        if (optionalVariableCost.isPresent()) {
            variableTemp = optionalVariableCost.get();
            if (variableTemp.getType_variable().equalsIgnoreCase("Porcentaje")) {
                variableTemp.setValue(variableCost.getValue() / 100);
            } else {
                repository.save(variableTemp);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(variableTemp, HttpStatus.CREATED);
    }

}
