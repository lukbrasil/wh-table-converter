/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.whtableconverter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author luk
 */
public class MyClass {

    public class XXX {

        public String id;
        public List<Pair<String, String>> attributes = new ArrayList<>();

        public XXX(String i) {
            id = i;
        }

        public void add(String attribute, String value) {
            attributes.add(new Pair<>(attribute, value));
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(new Pair<String, String>("id", id));
            for (Pair<String, String> attribute : attributes) {
                builder.append(" ").append(attribute);
            }
            return builder.toString();
        }
    }

    public void transform(String fileName) throws FileNotFoundException, IOException {
        List<XXX> result = new ArrayList<>();

        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

        boolean firstRecord = true;
        for (CSVRecord record : records) {
            if (firstRecord) {
                for (String value : record) {
                    result.add(new XXX(value));
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

        for (XXX bla : result) {
            System.out.println(bla);
        }
    }
}
