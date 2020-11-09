package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DateTimeValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper.JapaneseHolidayUtils;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static com.automationanywhere.commandsdk.model.DataType.DATETIME;

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
        name = "GetBeforeAfterHoliday", label = "[[GetBeforeAfterHoliday.label]]",
        node_label = "[[GetBeforeAfterHoliday.node_label]]", description = "[[GetBeforeAfterHoliday.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[GetBeforeAfterHoliday.return_label]]", return_type = DATETIME, return_required = true)
public class GetBeforeAfterHoliday {

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<ZonedDateTime> action(
            @Idx(index = "1", type = AttributeType.DATETIME)
            @Pkg(label = "[[GetBeforeAfterHoliday.targetDate.label]]")
            @NotEmpty
                    ZonedDateTime targetDate,
            @Idx(index = "2", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.1.label]]", value = "Holiday")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.2.label]]", value = "SaturdayAndSunday")),
                    @Idx.Option(index = "2.3", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.3.label]]", value = "HolidayExceptSaturdayAndSunday")),
                    @Idx.Option(index = "2.4", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.4.label]]", value = "NationalHoliday")),
                    @Idx.Option(index = "2.5", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.5.label]]", value = "SubstituteHoliday")),
                    @Idx.Option(index = "2.6", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.2.6.label]]", value = "CitizenHoliday"))
            })
            @Pkg(label = "[[GetBeforeAfterHoliday.type.label]]")
            @NotEmpty
                    String type,
            @Idx(index = "3", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.3.1.label]]", value = "After")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "[[GetBeforeAfterHoliday.type.3.2.label]]", value = "Before"))
            })
            @Pkg(label = "[[GetBeforeAfterHoliday.beforeAfter.label]]")
            @NotEmpty
                    String beforeAfter
    ){

        // valideate for null user-defined variable
        if(null==targetDate)
            throw new BotCommandException(MESSAGES.getString("emptyInputDate", "targetDate"));
        if(null==type)
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "type"));
        if(null==beforeAfter)
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "beforeAfter"));

        //Business logic
        ZonedDateTime result;
        boolean isHoliday = true;

        Instant instant = targetDate.toInstant();
        Date date = Date.from(instant);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek;


        while(isHoliday) {

            dayOfWeek =calendar.get(Calendar.DAY_OF_WEEK);

            switch (type) {
                case "Holiday":
                    isHoliday = Calendar.SUNDAY == dayOfWeek ||Calendar.SATURDAY == dayOfWeek || JapaneseHolidayUtils.isHoliday(calendar);
                    break;
                case "SaturdayAndSunday":
                    isHoliday = Calendar.SUNDAY == dayOfWeek ||Calendar.SATURDAY == dayOfWeek;
                    break;
                case "HolidayExceptSaturdayAndSunday":
                    isHoliday = JapaneseHolidayUtils.isHoliday(calendar);
                    break;
                case "NationalHoliday":
                    isHoliday = JapaneseHolidayUtils.isNationalHoliday(calendar);
                    break;
                case "SubstituteHoliday":
                    isHoliday = JapaneseHolidayUtils.isSubstituteHoliday(calendar);
                    break;
                case "CitizenHoliday":
                    isHoliday = JapaneseHolidayUtils.isCitizensHoliday(calendar);
                    break;
                default:
                    throw new BotCommandException(MESSAGES.getString("invalidType", "type"));
            }

            if(false == isHoliday){
                break;
            }else{
                if("After".equals(beforeAfter)){
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                }else if ("Before".equals(beforeAfter)){
                    calendar.add(Calendar.DAY_OF_YEAR, -1);
                }else{
                    throw new BotCommandException(MESSAGES.getString("invalidType", "beforeAfter"));
                }
            }
        }
        instant = calendar.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        result = ZonedDateTime.ofInstant(instant, zone);

        return new DateTimeValue(result);
    }

}
