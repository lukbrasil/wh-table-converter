/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter.table.service;

/**
 *
 * @author luk
 */
public class AttributeValueFormatException extends Exception {

    private String value;

    public AttributeValueFormatException(String invalidValue) {
        super();
        value = invalidValue;
    }
}
