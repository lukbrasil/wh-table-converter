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
import org.lukbrasil.whtableconverter.table.Table;
import org.lukbrasil.whtableconverter.table.TableItem;
import org.lukbrasil.whtableconverter.table.parsed.ParsedFixedAttribute;
import org.lukbrasil.whtableconverter.table.parsed.ParsedVariableAttribute;
import org.lukbrasil.whtableconverter.table.service.AttributeValueFormatException;
import org.lukbrasil.whtableconverter.table.service.TableService;

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

        Table result = service.fromCSV(csv);

        Assert.assertEquals("Test table id", "table x", result.getId());
        Assert.assertEquals("Test table size", 2, result.size());

        TableItem human = result.getItem(0);
        Assert.assertEquals("Test human id", "human", human.getId());
        Assert.assertEquals("Test human ws", "10+2d10", human.getValue("ws"));
        Assert.assertEquals("Test human mag", "2", human.getValue("mag"));

        TableItem dwarf = result.getItem(1);
        Assert.assertEquals("Test dwarf id", "dwarf", dwarf.getId());
        Assert.assertEquals("Test dwarf ws", "2", dwarf.getValue("ws"));
        Assert.assertEquals("Test dwarf mag", "20+1d10", dwarf.getValue("mag"));
    }

    @Test
    public void testParseFixedAttribute() throws AttributeValueFormatException {
        TableService service = new TableService();

        Assert.assertEquals(1, ((ParsedFixedAttribute) service.parseAttribute("m",
            "1")).getValue());
        Assert.assertEquals(10, ((ParsedFixedAttribute) service.parseAttribute("m",
            "10")).getValue());
        Assert.assertEquals(0, ((ParsedFixedAttribute) service.parseAttribute("m",
            "0")).getValue());
        Assert.assertEquals(-1, ((ParsedFixedAttribute) service.parseAttribute("m",
            "-1")).getValue());
        Assert.assertEquals(-10, ((ParsedFixedAttribute) service.parseAttribute("m",
            "-10")).getValue());
    }

    @Test
    public void testParseVariableAttribute() throws AttributeValueFormatException {
        TableService service = new TableService();

        ParsedVariableAttribute attribute;

        attribute = (ParsedVariableAttribute) service.parseAttribute("m",
            "20+2d10");
        Assert.assertEquals("Teste base", 20, attribute.getBase());
        Assert.assertEquals("Test quantity", 2, attribute.getQuantity());
        Assert.assertEquals("Test side", 10, attribute.getSide());
    }

    @Test
    public void testToJson() throws IOException, AttributeValueFormatException {
        TableService service = new TableService();
        Reader csv = new StringReader("table x,human,dwarf\nws,10+2d10,2\nmag,2,20+1d10");

        String json = service.toJson(service.parse(service.fromCSV(csv)));

        Assert.assertEquals("{\"id\":\"table x\",\"items\":[{\"id\":\"human\",\"attributes\":[{\"value\":2,\"id\":\"mag\",\"type\":\"fixed\"},{\"base\":10,\"quantity\":2,\"side\":10,\"id\":\"ws\",\"type\":\"variable\"}]},{\"id\":\"dwarf\",\"attributes\":[{\"base\":20,\"quantity\":1,\"side\":10,\"id\":\"mag\",\"type\":\"variable\"},{\"value\":2,\"id\":\"ws\",\"type\":\"fixed\"}]}]}", json);
    }
}
