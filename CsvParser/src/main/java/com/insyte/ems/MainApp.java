package com.insyte.ems;

import com.insyte.ems.utils.CsvParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String... args) throws Exception {
        /*Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        LocalDateTime ldt4 = LocalDateTime.parse("11.1.2013 19:12:23");
        LocalDateTime ldt3 = LocalDateTime.parse("11.1.1");
        LocalDateTime ldt5 = LocalDateTime.parse("11.1.2013 19:1");
        LocalDateTime ldt6 = LocalDateTime.parse("11.1.2013 1:1");

        LocalDateTime ldt1 = LocalDateTime.parse("1.1.2013");
        LocalDateTime ldt2 = LocalDateTime.parse("111.1.2013");*/




        CsvParser parser = new CsvParser(new BufferedReader(new FileReader("/Examples/Умный дом.csv")), "");
        ArrayList<String[]> list = parser.getDatas();
        for(String[] lines : list){
            for(String line : lines){
                System.out.print(line + '\t');
            }
            System.out.println();
        }
    }
}

