/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.main.StringPad;
import org.junit.jupiter.api.Test;

/**
 *
 * @author F6036477
 */
public class TestStringPad {
  
  @Test public void test1() {
    StringPad pad = new StringPad("hello world");
    System.out.printf("lpad='%s'%n", pad.lpad(" ", 30));
    System.out.printf("cpad='%s'%n", pad.cpad(" ", 30));
    System.out.printf("rpad='%s'%n", pad.rpad(" ", 30));
  }
  
  
  @Test public void test2() {
    System.out.println(StringPad.of("hello world").cpad("-", 8));
  }
  
}
