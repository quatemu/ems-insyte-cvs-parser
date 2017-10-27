/**
 * Класс, представляющий собой информацию о измерениях со счетчика
 *
 * @author  Артем Кучумов
 * @version 1.1
 * @since   2017-10-27
 */

package com.insyte.ems.utils.parser.csv;

import java.time.LocalDateTime;

public class Measure {
    public LocalDateTime DateTime;
    public long DeviceID;
    public long DataSourceID;
    public double Value;

    /*public Measure(LocalDateTime dateTime, long deviceID, long dataSourceID, double value){
        this.DateTime = dateTime;
        this.DeviceID = deviceID;
        this.DataSourceID = dataSourceID;
        this.Value = value;
    }*/
}
