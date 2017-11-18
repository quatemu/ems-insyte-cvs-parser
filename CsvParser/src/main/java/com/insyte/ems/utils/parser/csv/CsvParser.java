/**
 * Парсер csv-файла
 *
 * @author  Артем Кучумов
 * @version 1.3
 * @since   13-11-2017
 */

package com.insyte.ems.utils.parser.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CsvParser {
    private final static int COUNT_OF_COLUMNS = 4;

    /**
     * Файл, из которого происходит чтение
     */
    private BufferedReader reader = null;

    /**
     * Список индексов столбцов, по которым нужно искать данные в csv-файле
     * Index[0] = индекс столбца, в котором находится дата и время измерения
     * Index[1] = индекс столбца, в котором находится значение измерения
     * Index[2] = индекс столбца, в котором находится идентификатор устройства
     * Index[3] = индекс столбца, в котором находится идентификатор источника данных
     * Если в файле нет значений для некоторого столбца, то индекс равен любому отрицательному числу.
     * Нумерация столбцов начинается с нуля.
     */
    private ColumnNameToIndex indexes = null;

    /**
     * Разделитель данных в строке файла
     */
    private String delimiter = ";";
    public void setDelimiter(String delimiter){
        if(delimiter == null){
            throw new NullPointerException();
        }
        this.delimiter = delimiter;
    }

    /**
     * Шаблон даты и времени измерения
     */
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter){
        if(dateTimeFormatter == null){
            throw new NullPointerException();
        }
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public void setDateTimeFormatter(String dateTimeFormatterString){
        if(dateTimeFormatter == null){
            throw new NullPointerException();
        }
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatterString);
    }


    public CsvParser(BufferedReader reader, int[] indexes) {
        if(reader == null || indexes == null){
            throw new NullPointerException();
        }
        else if(indexes.length != COUNT_OF_COLUMNS){
            throw new IllegalStateException(String.format("Массив индексов должен состоять из %d значений.", COUNT_OF_COLUMNS));
        }
        this.reader = reader;
        this.indexes = new ColumnNameToIndex(indexes);
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
                if(measure != null){
                    measures.add(measure);
                }
            }
            reader.close();
            return measures;
        }
        catch (IOException ex){ ex.printStackTrace(); }
        finally {
            try { reader.close(); }
            catch (IOException ex){ ex.printStackTrace(); }
        }
        return null;
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
                if(indexes.DateTime >= 0){
                    measure.DateTime = LocalDateTime.parse(lines[indexes.DateTime], dateTimeFormatter);
                }
                if(indexes.Value >= 0){
                    measure.Value = Double.valueOf(lines[indexes.Value]);
                }
                if(indexes.DeviceID >= 0){
                    measure.DeviceID = Long.valueOf(lines[indexes.DeviceID]);
                }
                if(indexes.DataSourceID >= 0){
                    measure.DataSourceID = Long.valueOf(lines[indexes.DataSourceID]);
                }
                return measure;
            }
            catch (Exception ex){ ex.printStackTrace(); }
        }
        return null;
    }
}
