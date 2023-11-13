/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.main.StringColumn;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juno
 */
public class TestStringColumn {
  
  
  @Test public void test() {
    System.out.println(new StringColumn("hello world", 10, StringColumn.Align.RIGHT));
  }
  
}
