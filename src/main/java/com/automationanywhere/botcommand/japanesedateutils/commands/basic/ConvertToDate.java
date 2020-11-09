package com.automationanywhere.botcommand.japanesedateutils.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DateTimeValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper.JapaneseEraChanger;
import com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper.KanjiDateTimeFormatter;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import static com.automationanywhere.commandsdk.model.AttributeType.HELP;
import static com.automationanywhere.commandsdk.model.DataType.DATETIME;

/**
 *
 * Converts a string value to a datetime. (Japanese calendar)
 * Accepts one string inputs.
 * The date string must be Not NULL and providing the output variable is also mandatory.
 *
 * @author Yuji Hirose
 */

@BotCommand(commandType = BotCommand.CommandType.Command)

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "ConvertToDate", label = "[[ConvertToDate.label]]",
        node_label = "[[ConvertToDate.node_label]]", description = "[[ConvertToDate.description]]", icon = "DateIcon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[ConvertToDate.return_label]]", return_type = DATETIME, return_required = true)
public class ConvertToDate {

    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.japanesedateutils.messages");

    @Execute
    public Value<ZonedDateTime> action(
            @Idx(index = "1", type = AttributeType.TEXT)
            @Pkg(label = "[[ConvertToDate.targetStrDate.label]]")
            @NotEmpty
                    String targetStrDate,
            @Idx(index = "2", type = AttributeType.RADIO, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "[[ConvertToDate.type.2.1.label]]", value = "prebuilt")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "[[ConvertToDate.type.2.2.label]]", value = "custom"))
            })
            @Pkg(label = "[[ConvertToDate.type.label]]")
            @NotEmpty
                    String type,
            @Idx(index = "2.1.1", type = AttributeType.SELECT, options = {
                    @Idx.Option(index = "2.1.1.1", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.1.label]]", value = "Gy年M月d日")),
                    @Idx.Option(index = "2.1.1.2", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.2.label]]", value = "Gyy年MM月dd日")),
                    @Idx.Option(index = "2.1.1.3", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.3.label]]", value = "GGGGGy.M.d")),
                    @Idx.Option(index = "2.1.1.4", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.4.label]]", value = "GGGGGyy.MM.dd")),
                    @Idx.Option(index = "2.1.1.5", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.5.label]]", value = "GGGGGy/M/d")),
                    @Idx.Option(index = "2.1.1.6", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.6.label]]", value = "GGGGGyy/M/d")),
                    @Idx.Option(index = "2.1.1.7", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.7.label]]", value = "GGGGGy-M-d")),
                    @Idx.Option(index = "2.1.1.8", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.8.label]]", value = "GGGGGyy-MM-dd")),
                    @Idx.Option(index = "2.1.1.9", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.9.label]]", value = "GGGGGyyMd")),
                    @Idx.Option(index = "2.1.1.10", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.10.label]]", value = "GGGGGyyMMd")),
                    @Idx.Option(index = "2.1.1.11", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.11.label]]", value = "1Gy.M.d")),
                    @Idx.Option(index = "2.1.1.12", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.12.label]]", value = "1Gyy.MM.dd")),
                    @Idx.Option(index = "2.1.1.13", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.13.label]]", value = "1Gy/M/d")),
                    @Idx.Option(index = "2.1.1.14", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.14.label]]", value = "1Gyy/M/d")),
                    @Idx.Option(index = "2.1.1.15", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.15.label]]", value = "1Gy-M-d")),
                    @Idx.Option(index = "2.1.1.16", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.16.label]]", value = "1Gyy-MM-dd")),
                    @Idx.Option(index = "2.1.1.17", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.17.label]]", value = "1GyyMd")),
                    @Idx.Option(index = "2.1.1.18", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.18.label]]", value = "1GyyMMd")),
                    @Idx.Option(index = "2.1.1.19", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.19.label]]", value = "uuuu年M月d日")),
                    @Idx.Option(index = "2.1.1.20", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.20.label]]", value = "uuuu年MM月dd日")),
                    @Idx.Option(index = "2.1.1.21", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.21.label]]", value = "uuuu.M.d")),
                    @Idx.Option(index = "2.1.1.22", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.22.label]]", value = "uuuu.MM.dd")),
                    @Idx.Option(index = "2.1.1.23", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.23.label]]", value = "uuuu/M/d")),
                    @Idx.Option(index = "2.1.1.24", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.24.label]]", value = "uuuu/MM/dd")),
                    @Idx.Option(index = "2.1.1.25", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.25.label]]", value = "uuuu-M-d")),
                    @Idx.Option(index = "2.1.1.26", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.26.label]]", value = "uuuu-MM-dd")),
                    @Idx.Option(index = "2.1.1.27", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.27.label]]", value = "uuuuMMdd")),
                    @Idx.Option(index = "2.1.1.28", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.28.label]]", value = "Gy年M月d日(E)")),
                    @Idx.Option(index = "2.1.1.29", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.29.label]]", value = "Gyy年MM月dd日(E)")),
                    @Idx.Option(index = "2.1.1.30", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.30.label]]", value = "EEEE")),
                    @Idx.Option(index = "2.1.1.31", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.31.label]]", value = "Gy年M月d日(E) H時m分")),
                    @Idx.Option(index = "2.1.1.32", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.32.label]]", value = "Gyy年MM月dd日(E) HH時mm分")),
                    @Idx.Option(index = "2.1.1.33", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.33.label]]", value = "uuuu年M月d日(E) H時m分")),
                    @Idx.Option(index = "2.1.1.34", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.34.label]]", value = "uuuu年M月d日(E) HH時mm分")),
                    @Idx.Option(index = "2.1.1.35", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.35.label]]", value = "uuuu/M/d(E) H:m")),
                    @Idx.Option(index = "2.1.1.36", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.36.label]]", value = "uuuu/MM/dd(E) HH:mm")),
                    @Idx.Option(index = "2.1.1.37", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.37.label]]", value = "HH:mm")),
                    @Idx.Option(index = "2.1.1.38", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.38.label]]", value = "aHH時mm分")),
                    @Idx.Option(index = "2.1.1.39", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.39.label]]", value = "aHH:mm"))
