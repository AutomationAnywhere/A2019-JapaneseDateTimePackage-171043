package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DateTimeValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.automationanywhere.commandsdk.model.DataType.DATETIME;

/**
 *
 * Converts a Excel serial value to a datetime.
 * Accepts one string inputs.
 * The date string must be Not NULL and providing the output variable is also mandatory.
 *
 * @author Yuji Hirose
 */

@BotCommand(commandType = BotCommand.CommandType.Command)

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "ExcelSerialNumberToDate", label = "[[ExcelSerialNumberToDate.label]]",
        node_label = "[[ExcelSerialNumberToDate.node_label]]", description = "[[ExcelSerialNumberToDate.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[ExcelSerialNumberToDate.return_label]]", return_type = DATETIME, return_required = true)
public class ExcelSerialNumberToDate {

    // Start Date (Excel) ;1970/01/01 00:00:00
    private static final int excelSerialStartTime =25569;
    private static final int secOneDay = 60 * 60 * 24;
    private static final int secTimeDifference =60 * 60 * 9;

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<ZonedDateTime> action(
            @Idx(index = "1", type = AttributeType.TEXT)
            @Pkg(label = "[[ExcelSerialNumberToDate.strExcelSerialNumber.label]]")
            @NotEmpty
                    String strExcelSerialNumber){

        // valideate for null user-defined variable
        if(null==strExcelSerialNumber||strExcelSerialNumber.isEmpty())
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "strExcelSerialNumber"));

        //Business logic
        double dt;

        try {
            dt = Double.valueOf(strExcelSerialNumber);
        }catch(NumberFormatException e){
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "strExcelSerialNumber"));
        }

        dt= ((dt-excelSerialStartTime) * secOneDay)-secTimeDifference;

        long lt = (long) dt;

        Instant i = Instant.ofEpochSecond(lt);
        ZonedDateTime result = ZonedDateTime.ofInstant(i, ZoneId.of("Asia/Tokyo"));

        return new DateTimeValue(result);
    }

}
