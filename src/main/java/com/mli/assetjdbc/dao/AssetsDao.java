package com.mli.assetjdbc.dao;

import java.util.List;

import com.mli.assetjdbc.model.Assets;

public interface AssetsDao {
    List<Assets> selectAll();
    Assets select(String assetNumber);
    boolean insert(Assets asset);
    boolean update(Assets asset);
    boolean delete(String assetNumber);
	List<Assets> selectByUnitId(int unitId);
}
