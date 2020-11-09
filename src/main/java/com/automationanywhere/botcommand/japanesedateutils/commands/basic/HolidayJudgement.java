package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.BooleanValue;
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

import static com.automationanywhere.commandsdk.model.DataType.BOOLEAN;

/**
 * Determine if it is a holiday.
 * Accepts one date inputs.
 * The date string must be Not NULL and providing the output variable is also mandatory.
 *
 * @author Yuji Hirose
 */

@BotCommand(commandType = BotCommand.CommandType.Command)

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "HolidayJudgement", label = "[[HolidayJudgement.label]]",
        node_label = "[[HolidayJudgement.node_label]]", description = "[[HolidayJudgement.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[HolidayJudgement.return_label]]", return_type = BOOLEAN, return_required = true)
public class HolidayJudgement {

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<Boolean> action(
            @Idx(index = "1", type = AttributeType.DATETIME)
            @Pkg(label = "[[HolidayJudgement.targetDate.label]]")
            @NotEmpty
                    ZonedDateTime targetDate,
            @Idx(index = "2", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "[[HolidayJudgement.type.2.1.label]]", value = "Holiday")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "[[HolidayJudgement.type.2.2.label]]", value = "NationalHoliday")),
                    @Idx.Option(index = "2.3", pkg = @Pkg(label = "[[HolidayJudgement.type.2.3.label]]", value = "SubstituteHoliday")),
                    @Idx.Option(index = "2.4", pkg = @Pkg(label = "[[HolidayJudgement.type.2.4.label]]", value = "CitizenHoliday"))
            })
            @Pkg(label = "[[HolidayJudgement.type.label]]")
            @NotEmpty
                    String type
    ) {

        // valideate for null user-defined variable
        if (null == targetDate)
            throw new BotCommandException(MESSAGES.getString("emptyInputDate", "targetDate"));
        if (null == type)
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "type"));

        //Business logic
        boolean result = true;

        Instant instant = targetDate.toInstant();
        Date date = Date.from(instant);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (type) {
            case "Holiday":
                result = JapaneseHolidayUtils.isHoliday(calendar);
                break;
            case "NationalHoliday":
                result = JapaneseHolidayUtils.isNationalHoliday(calendar);
                break;
            case "SubstituteHoliday":
                result = JapaneseHolidayUtils.isSubstituteHoliday(calendar);
                break;
            case "CitizenHoliday":
                result = JapaneseHolidayUtils.isCitizensHoliday(calendar);
                break;
            default:
                throw new BotCommandException(MESSAGES.getString("invalidType", "type"));
        }
        return new BooleanValue(result);
    }

}
