package com.mli.assetjdbc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mli.assetjdbc.dto.UnitIdDTO;
import com.mli.assetjdbc.model.Units;
import com.mli.assetjdbc.service.UnitsService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/units")
public class UnitsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private UnitsService unitsService;
    
    /**
     * 檢索所有單位。
     * 
     * @return 單位列表
     */
    @Operation(summary = "檢索所有單位")
    @PostMapping("/find-all")
    public ResponseEntity<List<Units>> getAllUnits() {
        try {
            List<Units> units = unitsService.findAllUnits();
            return new ResponseEntity<>(units, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("檢索所有單位時發生錯誤: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/findById")
    @Operation(summary = "Retrieve a unit by its ID")
    public ResponseEntity<List<Units>> getUnitById(@RequestBody UnitIdDTO unitIdDTO) {
		logger.info("controller, unitIdDTO = {}", unitIdDTO);
		int id = unitIdDTO.getUnitId();
    	try {
            List<Units> unit = unitsService.findUnitById(id);
            return new ResponseEntity<>(unit, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("根據ID檢索單位時發生錯誤: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
