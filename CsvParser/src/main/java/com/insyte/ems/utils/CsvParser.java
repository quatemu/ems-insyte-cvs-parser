package com.insyte.ems.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CsvParser {
    private BufferedReader reader = null;
    private String delimiter = ";";
    private String DatePatternString = null;

    public CsvParser(BufferedReader reader, String datePatternString){
        this.reader = reader;
        this.DatePatternString = datePatternString;

        //this.DatePattern = Pattern.compile(datePatternString);
    }

    public ArrayList<String[]> getDatas(){
        if(reader == null){
            return null;
        }
        ArrayList<String[]> datas = new ArrayList<>();
        String line = null;
        try{
            while((line = reader.readLine()) != null){
                String[] lines = line.split(delimiter);
                datas.add(lines);
            }

            reader.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return datas;
    }

    private boolean isValidDate(String date){
        return date.matches(DatePatternString);
    }
}
