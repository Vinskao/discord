package com.mli.assetjdbc.dao;

import java.util.List;

import com.mli.assetjdbc.model.Units;

public interface UnitsDao {

	List<Units> findAll();

	List<Units> findById(int id);

}
