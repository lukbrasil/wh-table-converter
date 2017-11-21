/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.lukbrasil.whtableconverter.table.Table;
import org.lukbrasil.whtableconverter.table.TableItem;
import org.lukbrasil.whtableconverter.table.expression.RangeAttributeExpression;
import org.lukbrasil.whtableconverter.table.expression.SimpleAttributeExpression;
import org.lukbrasil.whtableconverter.table.parse.ParsedAttribute;
import org.lukbrasil.whtableconverter.table.parse.ParsedFixedAttribute;
import org.lukbrasil.whtableconverter.table.parse.ParsedTable;
import org.lukbrasil.whtableconverter.table.parse.ParsedTableItem;
import org.lukbrasil.whtableconverter.table.parse.ParsedVariableAttribute;
import org.lukbrasil.whtableconverter.table.expression.AttributeExpression;

/**
 *
 * @author luk
 */
public class TableService {

    private static final Pattern FIXED_PATTERN = Pattern.compile("[-]{0,1}\\d+");
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("([-]{0,1}\\d+)\\+(\\d+)d(\\d+)");
    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    private static final Pattern REFERENCE_PATTERN = Pattern.compile("([-]{0,1}\\d+\\+\\d+d\\d+)\\((.+)\\)");

    public Table fromCSV(Reader csv) throws IOException {

        Table result = new Table("");

        Iterator<CSVRecord> it = CSVFormat.DEFAULT.parse(csv).iterator();

        if (it.hasNext()) {
            for (String value : it.next()) {
                if (result.getId().isEmpty()) {
                    result = new Table(value);
                } else {
                    result.add(value);
                }
            }
        }

        while (it.hasNext()) {
            int i = -1;
            String attibuteName = "";

            for (String value : it.next()) {
                if (attibuteName.isEmpty()) {
                    attibuteName = value;
                } else {
                    result.getItem(i).add(attibuteName, value);
                }
                i++;
            }
        }

        return result;
    }

    public ParsedAttribute parseAttribute(String attribute, String value)
        throws AttributeValueFormatException {

        Matcher matcher;

        if ((matcher = FIXED_PATTERN.matcher(value)) != null //
            && matcher.matches()) {
            return new ParsedFixedAttribute(attribute,
                Integer.parseInt(value));
        } else if ((matcher = VARIABLE_PATTERN.matcher(value)) != null //
            && matcher.matches()) {
            return new ParsedVariableAttribute(attribute, Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        } else {
            throw new AttributeValueFormatException(value);
        }
    }

    public ParsedTable parse(Table table) throws AttributeValueFormatException {

        ParsedTable result = new ParsedTable(table.getId());

        for (int i = 0; i < table.size(); i++) {
            TableItem item = table.getItem(i);
            ParsedTableItem parsedItem = new ParsedTableItem(item.getId());

            item.getAttribute().entrySet().forEach((entry) -> {
                AttributeExpression attributeExpression = createAttributeExpression(entry.getKey());

                attributeExpression.evaluate((String attribute) -> {
                    try {
                        parsedItem.add(parseAttribute(attribute, entry.getValue()));
                    } catch (AttributeValueFormatException ex) {
                        Logger.getLogger(TableService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });

            result.add(parsedItem);
        }

        return result;
    }

    public String toJson(ParsedTable table) {
        return (new Gson()).toJson(table);
    }

    public AttributeExpression createAttributeExpression(String attribute) {
        Matcher matcher = RANGE_PATTERN.matcher(attribute);
        if (matcher.matches()) {
            return new RangeAttributeExpression(Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)));
        } else {
            return new SimpleAttributeExpression(attribute);
        }
    }
}
