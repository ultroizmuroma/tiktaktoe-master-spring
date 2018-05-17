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
    return getWinner(field.getLastCell());
  }

  public String getWinner(DashboardCell activeCell) {
    int currentCount = countVertical(activeCell);
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countRightDiagonal(activeCell);
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countHorizontal(activeCell);
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countLeftDiagonal(activeCell);
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    if (field.getEmptyCells() == 0) {
      return Dashboard.DEFAULT_EMPTY;
    }
    return null;
  }
//todo: Нужно возвращать не int, а структуру с результатом анализа (от, до, продолжительность)
  private int countVertical(DashboardCell activeCell) {
    int currentCount = 0;
    for (int i = activeCell.getRow() - 1; i > -1; i--) {
      if (field.getCellValue(i, activeCell.getCol()).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(i, activeCell.getCol()).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countLeftDiagonal(DashboardCell activeCell) {
    int currentCount = 0;
    for (int i = activeCell.getRow() - 1, j = activeCell.getCol() - 1; i > -1 && j > -1; i--, j--) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1, j = activeCell.getCol() + 1; i < field.getSize() && j < field.getSize(); i++, j++) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countHorizontal(DashboardCell activeCell) {
    int currentCount = 0;
    for (int i = activeCell.getCol() - 1; i > -1; i--) {
      if (field.getCellValue(activeCell.getRow(), i).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = activeCell.getCol() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(activeCell.getRow(), i).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }

  private int countRightDiagonal(DashboardCell activeCell) {
    int currentCount = 0;
    for (int i = activeCell.getRow() - 1, j = activeCell.getCol() + 1; i > -1 && j > -1; i--, j++) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1, j = activeCell.getCol() - 1; i < field.getSize() && j < field.getSize(); i++, j--) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
      } else {
        break;
      }
    }
    return currentCount + 1;
  }
}
