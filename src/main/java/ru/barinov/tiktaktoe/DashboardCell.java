package ru.barinov.tiktaktoe;

/**
 * @author alexey.barinov
 * @version 1.0
 * Date: 17.05.2018
 * Time: 15:23
 */
public class DashboardCell {
  private int row;
  private int col;
  private String value;

  public DashboardCell(int row, int col, String value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
