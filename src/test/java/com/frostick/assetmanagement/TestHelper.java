package com.frostick.assetmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TestHelper {
    public static LocalDate TEST_DATE;

    public static LocalDate TEST_DATE_2;

    public static LocalDate TEST_DATE_3;

    public static double TEST_VALUE_100;

    public static int ASSET_ID_1;

    static {
        TEST_DATE = LocalDate.parse("2022-01-01");
        TEST_VALUE_100 = 100d;
        ASSET_ID_1 = 1;
    }
}
