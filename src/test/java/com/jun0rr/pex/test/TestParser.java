/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.test;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Parser;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juno
 */
public class TestParser {

  @Test public void test() {
    try {
      Parser p = new Parser();
      //String exp = "(x=10+x - max 1 5) * x";
      p.setVariable("a", 2.0);
      String exp = "x=((10+10-max 1 5)*a)";
      System.out.printf("[INFO] * Resolving [%s]...%n", exp);
      Expression result = p.parse(exp);
      System.out.println("[INFO] * Parsed: " + result + " = " + result.resolve());
      System.out.printf("[INFO] variables: %s%n", p.variables());
      p.setVariable("a", 3.0);
      System.out.println("[INFO] * Parsed: " + result + " = " + result.resolve());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  
  @Test public void test2() {
    try {
      System.out.println("----------------------------------");
      Parser p = new Parser();
      String exp = "r=(a*m/b)";
      Expression result = p.parse(exp);
      System.out.println("[INFO] * Parsed: " + result + " = " + result.resolve());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test public void test3() {
    try {
      System.out.println("----------------------------------");
      Parser p = new Parser();
      String exp = "a=10";
      Expression result = p.parse(exp);
      System.out.println("[INFO] * Parsed: " + result + " = " + result.resolve());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
