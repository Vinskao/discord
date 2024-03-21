package com.mli.assetjdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mli.assetjdbc.dao.UnitsDao;
import com.mli.assetjdbc.model.Units;
@Service
public class UnitsService {
	@Autowired
    private UnitsDao unitsDaoJdbc;
    
    public List<Units> findAllUnits() {
        return unitsDaoJdbc.findAll();
    }
    
    public List<Units> findUnitById(int id) {
        return unitsDaoJdbc.findById(id);
    }
}