package com.insyte.ems;

import com.insyte.ems.utils.parser.csv.CsvParser;
import com.insyte.ems.utils.parser.csv.Measure;

import java.io.*;
import java.util.ArrayList;

public class MainApp {
    public static void main(String... args) throws Exception {

        CsvParser parser = new CsvParser(new BufferedReader(
                new FileReader("C:\\Users\\Thomas\\Documents\\GitHub\\ems-insyte-cvs_parser\\CsvParser\\examples\\Умный дом.csv")),
                "dd.MM.yyyy HH:mm");
        ArrayList<Measure> measures = parser.getMeasures();

        StringBuilder sb = new StringBuilder();
        for(Measure measure : measures){
            sb.append(String.valueOf(measure.DateTime) + ": " + String .valueOf(measure.Value) + "\n");
        }

        File file = new File("output.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sb.toString());
        }
    }
}

