/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.expression;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luk
 */
public class RangeAttributeExpression implements AttributeExpression {

    private List<String> ids = new ArrayList<>();

    public RangeAttributeExpression(int min, int max) {
        for (int i = min; i <= max; i++) {
            ids.add(String.valueOf(i));
        }
    }

    public void evaluate(ParseContext context) {
        ids.forEach((id) -> {
            context.addToItem(id);
        });
    }
}
