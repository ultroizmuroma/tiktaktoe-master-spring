package ru.barinov.tiktaktoe;

/**
 * @author alexey.barinov
 * @version 1.0
 * Date: 17.05.2018
 * Time: 12:36
 */

/**
 * Искусственный интеллект должен перед каждый своим ходом анализировать поле.
 * Для всех пустых ячеек необходимо высчитывать оценку.
 * В то же время после хода противника, необходимо проверить не образуется ли предвыигрышная комбинация.
 * В таком случае - остановить противника - наиболее приоритетная задача
 */
public class AI {
  private int[][] field;

  public AI(int[][] field) {
    this.field = field;
  }

  
}