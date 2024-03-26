package com.mli.assetmybatis.dao;

import java.util.List;

import com.mli.assetmybatis.model.Units;

/**
 * Units DAO MyBatis Mapper
 * 
 * @Author D3031104
 * @version 1.0
 */
public interface UnitsDAO {
    /**
     * Find all units.
     * 
     * @return A list of all units
     */
    List<Units> findAll();

    /**
     * Find units by their ID.
     * 
     * @param id The unit ID
     * @return A list of units with the specified ID
     */
    List<Units> findById(int id);

}
