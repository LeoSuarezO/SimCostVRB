package com.lso.simcostvrb.repository;

import com.lso.simcostvrb.entities.VariableCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariableRepository extends JpaRepository<VariableCost, Integer> {

    @Query("SELECT v FROM VariableCost v WHERE v.id_cost = :id_cost")
    List<VariableCost> findVariableByCost(Integer id_cost);

    @Query("SELECT  v FROM VariableCost  v WHERE v.id_cost = :id_cost AND v.name_variable = :name_variable")
    Optional<VariableCost> findDistinctVariable(Integer id_cost, String name);

}
