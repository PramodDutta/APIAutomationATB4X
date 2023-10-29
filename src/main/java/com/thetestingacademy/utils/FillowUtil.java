package com.thetestingacademy.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FillowUtil {

    public static String FILEPATH = System.getProperty("user.dir")+"/src/main/java/com/thetestingacademy/resources/TDNN.xlsx";


    public static String fetchDataFromXLSX(String sheetName,String id, String fieldName) throws FilloException {
        String value = null;
        Fillo fillo = new Fillo();
        Connection connection =  fillo.getConnection(FILEPATH);
        String query = "Select * from " + sheetName + " " + "where ID='" + id + "'";

        Recordset recordset = connection.executeQuery(query);
        while(recordset.next()){
            value = recordset.getField(fieldName);
        }
        recordset.close();
        connection.close();
        return value;

    }


}
