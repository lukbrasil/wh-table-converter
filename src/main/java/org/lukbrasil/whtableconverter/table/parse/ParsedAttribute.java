/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.parse;

/**
 *
 * @author luk
 */
public class ParsedAttribute {

    private String id;
    private String type;

    public ParsedAttribute(String attrId, String attrType) {
        id = attrId;
        type = attrType;
    }

    public String getId() {
        return id;
    }
}
