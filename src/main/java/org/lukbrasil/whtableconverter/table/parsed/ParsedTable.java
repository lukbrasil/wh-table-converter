/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.parsed;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luk
 */
public class ParsedTable {

    private String id;
    private List<ParsedTableItem> items = new ArrayList<>();

    public ParsedTable(String tableId) {
        id = tableId;
    }

    public void add(ParsedTableItem item) {
        items.add(item);
    }
}
