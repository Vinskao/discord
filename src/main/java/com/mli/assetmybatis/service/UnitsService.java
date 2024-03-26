package com.mli.assetmybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mli.assetmybatis.dao.UnitsDAO;
import com.mli.assetmybatis.model.Units;

/**
 * 處理部門相關業務邏輯的服務類。
 * 
 * @Author D3031104
 * @version 1.0
 */
@Service
@Transactional
public class UnitsService {
    @Autowired
    private UnitsDAO unitsDaoJdbc;

    /**
     * 檢索所有部門的方法。
     * 
     * @return 包含所有部門的列表
     */
    public List<Units> findAllUnits() {
        return unitsDaoJdbc.findAll();
    }

    /**
     * 通過部門 ID 檢索部門的方法。
     * 
     * @param id 要檢索的部門的 ID
     * @return 包含檢索到的部門的列表
     */
    public List<Units> findUnitById(int id) {
        return unitsDaoJdbc.findById(id);
    }
}
