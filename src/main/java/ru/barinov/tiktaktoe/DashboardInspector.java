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
    int currentCount = countVertical(activeCell).getLength();
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countRightDiagonal(activeCell).getLength();
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countHorizontal(activeCell).getLength();
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    currentCount = countLeftDiagonal(activeCell).getLength();
    if (currentCount >= winCount) {
      return activeCell.getValue();
    }
    if (field.getEmptyCells() == 0) {
      return Dashboard.DEFAULT_EMPTY;
    }
    return null;
  }

  public InspectionResult countVertical(DashboardCell activeCell) {
    int currentCount = 0;
    InspectionResult inspectionResult = new InspectionResult(null, null, 0);
    DashboardCell from = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    DashboardCell to = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    for (int i = activeCell.getRow() - 1; i > -1; i--) {
      if (field.getCellValue(i, activeCell.getCol()).equals(activeCell.getValue())) {
        currentCount++;
        from.setRow(i);
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(i, activeCell.getCol()).equals(activeCell.getValue())) {
        currentCount++;
        to.setRow(i);
      } else {
        break;
      }
    }
    inspectionResult.setFrom(from);
    inspectionResult.setTo(to);
    inspectionResult.setLength(currentCount + 1);
    return inspectionResult;
  }

  public InspectionResult countLeftDiagonal(DashboardCell activeCell) {
    int currentCount = 0;
    InspectionResult inspectionResult = new InspectionResult(null, null, 0);
    DashboardCell from = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    DashboardCell to = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    for (int i = activeCell.getRow() - 1, j = activeCell.getCol() - 1; i > -1 && j > -1; i--, j--) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
        from.setRow(i);
        from.setCol(j);
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1, j = activeCell.getCol() + 1; i < field.getSize() && j < field.getSize(); i++, j++) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
        from.setRow(i);
        from.setCol(j);
      } else {
        break;
      }
    }

    inspectionResult.setFrom(from);
    inspectionResult.setTo(to);
    inspectionResult.setLength(currentCount + 1);
    return inspectionResult;
  }

  public InspectionResult countHorizontal(DashboardCell activeCell) {
    int currentCount = 0;
    InspectionResult inspectionResult = new InspectionResult(null, null, 0);
    DashboardCell from = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    DashboardCell to = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    for (int i = activeCell.getCol() - 1; i > -1; i--) {
      if (field.getCellValue(activeCell.getRow(), i).equals(activeCell.getValue())) {
        currentCount++;
        from.setCol(i);
      } else {
        break;
      }
    }
    for (int i = activeCell.getCol() + 1; i < field.getSize(); i++) {
      if (field.getCellValue(activeCell.getRow(), i).equals(activeCell.getValue())) {
        currentCount++;
        to.setCol(i);
      } else {
        break;
      }
    }
    inspectionResult.setFrom(from);
    inspectionResult.setTo(to);
    inspectionResult.setLength(currentCount + 1);
    return inspectionResult;
  }

  public InspectionResult countRightDiagonal(DashboardCell activeCell) {
    int currentCount = 0;
    InspectionResult inspectionResult = new InspectionResult(null, null, 0);
    DashboardCell from = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    DashboardCell to = new DashboardCell(activeCell.getRow(), activeCell.getCol(), activeCell.getValue());
    for (int i = activeCell.getRow() - 1, j = activeCell.getCol() + 1; i > -1 && j > -1; i--, j++) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
        from.setRow(i);
        from.setCol(i);
      } else {
        break;
      }
    }
    for (int i = activeCell.getRow() + 1, j = activeCell.getCol() - 1; i < field.getSize() && j < field.getSize(); i++, j--) {
      if (field.getCellValue(i, j).equals(activeCell.getValue())) {
        currentCount++;
        to.setRow(i);
        to.setCol(i);
      } else {
        break;
      }
    }
    inspectionResult.setFrom(from);
    inspectionResult.setTo(to);
    inspectionResult.setLength(currentCount + 1);
    return inspectionResult;
  }
}
