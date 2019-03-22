import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

  private Board board;

  @Before
  public void setUp() throws Exception {
    int [][] blocks = new int[4][4];

    board = new Board(blocks);
  }

  @Test
  public void testDimension() {
    int dim = board.dimension();
    assertEquals(4, dim);
  }

}
