/**
 * Семантическое представление индексов
 * столбцов в csw-файле
 *
 * @author  Артем Кучумов
 * @version 1.3
 * @since   13-11-2017
 */

package com.insyte.ems.utils.parser.csv;

class ColumnNameToIndex {
    public int DateTime;
    public int Value;
    public int DeviceID;
    public int DataSourceID;

    public ColumnNameToIndex(int[] indexes){
        this.DateTime       = indexes[0];
        this.Value          = indexes[1];
        this.DeviceID       = indexes[2];
        this.DataSourceID   = indexes[3];
    }
}
