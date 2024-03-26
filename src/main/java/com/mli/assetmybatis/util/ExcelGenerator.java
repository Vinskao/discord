package com.mli.assetmybatis.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mli.assetmybatis.dto.AssetsTotalDTO;
import com.mli.assetmybatis.model.Assets;

/**
 * @Author D3031104
 * @version 1.0
 *          從 AssetsTotalDTO 生成 Excel 文件的工具類別。
 */
public class ExcelGenerator {
    /**
     * 從提供的 AssetsTotalDTO 生成 Excel 文件。
     * 
     * @param assetsTotalDTO 包含資產資料的 AssetsTotalDTO。
     * @return Excel 文件的二進位數組表示形式。
     */
    public static byte[] generateAssetExcel(AssetsTotalDTO assetsTotalDTO) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Assets");

            // 標題
            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "Asset Number", "Asset Name", "Unit of Use", "Creation Date", "Value", "Unit ID",
                    "User" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 寫數據
            List<Assets> assetsList = assetsTotalDTO.getAssets();
            int rowNum = 1;
            for (Assets asset : assetsList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(asset.getId());
                row.createCell(1).setCellValue(asset.getAssetNumber());
                row.createCell(2).setCellValue(asset.getAssetName());
                row.createCell(3).setCellValue(asset.getUnitId());
                row.createCell(4)
                        .setCellValue(asset.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                row.createCell(5).setCellValue(asset.getValue());
                row.createCell(6).setCellValue(asset.getUnitId());
                row.createCell(7).setCellValue(asset.getUserId());
            }

            // 添加總計在最後一行
            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(0).setCellValue("Total");
            totalRow.createCell(5).setCellValue(assetsTotalDTO.getTotal());
            try (FileOutputStream fileOut = new FileOutputStream("output/assets.xlsx")) {
                workbook.write(fileOut);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
