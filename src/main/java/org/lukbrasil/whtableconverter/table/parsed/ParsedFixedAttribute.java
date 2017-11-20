/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.parsed;

/**
 *
 * @author luk
 */
public class ParsedFixedAttribute extends ParsedAttribute {

    private int value;

    public ParsedFixedAttribute(String attrId, int fixedValue) {
        super(attrId, "fixed");
        value = fixedValue;
    }

    public int getValue() {
        return value;
    }
}
