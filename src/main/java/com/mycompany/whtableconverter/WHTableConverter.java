/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.whtableconverter;

/**
 *
 * @author luk
 */
public class WHTableConverter {

    public static void main(String[] args) {
        try {
            MyClass myClass = new MyClass();

            myClass.transform(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
