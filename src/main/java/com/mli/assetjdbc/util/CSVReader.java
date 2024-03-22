package com.mli.assetjdbc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mli.assetjdbc.model.Assets;
import com.opencsv.exceptions.CsvValidationException;

/**
 * CSVReaderUtil 類別提供方法從 CSV 檔案中讀取資料並將其轉換為 Assets 物件列表。
 * 
 * @Author D3031104
 * @version 1.0
 */
public class CSVReader {
    private final static Logger logger = LoggerFactory.getLogger(CSVReader.class);

    /**
     * 從 CSV 檔案中讀取資料並將其轉換為 Assets 物件列表。
     * 
     * @param filePath CSV 檔案的路徑
     * @return Assets 物件列表
     */
    public static List<Assets> readCSV(String filePath) {
        List<Assets> assetsList = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                com.opencsv.CSVReader csvReader = new com.opencsv.CSVReader(bufferedReader);) {
            String[] nextLine;
            csvReader.skip(1);
            while ((nextLine = csvReader.readNext()) != null) {
                logger.info("Parsing id value from CSV: {}", nextLine[0]);

                // CSV 檔案的結構和 Assets 類別的對應關係
                int id = Integer.parseInt(nextLine[0]);

                String assetNumber = nextLine[1];
                String assetName = nextLine[2];
                int userId = Integer.parseInt(nextLine[3]);

                // 日期格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String dateStr = nextLine[4];
                if (dateStr.matches("\\d{4}/\\d{1}/\\d{1}")) {
                    dateStr = dateStr.replaceAll("(\\d{4})/(\\d{1})/(\\d{1})", "$1/0$2/0$3");
                } else if (dateStr.matches("\\d{4}/\\d{1}/\\d{2}")) {
                    dateStr = dateStr.replaceAll("(\\d{4})/(\\d{1})/(\\d{2})", "$1/0$2/$3");
                } else if (dateStr.matches("\\d{4}/\\d{2}/\\d{1}")) {
                    dateStr = dateStr.replaceAll("(\\d{4})/(\\d{2})/(\\d{1})", "$1/$2/0$3");
                }
                LocalDate creationDate = LocalDate.parse(dateStr, formatter);

                double value = Double.parseDouble(nextLine[5]);
                int unitId = Integer.parseInt(nextLine[6]);

                // 建立一個 Assets 物件並將其加入列表
                Assets asset = new Assets(id, assetNumber, assetName, userId, creationDate, value, unitId);
                assetsList.add(asset);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return assetsList;
    }
}
