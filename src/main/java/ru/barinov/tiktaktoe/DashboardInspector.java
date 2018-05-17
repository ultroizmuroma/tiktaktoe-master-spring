package ru.barinov.tiktaktoe;

/**
 * @author alexey.barinov
 * @version 1.0
 * Date: 17.05.2018
 * Time: 15:11
 */

/**
 * Класс предназначен для работы с доской. Именно он должен анализировать текущее положение на доске.
 */
public class DashboardInspector {
  private Dashboard field;
  private int winCount;

  public DashboardInspector(Dashboard field) {
    this.field = field;
    this.winCount = field.getWinCount();
  }

  public String getWinner() {
    int currentCount = countVertical();
    if (currentCount >= winCount) {
      return field.getLastCell().getValue();
    }
    currentCount = countRightDiagonal();
    if (currentCount >= winCount) {
      return field.getLastCell().getValue();
    }
    currentCount = countHorizontal();
    if (currentCount >= winCount) {
      return field.getLastCell().getValue();
    }
    currentCount = countLeftDiagonal();
    if (currentCount >= winCount) {
      return field.getLastCell().getValue();
    }
    if (field.getEmptyCells() == 0) {
      return Dashboard.DEFAULT_EMPTY;
    }
    return null;
  }

  private int countVertical() {
    int currentCount = 0;
    for (int i = field.getLastCell().getRow() - 1; i > -1; i--) {
      if (field.getCellValue(i, field.getLastCell().getCol()).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = field.getLastCell().getRow() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(i, field.getLastCell().getCol()).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countLeftDiagonal() {
    int currentCount = 0;
    for (int i = field.getLastCell().getRow() - 1, j = field.getLastCell().getCol() - 1; i > -1 && j > -1; i--, j--) {
      if (field.getCellValue(i, j).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = field.getLastCell().getRow() + 1, j = field.getLastCell().getCol() + 1; i < field.getSize() && j < field.getSize(); i++, j++) {
      if (field.getCellValue(i, j).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countHorizontal() {
    int currentCount = 0;
    for (int i = field.getLastCell().getCol() - 1; i > -1; i--) {
      if (field.getCellValue(field.getLastCell().getRow(), i).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = field.getLastCell().getCol() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(field.getLastCell().getRow(), i).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countRightDiagonal() {
    int currentCount = 0;
    for (int i = field.getLastCell().getRow() - 1, j = field.getLastCell().getCol() + 1; i > -1 && j > -1; i--, j++) {
      if (field.getCellValue(i, j).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = field.getLastCell().getRow() + 1, j = field.getLastCell().getCol() - 1; i < field.getSize() && j < field.getSize(); i++, j--) {
      if (field.getCellValue(i, j).equals(field.getLastCell().getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }
}
