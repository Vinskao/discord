package com.mli.assetjdbc.dao;

import java.util.List;

import com.mli.assetjdbc.model.Assets;

public interface AssetsDAO {
    List<Assets> selectAll();

    Assets select(String assetNumber);

    boolean insert(Assets asset);

    boolean update(Assets asset);

    boolean delete(String assetNumber);

    List<Assets> selectByUnitId(int unitId);
    
    int getLastIdFromDatabase();

	int generateNewId();
}
