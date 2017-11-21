/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.parse;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luk
 */
public class ParsedTableItem {

    private String id;
    private List<ParsedAttribute> attributes = new ArrayList<>();

    public ParsedTableItem(String itemId) {
        id = itemId;
    }

    public void add(ParsedAttribute attribute) {
        attributes.add(attribute);
    }
}
