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
public class ParsedVariableAttribute extends ParsedAttribute {

    private int base;
    private int quantity;
    private int side;

    public ParsedVariableAttribute(String attrId, int fixedBase, int dieQuantity, int dieSide) {
        super(attrId, "variable");
        base = fixedBase;
        quantity = dieQuantity;
        side = dieSide;
    }

    public int getBase() {
        return base;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSide() {
        return side;
    }
}
