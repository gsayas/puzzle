
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.StreamSupport;
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
    wrongBlocks = new int[3][3];
    wrongBlocks[0][0] = 1; wrongBlocks[0][1] = 2; wrongBlocks[0][2] = 3;
    wrongBlocks[1][0] = 4; wrongBlocks[1][1] = 5; wrongBlocks[1][2] = 6;
    wrongBlocks[2][0] = 8; wrongBlocks[2][1] = 7; wrongBlocks[2][2] = 0;

    wrongBoard = new Board(wrongBlocks);


    assertTrue(solver.isSolvable());
    assertFalse((new Solver(wrongBoard).isSolvable()));
  }

  /*@Test
  public void testNodeMoves() {
    Solver.Node root = new Solver.Node(wrongBoard, null);
    assertEquals(0, root.moves());

    Solver.Node childLevel1 = new Solver.Node(wrongBoard, root);
    assertEquals(1, childLevel1.moves());
  }*/

  @Test
  public void testMoves() {
    assertEquals(4, solver.moves());
  }

  @Test
  public void testSolution() {
    Iterable<Board> solution = solver.solution();

    for (Board board : solution) {
      System.out.println(board.toString());
    }

    assertEquals(5, StreamSupport.stream(solution.spliterator(), false).count());
  }
}
