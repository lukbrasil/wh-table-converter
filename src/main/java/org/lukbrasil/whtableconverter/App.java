/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter;

import org.lukbrasil.whtableconverter.table.service.TableService;
import java.io.FileReader;
import org.lukbrasil.whtableconverter.table.Table;

/**
 *
 * @author luk
 */
public class App {

    public static void main(String[] args) {
        try {
            TableService myClass = new TableService();

            System.out.println("Converting file " + args[0]);

            Table result = myClass.fromCSV(new FileReader(args[0]));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
