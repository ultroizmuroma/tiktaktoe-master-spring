package ru.barinov.tiktaktoe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TiktaktoeApplicationTests {

	@Test
	public void setAndGetTest() {
	  Dashboard dashboard = new Dashboard();
    dashboard.setX(1, 1);
    dashboard.setO(1, 2);
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getCellValue(0, 0));
    Assert.assertEquals(Dashboard.DEFAULT_X, dashboard.getCellValue(1, 1));
    Assert.assertEquals(Dashboard.DEFAULT_O, dashboard.getCellValue(1, 2));
  }

  @Test
  public void winConditionTest() {
    Dashboard dashboard = new Dashboard(4, 3);
    DashboardInspector inspector = new DashboardInspector(dashboard);
    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i, 0);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, inspector.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i, 0);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(null, inspector.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(0, i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, inspector.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(0, i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(null, inspector.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i, i + 1);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, inspector.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i, i + 1);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(null, inspector.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i + 1, dashboard.getSize() - 1 - i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, inspector.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i + 1, dashboard.getSize() - 1 - i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(null, inspector.getWinner());
    dashboard.refresh();

    dashboard.setX(0, 0);
    dashboard.setX(0, 2);
    dashboard.setX(0, 3);
    dashboard.setX(1, 1);
    dashboard.setX(1, 2);
    dashboard.setX(2, 0);
    dashboard.setX(2, 3);
    dashboard.setX(3, 0);
    dashboard.setX(3, 2);
    dashboard.setX(3, 3);
    System.out.println(dashboard.toString());
    Assert.assertEquals(null, inspector.getWinner());
  }

  @Test
  public void completeGameTest() {
	  Dashboard dashboard = new Dashboard();
	  DashboardInspector inspector = new DashboardInspector(dashboard);
	  dashboard.setX(1, 1);
	  dashboard.setO(0, 0);
	  dashboard.setX(2, 0);
	  dashboard.setO(0, 2);
	  dashboard.setX(2, 2);
	  dashboard.setO(0, 1);
    System.out.println(dashboard.toString());
	  Assert.assertEquals(Dashboard.DEFAULT_O, inspector.getWinner());
  }

  @Test
  public void randomGameTest() {
	  Dashboard dashboard = new Dashboard(10, 5);
    DashboardInspector inspector = new DashboardInspector(dashboard);
    Random generator = new Random();
    while (inspector.getWinner() == null) {
      int row = generator.nextInt(dashboard.getSize());
      int col = generator.nextInt(dashboard.getSize());
      dashboard.setNext(row, col);
    }
    System.out.println(dashboard.toString());
    System.out.println(inspector.getWinner());
  }

  @Test
  public void computeIntervalTest() {
	  Dashboard dashboard = new Dashboard(10, 3);
	  DashboardInspector inspector = new DashboardInspector(dashboard);
    dashboard.setX(5, 5);
    dashboard.setX(5, 6);
    dashboard.setX(5, 7);
    dashboard.setX(4, 6);
    dashboard.setX(6, 6);
    System.out.println(dashboard.toString());
    InspectionResult horizontalInspectionResult = new InspectionResult(
            new DashboardCell(5, 5, Dashboard.DEFAULT_X),
            new DashboardCell(5, 7, Dashboard.DEFAULT_X),
            3);

    InspectionResult rightDiagonalInspectionResult = new InspectionResult(
            new DashboardCell(5, 6, Dashboard.DEFAULT_X),
            new DashboardCell(5, 6, Dashboard.DEFAULT_X),
            1);

    InspectionResult leftDiagonalInspectionResult = new InspectionResult(
            new DashboardCell(5, 6, Dashboard.DEFAULT_X),
            new DashboardCell(5, 6, Dashboard.DEFAULT_X),
            1);

    InspectionResult verticalInspectionResult = new InspectionResult(
            new DashboardCell(4, 6, Dashboard.DEFAULT_X),
            new DashboardCell(6, 6, Dashboard.DEFAULT_X),
            3);

    DashboardCell activeCell = new DashboardCell(5, 6, Dashboard.DEFAULT_X);
    Assert.assertEquals(horizontalInspectionResult, inspector.countHorizontal(activeCell));
    Assert.assertEquals(rightDiagonalInspectionResult, inspector.countRightDiagonal(activeCell));
    Assert.assertEquals(leftDiagonalInspectionResult, inspector.countLeftDiagonal(activeCell));
    Assert.assertEquals(verticalInspectionResult, inspector.countVertical(activeCell));
  }

  @Test
  public void inspectionResultEquals() {
    DashboardCell from = new DashboardCell(5, 5, Dashboard.DEFAULT_X);
    DashboardCell from2 = new DashboardCell(5, 5, Dashboard.DEFAULT_X);
    DashboardCell to = new DashboardCell(5, 7, Dashboard.DEFAULT_X);
    DashboardCell to2 = new DashboardCell(5, 7, Dashboard.DEFAULT_X);
    InspectionResult inspectionResult = new InspectionResult(from, to, 3);
    InspectionResult inspectionResult2 = new InspectionResult(from2, to2, 3);
    Assert.assertEquals(inspectionResult, inspectionResult2);
  }
}
