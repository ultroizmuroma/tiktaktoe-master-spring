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

  //todo: Разбить на отдельные тесты, а то становится непонятным какой именно тест сломался
  @Test
  public void winConditionTest() {
    Dashboard dashboard = new Dashboard(4, 3);
    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i, 0);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, dashboard.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i, 0);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(0, i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, dashboard.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(0, i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i, i + 1);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, dashboard.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i, i + 1);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getWinner());
    dashboard.refresh();


    for (int i = 0; i < dashboard.getSize(); i++) {
      dashboard.setX(i + 1, dashboard.getSize() - 1 - i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_X, dashboard.getWinner());
    dashboard.refresh();

    for (int i = 0; i < 2; i++) {
      dashboard.setX(i + 1, dashboard.getSize() - 1 - i);
    }
    System.out.println(dashboard.toString());
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getWinner());
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
    Assert.assertEquals(Dashboard.DEFAULT_EMPTY, dashboard.getWinner());
  }

  @Test
  public void completeGameTest() {
	  Dashboard dashboard = new Dashboard();
	  dashboard.setX(1, 1);
	  dashboard.setO(0, 0);
	  dashboard.setX(2, 0);
	  dashboard.setO(0, 2);
	  dashboard.setX(2, 2);
	  dashboard.setO(0, 1);
    System.out.println(dashboard.toString());
	  Assert.assertEquals(Dashboard.DEFAULT_O, dashboard.getWinner());
  }

  @Test
  public void randomGameTest() {
	  Dashboard dashboard = new Dashboard(10, 3);
    Random generator = new Random();
    while (dashboard.getWinner() == null) {
      int row = generator.nextInt(dashboard.getSize());
      int col = generator.nextInt(dashboard.getSize());
      dashboard.setNext(row, col);
    }
    System.out.println(dashboard.toString());
    System.out.println(dashboard.getWinner());
  }
}
