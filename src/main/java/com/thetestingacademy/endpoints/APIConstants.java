package com.thetestingacademy.endpoints;

import com.codoid.products.exception.FilloException;
import com.thetestingacademy.utils.FillowUtil;
import com.thetestingacademy.utils.PropertyReaderUtil;

public class APIConstants {

   public static String BASE_URL;

    static {
        try {
            BASE_URL = FillowUtil.fetchDataFromXLSX("Sheet1","baseurl","Value");
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }

    //    public static String BASE_URL;
    public static String CREATE_BOOKING;
    public static String UPDATE_BOOKING;

    static {
        try {
//            BASE_URL = PropertyReaderUtil.readyKey("url");
            CREATE_BOOKING = PropertyReaderUtil.readyKey("CREATE_BOOKING");
            UPDATE_BOOKING = PropertyReaderUtil.readyKey("UPDATE_BOOKING");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
