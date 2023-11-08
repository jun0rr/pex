/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.main.StringColumn;
import com.jun0rr.pex.main.StringColumn.Align;
import com.jun0rr.pex.main.StringRow;
import com.jun0rr.pex.main.StringTable;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juno
 */
public class TestStringTable {
  
  @Test public void test() {
    StringRow row = new StringRow();
    row.addColumn("hello", 10, StringColumn.Align.LEFT, '|')
        .addColumn("world", 15, StringColumn.Align.CENTER, '|')
        .addColumn(String.valueOf(Math.random()*10000+1), 20, Align.RIGHT, '|');
    StringRow header = new StringRow();
    header.addColumn("Name", 10, Align.LEFT, '|')
        .addColumn("Attr", 15, Align.CENTER, '|')
        .addColumn("Value", 20, Align.RIGHT, '|');
    StringTable table = new StringTable("Hello World", Align.CENTER, '=', '-');
    table.addRow(header)
        .addRow(row)
        .addRow(row)
        .addRow(row);
    System.out.println(table);
  }
  
}
