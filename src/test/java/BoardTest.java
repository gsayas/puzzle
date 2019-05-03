import java.util.stream.StreamSupport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {

  private int [][] wrongBlocks = new int[3][3];
  private int [][] rightBlocks = new int[3][3];
  private Board wrongBoard;
  private Board rightBoard;

  @Before
  public void setUp() {
    wrongBlocks = new int[3][3];
    wrongBlocks[0][0] = 8; wrongBlocks[0][1] = 1; wrongBlocks[0][2] = 3;
    wrongBlocks[1][0] = 4; wrongBlocks[1][1] = 0; wrongBlocks[1][2] = 2;
    wrongBlocks[2][0] = 7; wrongBlocks[2][1] = 6; wrongBlocks[2][2] = 5;

    wrongBoard = new Board(wrongBlocks);

    rightBlocks = new int[3][3];
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
    wrongBlocks = new int[2][2];
    wrongBlocks[0][0] = 1; wrongBlocks[0][1] = 0;
    wrongBlocks[1][0] = 2; wrongBlocks[1][1] = 3;
    Board square = new Board(wrongBlocks);

    assertEquals(10, wrongBoard.manhattan());
    assertEquals(0, rightBoard.manhattan());
    assertEquals(3, square.manhattan());
  }

  @Test
  public void testEquals() {
    assertFalse(rightBoard.equals(wrongBoard));
    assertTrue(rightBoard.equals(rightBoard));
    assertTrue(rightBoard.equals(new Board(rightBlocks)));
  }

  @Test
  public void testToString() {
    String expected = "3\n"
        + "1 2 3\n"
        + "4 5 6\n"
        + "7 8 0";

    assertTrue(expected.equals(rightBoard.toString()));
  }

  @Test
  public void testTwin() {
    Board twin = rightBoard.twin();
    /*System.out.println(twin.toString());
    System.out.println(rightBoard.toString());*/

    assertNotEquals(rightBoard, twin);
    assertEquals(rightBoard.dimension(), twin.dimension());
  }

  @Test
  public void testNeighbors() {
    Iterable<Board> neighbors = rightBoard.neighbors();

    /*for (Board board : neighbors) {
      System.out.println(board.toString());
    }*/

    assertEquals(2, StreamSupport.stream(neighbors.spliterator(), false).count());

  }
}
