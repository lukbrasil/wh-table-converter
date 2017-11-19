/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lukbrasil.whtableconverter.TableService;

/**
 *
 * @author luk
 */
public class TableServiceTest {

    public TableServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFromCSV() throws IOException {
        TableService service = new TableService();

        Reader csv = new StringReader("table x,human,dwarf\nws,10+2d10,2\nmag,2,20+1d10");

        TableService.Table result = service.fromCSV(csv);

        Assert.assertEquals("Test table id", "table x", result.getId());
        Assert.assertEquals("Test table size", 2, result.size());

        TableService.TableItem human = result.getItem(0);
        Assert.assertEquals("Test human id", "human", human.getId());
        Assert.assertEquals("Test human ws", "10+2d10", human.getValue("ws"));
        Assert.assertEquals("Test human mag", "2", human.getValue("mag"));

        TableService.TableItem dwarf = result.getItem(1);
        Assert.assertEquals("Test dwarf id", "dwarf", dwarf.getId());
        Assert.assertEquals("Test dwarf ws", "2", dwarf.getValue("ws"));
        Assert.assertEquals("Test dwarf mag", "20+1d10", dwarf.getValue("mag"));
    }
}
