package ru.barinov.tiktaktoe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

  //todo: –азбить на отдельные тесты, а то становитс€ непон€тным какой именно тест сломалс€
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
}
