/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import com.jun0rr.pex.Expression;
import com.jun0rr.pex.Parser;
import com.jun0rr.pex.main.StringColumn.Align;
import java.util.Scanner;

/**
 *
 * @author Juno
 */
public class Terminal {
  
  private final Parser parser;
  
  public Terminal() {
    this.parser = new Parser();
  }
  
  public Parser parser() {
    return parser;
  }
  
  public void printVars() {
    StringTable table = new StringTable(String.format("Variables: %d", parser.variables().size()), Align.CENTER, '=', '-');
    StringRow header = new StringRow('|')
        .addColumn("Name", 12, Align.LEFT)
        .addColumn("Value", 48, Align.RIGHT);
    table.addRow(header);
    parser.variables().entrySet().stream()
        .map(e->new StringRow('|')
            .addColumn(e.getKey(), 12, Align.LEFT)
            .addColumn(e.getValue().toString(), 48, Align.RIGHT))
        .forEach(table::addRow);
    System.out.println(table);
  }
  
  public void printOps() {
    StringTable table = new StringTable(String.format("Operations: %d", parser.operations().size()), Align.CENTER, '=', '-');
    StringRow header = new StringRow('|')
        .addColumn("Name", 16, Align.LEFT)
        .addColumn("Token", 16, Align.CENTER)
        .addColumn("PlaceParam", 12, Align.CENTER)
        .addColumn("Arity", 7, Align.RIGHT)
        .addColumn("Priority", 10, Align.RIGHT)
        ;
    table.addRow(header);
    parser.operations().stream()
        .sorted()
        .map(e->new StringRow('|')
            .addColumn(e.getClass().getSimpleName(), 16, Align.LEFT)
            .addColumn(e.toString(), 16, Align.CENTER)
            .addColumn(e.placeParam().name(), 12, Align.CENTER)
            .addColumn(String.valueOf(e.arity()), 7, Align.RIGHT)
            .addColumn(String.valueOf(e.priority()), 10, Align.RIGHT))
        .forEach(table::addRow);
    System.out.println(table);
  }
  
  public void loop() {
    Scanner scanner = new Scanner(System.in);
    System.out.printf("+------------------------+%n");
    System.out.printf("|        JPex 1.0        |%n");
    System.out.printf("| Math Expression Parser |%n");
    System.out.printf("+------------------------+%n%n");
    while(true) {
      try {
        System.out.printf("[JPex]> ");
        String line = scanner.nextLine();
        switch(line) {
          case "vars":
            printVars();
            break;
          case "ops":
            printOps();
            break;
          case "exit":
            System.out.printf("[JPex]> bye!%n");
            System.exit(0);
            break;
          case "stack":
            parser.setShowStack(!parser.isShowStack());
            System.out.printf("[JPex]> set show stack: %s%n", parser.isShowStack());
            break;
          case "rmvar":
            System.out.printf("[JPex]> variable name: ");
            line = scanner.nextLine();
            parser.variables().remove(line);
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
