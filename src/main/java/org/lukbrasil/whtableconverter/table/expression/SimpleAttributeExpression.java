/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.expression;

/**
 *
 * @author luk
 */
public class SimpleAttributeExpression implements AttributeExpression {

    private String id;

    public SimpleAttributeExpression(String id) {
        this.id = id;
    }

    public void evaluate(ParseContext context) {
        context.addToItem(id);
    }
}
