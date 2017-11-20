/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luk
 */
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

    public Map<String, String> getAttribute() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public String toString() {
        return String.format("[%s] id=%s attr=%s", getClass().getSimpleName(), id, attributes);
    }
}
