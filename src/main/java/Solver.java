import edu.princeton.cs.algs4.MinPQ;

public class Solver {

  //private final Board board;
  private MinPQ<Board> queue = new MinPQ<>();

  public Solver(Board initial) {
    queue.insert(initial);


  }

  public boolean isSolvable() {
    return false;
  }

  public int moves() {
    return 0;
  }

  public Iterable<Board> solution() {
    return null;
  }

  public static void main(String[] args) {

  }

  public static class Node {
    private Board board;
    private Node parent;
    private int moves;

    public Node (Board boardArg, Node parentArg) {
      board = boardArg;
      parent = parentArg;

      if( parentArg == null ) {
        moves = 0;
      } else {
        moves = parentArg.moves + 1;
      }
    }

    public int priority() {
      return board.manhattan();
    }

    public int moves() {
      return moves;
    }

  }

}