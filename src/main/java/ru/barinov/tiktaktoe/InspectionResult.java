package ru.barinov.tiktaktoe;

/**
 * @author alexey.barinov
 * @version 1.0
 * Date: 18.05.2018
 * Time: 10:33
 */
public class InspectionResult {
  private DashboardCell from;
  private DashboardCell to;
  private int length;

  public InspectionResult(DashboardCell from, DashboardCell to, int length) {
    this.from = from;
    this.to = to;
    this.length = length;
  }

  public DashboardCell getFrom() {
    return from;
  }

  public void setFrom(DashboardCell from) {
    this.from = from;
  }

  public DashboardCell getTo() {
    return to;
  }

  public void setTo(DashboardCell to) {
    this.to = to;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || to == null || from == null) {
      return false;
    }
    InspectionResult inspectionResult = (InspectionResult) obj;
    return to.equals(inspectionResult.getTo()) && from.equals(inspectionResult.getFrom());
  }

  @Override
  public String toString() {
    String result = "";
    if(from != null) {
      result += "from: " + from.toString() + ", ";
    }
    if (to != null) {
      result += "to: " + to.toString() + ", ";
    }
    result += "length: " + length;
    return result;
  }
}
