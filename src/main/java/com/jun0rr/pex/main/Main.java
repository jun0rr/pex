/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Parser;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Juno
 */
public class Main {
  
  public static void main(String... args) {
    Parser parser = new Parser();
    Scanner scanner = new Scanner(System.in);
    System.out.printf("+------------------------+%n");
    System.out.printf("|        JPex 1.0        |%n");
    System.out.printf("| Math Expression Parser |%n");
    System.out.printf("+------------------------+%n%n");
    if(args != null && args.length > 0) {
      List.of(args).stream().map(parser::parse).forEach(e->System.out.printf("[JPex]> %s = %s%n", e, e.resolve()));
      System.exit(0);
    }
    while(true) {
      try {
        System.out.printf("[JPex]> ");
        String line = scanner.nextLine();
        System.out.printf(">>> line=%s%n", line);
        switch(line) {
          case "vars":
            System.out.printf("[JPex]> Variables(%d)%n", parser.variables().size());
            parser.variables().forEach((k,v)->System.out.printf("[JPex]>   %s = %s%n", k, v));
            break;
          case "exit":
            System.out.printf("[JPex]> bye!%n");
            System.exit(0);
            break;
          default:
            Expression e = parser.parse(line);
            System.out.printf("[JPex]> %s = %s%n", e, e.resolve());
            break;
        }
      }
      catch(Exception e) {
        System.err.printf("[ERROR] [JPex]> %s: %s%n", e.getClass().getSimpleName(), e.getMessage());
      }
    }
  }
  
}
