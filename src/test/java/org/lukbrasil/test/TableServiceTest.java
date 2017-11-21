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
import org.lukbrasil.whtableconverter.table.expression.ParseContext;
import org.lukbrasil.whtableconverter.table.parse.ParsedFixedAttribute;
import org.lukbrasil.whtableconverter.table.parse.ParsedVariableAttribute;
import org.lukbrasil.whtableconverter.table.service.AttributeValueFormatException;
import org.lukbrasil.whtableconverter.table.service.TableService;

/**
 *
 * @author luk
 */
public class TableServiceTest {

    private static TableService service = new TableService();

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

        ParsedVariableAttribute attribute;

        attribute = (ParsedVariableAttribute) service.parseAttribute("m",
            "20+2d10");
        Assert.assertEquals("Teste base", 20, attribute.getBase());
        Assert.assertEquals("Test quantity", 2, attribute.getQuantity());
        Assert.assertEquals("Test side", 10, attribute.getSide());
    }

    @Test
    public void testToJson() throws IOException, AttributeValueFormatException {

        Reader csv = new StringReader("table x,human,dwarf\nws,10+2d10,2\nmag,2,20+1d10");

        String json = service.toJson(service.parse(service.fromCSV(csv)));

        Assert.assertEquals("{\"id\":\"table x\",\"items\":[{\"id\":\"human\",\"attributes\":[{\"value\":2,\"id\":\"mag\",\"type\":\"fixed\"},{\"base\":10,\"quantity\":2,\"side\":10,\"id\":\"ws\",\"type\":\"variable\"}]},{\"id\":\"dwarf\",\"attributes\":[{\"base\":20,\"quantity\":1,\"side\":10,\"id\":\"mag\",\"type\":\"variable\"},{\"value\":2,\"id\":\"ws\",\"type\":\"fixed\"}]}]}", json);
    }

    @Test
    public void testParsedAttibuteExpression() throws AttributeValueFormatException {

        service.createAttributeExpression("a value").evaluate((attribute) -> {
            Assert.assertEquals("Test simple attribute", "a value", attribute);
        });

        service.createAttributeExpression("2-4").evaluate(new ParseContext() {
            private int i = 2;

            @Override
            public void addToItem(String attribute) {
                Assert.assertEquals("Test range attribute", String.valueOf(i++), attribute);

            }
        });
    }

    /*
    public void testParseReferenceAttribute() {
        Reader csv = new StringReader("table 2.1,human,dwarf\n1-3,10,11\n4,11,12");
        Table table = service.fromCSV(csv);

        service.parseAttribute("human", "w", "1d10(table 2.1)", table);
    }
     */
}
