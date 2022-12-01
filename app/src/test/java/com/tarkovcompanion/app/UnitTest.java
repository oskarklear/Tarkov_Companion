package com.tarkovcompanion.app;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class UnitTest {
    @Test
    public void test1() {
        Item item = new Item();
        item.setId("testItem");
        assertTrue(item.getId().equals("testItem"));
    }
    @Test
    public void test2() {
        Item item = new Item();
        item.setShortName("testItem");
        assertTrue(item.getShortName().equals("testItem"));
    }
    @Test
    public void test3() {
        Item item = new Item();
        item.setDescription("An example item");
        assertTrue(item.getDescription().equals("An example item"));
    }
    @Test
    public void test4() {
        Item item = new Item();
        item.setItemName("testItem");
        assertTrue(item.getItemName().equals("testItem"));
    }
    @Test
    public void test5() {
        Item item = new Item();
        item.setAvg24hPrice(200);
        assertTrue(item.getAvg24hPrice() == 200);
    }
    @Test
    public void test6() {
        Item item = new Item();
        item.setFleaMarketFee(100);
        assertTrue(item.getFleaMarketFee() == 100);
    }
    @Test
    public void test7() {
        Item item = new Item();
        item.setLastLowPrice(5);
        assertTrue(item.getLastLowPrice() == 5);
    }
    @Test
    public void test8() {
        Item item = new Item();
        item.setHeight(50);
        assertTrue(item.getHeight() == 50);
    }
    @Test
    public void test9() {
        Item item = new Item();
        item.setWidth(50);
        assertTrue(item.getWidth() == 50);
    }
    @Test
    public void test10() {
        Item item = new Item();
        item.setIconLink("www.dummylink.com");
        assertTrue(item.getIconLink().equals("www.dummylink.com"));
    }
    @Test
    public void test11() {
        Item item = new Item();
        item.setLargeIconLink("www.dummylink.com");
        assertTrue(item.getLargeIconLink().equals("www.dummylink.com"));
    }
    @Test
    public void test12() {
        DataResponse dr = new DataResponse();
        List<Item> il = new ArrayList<Item>();
        il.add(new Item());
        il.add(new Item());
        il.add(new Item());
        il.add(new Item());
        il.add(new Item());
        dr.setItems(il);
        assertTrue(dr.getItems().equals(il));
    }
}