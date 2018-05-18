package ru.barinov.tiktaktoe;

import java.util.logging.Logger;

/**
 * @author alexey.barinov
 * @version 1.0
 * Date: 16.05.2018
 * Time: 11:37
 */
public class Dashboard {
  private static final Logger LOGGER = Logger.getLogger(Dashboard.class.getSimpleName());
  public static final String DEFAULT_X = "X";
  public static final String DEFAULT_O = "O";
  public static final String DEFAULT_EMPTY = " ";

  private int size;
  private String[][] field;
  private DashboardCell lastCell = new DashboardCell(0, 0, null);
  private int winCount;
  private int emptyCells;

  public Dashboard() {
    this(3, 3);
  }

  public Dashboard(int size) {
    this(size, 3);
  }

  public Dashboard(int size, int winCount) {
    this.size = size;
    field = new String[size][size];
    this.winCount = winCount;
    emptyCells = size * size;
    refresh();
  }

  public int getSize() {
    return size;
  }

  public int getWinCount() {
    return winCount;
  }

  public DashboardCell getLastCell() {
    return lastCell;
  }

  public int getEmptyCells() {
    return emptyCells;
  }

  public void refresh() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        field[row][col] = DEFAULT_EMPTY;
      }
    }
  }

  public boolean setO(int row, int col) {
    return setCellValue(row, col, DEFAULT_O);
  }

  public boolean setX(int row, int col) {
    return setCellValue(row, col, DEFAULT_X);
  }

  private boolean setCellValue(int row, int col, String value) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
      LOGGER.warning(String.format("Игноририруется попытка выйти за пределы поля при установке: строка %d, столбец %d", row, col));
      return false;
    }
    if (!DEFAULT_EMPTY.equals(field[row][col]) && !value.equals(DEFAULT_EMPTY)) {
      LOGGER.warning(String.format("Ячейка занята: строка %d, столбец %d", row, col));
      return false;
    }
    field[row][col] = value;
    lastCell.setRow(row);
    lastCell.setCol(col);
    lastCell.setValue(value);
    emptyCells--;
    return true;
  }

  public boolean setNext(int row, int col) {
    if (DEFAULT_X.equals(lastCell.getValue())) {
      return setO(row, col);
    }
    return setX(row, col);
  }

  public String getCellValue(int row, int col) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
      LOGGER.warning(String.format("Игноририруется попытка выйти за пределы поля при получении: строка %d, столбец %d", row, col));
      return DEFAULT_EMPTY;
    }
    return field[row][col];
  }

  @Override
  public String toString() {
    String result = "";
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        result += "[" + field[row][col] + "] ";
      }
      result += "\r\n";
    }
    return result;
  }
}
