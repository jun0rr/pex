/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.main.StringColumn;
import com.jun0rr.pex.main.StringColumn.Align;
import com.jun0rr.pex.main.StringRow;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juno
 */
public class TestStringRow {
  
  
  @Test public void test() {
    StringRow row = new StringRow('|');
    row.addColumn(new StringColumn("hello", 10, Align.LEFT))
        .addColumn(new StringColumn("world", 15, Align.CENTER))
        .addColumn(String.valueOf(Math.random()*10000), 20, Align.RIGHT);
    System.out.println(row);
  }
  
}
