/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.whtableconverter;

import java.io.FileReader;
import java.util.List;

/**
 *
 * @author luk
 */
public class App {

    public static void main(String[] args) {
        try {
            TableService myClass = new TableService();

            System.out.println("Converting file " + args[0]);

            List<TableService.TableItem> result = myClass.fromCSV(new FileReader(args[0]));

            for (TableService.TableItem item : result) {
                System.out.println(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
