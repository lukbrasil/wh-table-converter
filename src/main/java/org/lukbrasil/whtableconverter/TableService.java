/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author luk
 */
public class TableService {

    public class TableItem {

        private String id;
        private Map<String, String> attributes = new HashMap<>();

        TableItem(String itemId) {
            id = itemId;
        }

        public String getId() {
            return id;
        }

        public Map<String, String> copyAttributes() {
            return new HashMap<String, String>(attributes);
        }

        public TableItem add(String key, String value) {
            attributes.put(key, value);
            return this;
        }

        public String toString() {
            return String.format("[%s] id=%s attr=%s", getClass().getSimpleName(), id, attributes);
        }
    }

    public List<TableItem> fromCSV(Reader csv) throws IOException {
        List<TableItem> result = new ArrayList<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csv);

        boolean firstRecord = true;
        for (CSVRecord record : records) {
            if (firstRecord) {
                for (String value : record) {
                    result.add(new TableItem(value));
                }
                firstRecord = false;
            } else {
                int i = 0;
                String attibuteName = "";
                for (String value : record) {
                    if (i == 0) {
                        attibuteName = value;
                    } else {
                        result.get(i).add(attibuteName, value);
                    }
                    i++;
                }
            }
        }

        return result;
    }
}
