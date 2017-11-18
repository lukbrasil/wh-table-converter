/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lukbrasil.test;

import org.lukbrasil.whtableconverter.TableService;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
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

        Reader csv = new StringReader(",human,dwarf\nws,10+2d10,2\nmag,2,20+1d10");

        List<TableService.TableItem> result = service.fromCSV(csv);

        Assert.assertEquals("Test size", 3, result.size());

        Assert.assertEquals("Test empty id", "", result.get(0).getId());
        Assert.assertEquals("Test human id", "human", result.get(1).getId());
        Assert.assertEquals("Test dwarf id", "dwarf", result.get(2).getId());

        Map<String, String> attributes;

        attributes = result.get(1).copyAttributes();
        Assert.assertEquals("Test human ws", "10+2d10", attributes.get("ws"));
        Assert.assertEquals("Test human mag", "2", attributes.get("mag"));

        attributes = result.get(2).copyAttributes();
        Assert.assertEquals("Test dwarf ws", "2", attributes.get("ws"));
        Assert.assertEquals("Test dwarf mag", "20+1d10", attributes.get("mag"));
    }
}
