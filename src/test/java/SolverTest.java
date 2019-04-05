
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SolverTest {

  private int [][] wrongBlocks = new int[3][3];
  private Board wrongBoard;
  private Solver solver;

  @Before
  public void setUp() {
    wrongBlocks = new int[3][3];
    wrongBlocks[0][0] = 0; wrongBlocks[0][1] = 1; wrongBlocks[0][2] = 3;
    wrongBlocks[1][0] = 4; wrongBlocks[1][1] = 2; wrongBlocks[1][2] = 5;
    wrongBlocks[2][0] = 7; wrongBlocks[2][1] = 8; wrongBlocks[2][2] = 6;

    wrongBoard = new Board(wrongBlocks);
    solver = new Solver(wrongBoard);
  }

  @Test
  public void testIsSolvable() {
    assertTrue(solver.isSolvable());
  }

  @Test
  public void testNodeMoves() {
    Solver.Node root = new Solver.Node(wrongBoard, null);
    assertEquals(0, root.moves());

    Solver.Node childLevel1 = new Solver.Node(wrongBoard, root);
    assertEquals(1, childLevel1.moves());
  }

}
