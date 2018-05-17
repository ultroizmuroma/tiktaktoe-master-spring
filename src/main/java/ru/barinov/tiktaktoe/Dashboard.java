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
  private ActiveCell lastCell = new ActiveCell(0, 0, null);
  private int winCount;
  private int emptyCells;

  public Dashboard() {
    this(3, 3);
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
    lastCell.row = row;
    lastCell.col = col;
    lastCell.value = value;
    emptyCells--;
    return true;
  }

  public boolean setNext(int row, int col) {
    if (DEFAULT_X.equals(lastCell.value)) {
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

  public String getWinner() {
    int currentCount = countVertical();
    if (currentCount >= winCount) {
      return lastCell.value;
    }
    currentCount = countRightDiagonal();
    if (currentCount >= winCount) {
      return lastCell.value;
    }
    currentCount = countHorizontal();
    if (currentCount >= winCount) {
      return lastCell.value;
    }
    currentCount = countLeftDiagonal();
    if (currentCount >= winCount) {
      return lastCell.value;
    }
    if (emptyCells == 0) {
      return DEFAULT_EMPTY;
    }
    return null;
  }

  private int countVertical() {
    int currentCount = 0;
    for (int i = lastCell.row - 1; i > -1; i--) {
      if (getCellValue(i, lastCell.col).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = lastCell.row + 1; i < size; i++) {
      if (getCellValue(i, lastCell.col).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countLeftDiagonal() {
    int currentCount = 0;
    for (int i = lastCell.row - 1, j = lastCell.col - 1; i > -1 && j > -1; i--, j--) {
      if (getCellValue(i, j).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = lastCell.row + 1, j = lastCell.col + 1; i < size && j < size; i++, j++) {
      if (getCellValue(i, j).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countHorizontal() {
    int currentCount = 0;
    for (int i = lastCell.col - 1; i > -1; i--) {
      if (getCellValue(lastCell.row, i).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = lastCell.col + 1; i < size; i++) {
      if (getCellValue(lastCell.row, i).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countRightDiagonal() {
    int currentCount = 0;
    for (int i = lastCell.row - 1, j = lastCell.col + 1; i > -1 && j > -1; i--, j++) {
      if (getCellValue(i, j).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = lastCell.row + 1, j = lastCell.col - 1; i < size && j < size; i++, j--) {
      if (getCellValue(i, j).equals(lastCell.value)) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private class ActiveCell {
    int row;
    int col;
    String value;

    ActiveCell(int row, int col, String value) {
      this.row = row;
      this.col = col;
      this.value = value;
    }
  }
}
