import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

  private Board wrongBoard;
  private Board rightBoard;

  @Before
  public void setUp() throws Exception {
    int [][] blocks = new int[3][3];
    blocks[0][0] = 8; blocks[0][1] = 1; blocks[0][2] = 3;
    blocks[1][0] = 4; blocks[1][1] = 0; blocks[1][2] = 2;
    blocks[2][0] = 7; blocks[2][1] = 6; blocks[2][2] = 5;

    wrongBoard = new Board(blocks);

    int [][] rightBlocks = new int[3][3];
    rightBlocks[0][0] = 1; rightBlocks[0][1] = 2; rightBlocks[0][2] = 3;
    rightBlocks[1][0] = 4; rightBlocks[1][1] = 5; rightBlocks[1][2] = 6;
    rightBlocks[2][0] = 7; rightBlocks[2][1] = 8; rightBlocks[2][2] = 0;

    rightBoard = new Board(rightBlocks);
  }

  @Test
  public void testDimension() {
    int dim = wrongBoard.dimension();
    assertEquals(3, dim);
  }

  @Test
  public void testHamming() {
    assertEquals(5, wrongBoard.hamming());
    assertEquals(0, rightBoard.hamming());
  }

  @Test
  public void testIsGoal() {
    assertEquals(false, wrongBoard.isGoal());
    assertEquals(true, rightBoard.isGoal());
  }

  @Test
  public void testManhattan() {
    assertEquals(10, wrongBoard.manhattan());
    assertEquals(0, rightBoard.manhattan());
  }
}
