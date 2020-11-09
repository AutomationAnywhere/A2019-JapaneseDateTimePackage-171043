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
 * Get previous/next holiday.
 * Accepts one date inputs.
 * The date variable must be Not NULL and providing the output variable is also mandatory.
 *
 * @author Yuji Hirose
 */

@BotCommand(commandType = BotCommand.CommandType.Command)

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "GetPreviousNextHoliday", label = "[[GetPreviousNextHoliday.label]]",
        node_label = "[[GetPreviousNextHoliday.node_label]]", description = "[[GetPreviousNextHoliday.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[GetPreviousNextHoliday.return_label]]", return_type = DATETIME, return_required = true)
public class GetPreviousNextHoliday {

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<ZonedDateTime> action(
            @Idx(index = "1", type = AttributeType.DATETIME)
            @Pkg(label = "[[GetPreviousNextHoliday.targetDate.label]]")
            @NotEmpty
                    ZonedDateTime targetDate,
            @Idx(index = "2", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.1.label]]", value = "Holiday")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.2.label]]", value = "SaturdayAndSunday")),
                    @Idx.Option(index = "2.3", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.3.label]]", value = "HolidayExceptSaturdayAndSunday")),
                    @Idx.Option(index = "2.4", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.4.label]]", value = "NationalHoliday")),
                    @Idx.Option(index = "2.5", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.5.label]]", value = "SubstituteHoliday")),
                    @Idx.Option(index = "2.6", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.2.6.label]]", value = "CitizenHoliday"))
            })
            @Pkg(label = "[[GetPreviousNextHoliday.type.label]]")
            @NotEmpty
                    String type,
            @Idx(index = "3", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.3.1.label]]", value = "Next")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "[[GetPreviousNextHoliday.type.3.2.label]]", value = "Previous"))
            })
            @Pkg(label = "[[GetPreviousNextHoliday.PreviousNext.label]]")
            @NotEmpty
                    String PreviousNext
    ){

        // valideate for null user-defined variable
        if(null==targetDate)
            throw new BotCommandException(MESSAGES.getString("emptyInputDate", "targetDate"));
        if(null==type)
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "type"));
        if(null==PreviousNext)
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "PreviousNext"));

        //Business logic
        ZonedDateTime result;
        boolean isHoliday = false;

        Instant instant = targetDate.toInstant();
        Date date = Date.from(instant);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek;


        while(!isHoliday) {

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

            if(true == isHoliday){
                break;
            }else{
                if("Next".equals(PreviousNext)){
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                }else if ("Previous".equals(PreviousNext)){
                    calendar.add(Calendar.DAY_OF_YEAR, -1);
                }else{
                    throw new BotCommandException(MESSAGES.getString("invalidType", "PreviousNext"));
                }
            }
        }
        instant = calendar.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        result = ZonedDateTime.ofInstant(instant, zone);

        return new DateTimeValue(result);
    }

}
