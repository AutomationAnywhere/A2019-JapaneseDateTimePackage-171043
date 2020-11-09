package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.BooleanValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper.JapaneseHolidayUtils;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static com.automationanywhere.commandsdk.model.DataType.STRING;

/**
 *
 * Get holiday name.
 * Accepts one date inputs.
 * The date string must be Not NULL and providing the output variable is also mandatory.
 *
 * @author Yuji Hirose
 */

@BotCommand(commandType = BotCommand.CommandType.Command)

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "GetHolidayName", label = "[[GetHolidayName.label]]",
        node_label = "[[GetHolidayName.node_label]]", description = "[[GetHolidayName.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[GetHolidayName.return_label]]", return_type = STRING, return_required = true)
public class GetHolidayName {

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<String> action(
            @Idx(index = "1", type = AttributeType.DATETIME)
            @Pkg(label = "[[GetHolidayName.targetDate.label]]")
            @NotEmpty
                    ZonedDateTime targetDate){

        // valideate for null user-defined variable
        if(null==targetDate)
            throw new BotCommandException(MESSAGES.getString("emptyInputDate", "targetDate"));

        //Business logic
        String result ="";

        Instant instant = targetDate.toInstant();
        Date date = Date.from(instant);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        result = JapaneseHolidayUtils.getHolidayName(calendar);
        if(null==result)
            result ="";

        return new StringValue(result);
    }

}
