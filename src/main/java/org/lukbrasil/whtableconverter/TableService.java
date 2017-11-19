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

    public class Table {

        private final String id;
        private final List<TableItem> items = new ArrayList<>();

        public Table(String tableId) {
            id = tableId;
        }

        public void add(String itemId) {
            TableItem item = new TableItem(itemId);
            items.add(item);
        }

        public TableItem getItem(int index) {
            return items.get(index);
        }

        public int size() {
            return items.size();
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("[%s] id=%s\n", getClass().getSimpleName(), getId()));
            for (TableItem item : items) {
                builder.append(item).append("\n");
            }
            return builder.toString();
        }
    }

    public class TableItem {

        private final String id;
        private final Map<String, String> attributes = new HashMap<>();

        TableItem(String itemId) {
            id = itemId;
        }

        public String getId() {
            return id;
        }

        public void add(String key, String value) {
            attributes.put(key, value);
        }

        public String getValue(String attribute) {
            return attributes.get(attribute);
        }

        @Override
        public String toString() {
            return String.format("[%s] id=%s attr=%s", getClass().getSimpleName(), id, attributes);
        }
    }

    public Table fromCSV(Reader csv) throws IOException {
        Table result = null;

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csv);

        boolean firstRecord = true;
        for (CSVRecord record : records) {
            if (firstRecord) {
                for (String value : record) {
                    if (result == null) {
                        result = new Table(value);
                    } else {
                        result.add(value);
                    }
                }
                firstRecord = false;
            } else {
                int i = -1;
                String attibuteName = "";
                for (String value : record) {
                    if (i == -1) {
                        attibuteName = value;
                    } else {
                        result.getItem(i).add(attibuteName, value);
                    }
                    i++;
                }
            }
        }

        return result;
    }
}
