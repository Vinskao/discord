package com.mli.assetjdbc.dao;

import java.util.List;

import com.mli.assetjdbc.model.Units;

public interface UnitsDAO {

	List<Units> findAll();

	List<Units> findById(int id);

}
