/**
 * Парсер csv-файла
 *
 * @author  Артем Кучумов
 * @version 1.1
 * @since   2017-10-27
 */

package com.insyte.ems.utils.parser.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CsvParser {
    /**
     * Файл, из которого происходит чтение
     */
    private BufferedReader reader = null;

    /**
     * Разделитель данных в строке файла
     */
    private String delimiter = ";";

    /**
     * Шаблон, которому должна удовлетворять дата и время измерения
     */
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public CsvParser(BufferedReader reader){
        this.reader = reader;
    }

    public CsvParser(BufferedReader reader, DateTimeFormatter dateTimeFormatter){
        this.reader = reader;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public CsvParser(BufferedReader reader, String dateTimeFormatterString){
        this.reader = reader;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterString);
    }

    public CsvParser(BufferedReader reader, String dateTimeFormatterString, String delimiter){
        this.reader = reader;
        this.delimiter = delimiter;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterString);
    }

    public CsvParser(BufferedReader reader, DateTimeFormatter dateTimeFormatter, String delimiter){
        this.reader = reader;
        this.delimiter = delimiter;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /**
     * Метод извлекает данные из файла, парсит их и возвращает валидные значения.
     * @return - возвращает список информации о измерениях со счетчика
     */
    public ArrayList<Measure> getMeasures(){
        ArrayList<Measure> measures = new ArrayList<>();
        String line;
        try{
            while((line = reader.readLine()) != null){
                String[] lines = line.split(delimiter);
                Measure measure = getMeasure(lines);
                if(measure != null)
                    measures.add(measure);
            }
            reader.close();
            return measures;
        }
        catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        finally {
            try { reader.close(); }
            catch (IOException ex){ ex.printStackTrace(); }
        }
    }

    /**
     * Метод конвертирует строку из файла в "измерение"
     * @param lines - строка из файла, сплитанутая на отдельные значения
     * @return - возвращаем информацию измерении со счетчика
     */
    private Measure getMeasure(String[] lines){
        if(lines != null && lines.length > 1){
            try{
                Measure measure = new Measure();
                measure.DateTime = LocalDateTime.parse(lines[0], dateTimeFormatter);
                measure.Value = Double.valueOf(lines[1]);
                return measure;
            }
            catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
