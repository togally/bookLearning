package com.togally.support.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;

import java.util.Map;

public class ExcelUtils {

    /**
     * 提取注释
     * @param file
     */
    public static void extractComment(String file){
        Sheet sheet = ExcelUtil.getReader(file).getSheet();
        ExcelWriter excelWriter = ExcelUtil.getWriter(file);
        Map<CellAddress, ? extends Comment> map = sheet.getCellComments();
        for (Map.Entry<CellAddress, ? extends Comment> entry : map.entrySet()) {
            CellAddress cellAddress = entry.getKey();
            excelWriter.writeCellValue(cellAddress.getColumn(),cellAddress.getRow(),entry.getValue().getString());
        }
        excelWriter.flush();
        excelWriter.close();
    }
}
