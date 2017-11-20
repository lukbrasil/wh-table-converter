/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luk
 */
public class Table {

    private final String id;
    private final List<TableItem> items = new ArrayList<>();

    public Table(String tableId) {
        id = tableId;
    }

    public void add(String itemId) {
        items.add(new TableItem(itemId));
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
        items.forEach((item) -> {
            builder.append(item).append("\n");
        });

        return builder.toString();
    }
}
