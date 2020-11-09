package com.automationanywhere.botcommand.japanesedateutils.commands.basic.helper;

public class JapaneseEraChanger {

    private static String[] fullNameEra = {"明治", "大正", "昭和", "平成", "令和"};
    private static String[] singleNameEra = {"明", "大", "昭", "平", "令"};
    private static String[] singlCombinedeNameEra = {"㍾", "㍽", "㍼", "㍻", "㋿"};
    private static String[] singleNumberNameEra = {"1", "2", "3", "4", "5"};
    private static String[] singleAlphabetNameEra = {"M", "T", "S", "H", "R"};


    public static String FullToSingle(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(fullNameEra[num],singleNameEra[num]);
        }
        return result;
    }

    public static String SingleToFull(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(singleNameEra[num],fullNameEra[num]);
        }
        return result;
    }

    public static String FullToSingleCombined(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(fullNameEra[num],singlCombinedeNameEra[num]);
        }
        return result;
    }

    public static String SingleCombinedToFull(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(singlCombinedeNameEra[num],fullNameEra[num]);
        }
        return result;
    }

    public static String FullToNumberSingle(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(fullNameEra[num],singleNumberNameEra[num]);
        }
        return result;
    }

    public static String NumberSingleToFull(String source){
        if(source.isEmpty())
            return source;

        String result =source;

        for (int num = 0; num < 5; num++) {
            result = source.replaceAll(singleNumberNameEra[num],fullNameEra[num]);
        }
        return result;
    }
}
