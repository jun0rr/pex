/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jun0rr.pex.main;

import com.jun0rr.pex.main.StringColumn.Align;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Juno
 */
public class StringRow {
  
  private final List<StringColumn> cols;
  
  public StringRow() {
    this.cols = new LinkedList<>();
  }
  
  public List<StringColumn> columns() {
    return cols;
  }
  
  public StringRow addColumn(StringColumn col) {
    cols.add(Objects.requireNonNull(col));
    return this;
  }
  
  public StringRow addColumn(String value, int lenght, Align align, char colsep) {
    cols.add(new StringColumn(value, lenght, align, colsep));
    return this;
  }
  
  public int size() {
    return cols.size();
  }
  
  @Override
  public String toString() {
    if(cols.isEmpty()) {
      throw new IllegalStateException("No columns found");
    }
    StringColumn first = cols.get(0);
    StringBuilder sb = new StringBuilder(String.valueOf(first.colSeparator()));
    cols.forEach(c->sb.append(c));
    return sb.toString();
  }
  
}