//                    ,
//                    @Idx.Option(index = "2.1.1.40", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.40.label]]", value = "kanji"))
                    // for public sector Era system
                    ,@Idx.Option(index = "2.1.1.50", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.50.label]]", value = "NumberGyyMMdd"))
                    ,@Idx.Option(index = "2.1.1.51", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.51.label]]", value = "NumberGyy-MM-dd"))
                    ,@Idx.Option(index = "2.1.1.52", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.52.label]]", value = "NumberGyy/MM/dd"))
                    ,@Idx.Option(index = "2.1.1.53", pkg = @Pkg(label = "[[ConvertToDate.format.2.1.1.53.label]]", value = "NumberGyy.MM.dd"))

            })
            @Pkg(label = "[[ConvertToDate.format.label]]")
            @NotEmpty
                    String format,
            @Idx(index = "2.2.1", type = AttributeType.TEXT)
            @Pkg(label = "[[ConvertToDate.format.label]]")
            @NotEmpty
                    String customFormat) {

        // valideate for null user-defined variable
        if(null==targetStrDate||targetStrDate.isEmpty())
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "targetStrDate"));

        if("custom".equals(type)&&(null==customFormat||customFormat.isEmpty()))
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "customFormat"));

        //Business logic
        ZonedDateTime result = null;

        //Workaround for the problem that 100 years are added when a 2-digit year is specified
        if(format.contains("Gyy"))
            format.replaceAll("Gy+","Gy");

        if("prebuilt".equals(type)) {
            // replace format for single G
            if(format.contains("1Gy")) {
                format = format.replaceAll("1Gy+", "Gy");
                // change 1Gy string to full
                // using static helper
                targetStrDate = JapaneseEraChanger.SingleToFull(targetStrDate);
            }

            // replace format for single Number G
            if(format.contains("NumberG")) {
                format = format.replaceAll("NumberGy+", "Gy");
                // change 1Gy string to full
                // using static helper
                targetStrDate = JapaneseEraChanger.NumberSingleToFull(targetStrDate);
            }

            // for kanji parse
            if("kanji".equals(format)) {
                DateTimeFormatter dtf = KanjiDateTimeFormatter.get();
                try{
                    result = ZonedDateTime.parse(targetStrDate,dtf);
                }catch(DateTimeParseException e){
                        throw new BotCommandException(MESSAGES.getString("datetimeParseError", "targetStrDate"));
                }
            // normal parse
            }else{
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format, Locale.JAPAN).withChronology(JapaneseChronology.INSTANCE).withResolverStyle(ResolverStyle.LENIENT);
                try{
                    result = dtf.parse(targetStrDate, LocalDate::from).atStartOfDay(ZoneId.of("Asia/Tokyo"));
                }catch(DateTimeParseException e){
                    throw new BotCommandException(MESSAGES.getString("datetimeParseError", "targetStrDate"));
                }
            }

        }

        if("custom".equals(type)) {
            //Workaround for the problem that 100 years are added when a 2-digit year is specified
            if(customFormat.contains("Gyy"))
                customFormat.replaceAll("Gyy","Gy");

            // replace format for single G
            if(customFormat.contains("1Gy")) {
                customFormat = customFormat.replaceAll("1Gy", "Gy");
                // change 1Gy string to full
                // using static helper
                targetStrDate = JapaneseEraChanger.SingleToFull(targetStrDate);
            }


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(customFormat, Locale.JAPAN).withChronology(JapaneseChronology.INSTANCE).withResolverStyle(ResolverStyle.LENIENT);
            result = ZonedDateTime.parse(targetStrDate,dtf);
        }


        return new DateTimeValue(result);
    }

    @Idx(index = "2.2.2", type = HELP)
    @Pkg(label = "[[ConvertToDate.format.help]]", description = "[[ConvertToDate.format.help.description]]")
    String help;
}
