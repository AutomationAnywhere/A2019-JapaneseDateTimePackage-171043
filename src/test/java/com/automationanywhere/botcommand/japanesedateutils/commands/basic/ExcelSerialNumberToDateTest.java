package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ExcelSerialNumberToDateTest {
    @Test
    public void ExcelSerialNumberToDateTestWithOption1(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strExcelSerialNumber = "43952";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 0, 0, 0, 0, zoneId);

        ExcelSerialNumberToDate ExcelSerialNumberToDate = new ExcelSerialNumberToDate();
        Value<ZonedDateTime> result = ExcelSerialNumberToDate.action(strExcelSerialNumber);

        Assert.assertEquals(result.get(), expectedOutput);
    }

    @Test
    public void ExcelSerialNumberToDateTestWithOption2(){
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String strExcelSerialNumber = "43952.3961805556";
        ZonedDateTime expectedOutput = ZonedDateTime.of(2020, 5, 1, 9, 30, 30, 0, zoneId);

        ExcelSerialNumberToDate ExcelSerialNumberToDate = new ExcelSerialNumberToDate();
        Value<ZonedDateTime> result = ExcelSerialNumberToDate.action(strExcelSerialNumber);

        Assert.assertEquals(result.get(), expectedOutput);
    }
}
