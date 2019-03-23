import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

  private Board board;

  @Before
  public void setUp() throws Exception {
    int [][] blocks = new int[3][3];
    blocks[0][0] = 8; blocks[0][1] = 1; blocks[0][2] = 3;
    blocks[1][0] = 4; blocks[1][1] = 0; blocks[1][2] = 2;
    blocks[2][0] = 7; blocks[2][1] = 6; blocks[2][2] = 5;

    board = new Board(blocks);
  }

  @Test
  public void testDimension() {
    int dim = board.dimension();
    assertEquals(3, dim);
  }

  @Test
  public void testHamming() {
    int hamming = board.hamming();
    assertEquals(5, hamming);
  }
}
