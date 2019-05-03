import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.List;

public class Solver {

  private List<Board> solution;
  private int moves;
  private Board board;

  public Solver(Board initial) {
    board = initial;
    moves = -1;
  }

  private void solve() {
    MinPQ<Node> queue = new MinPQ<>();
    solution = new ArrayList<>();
    queue.insert(new Node(board, null));
    Node current;

    do {
      current = queue.delMin();
      solution.add(current.getBoard());
      insertNeighbors(queue, current, false);
    } while ( !current.getBoard().isGoal() );

    moves = current.moves();
  }

  private void insertNeighbors(MinPQ<Node> queue, Node current, boolean debug) {
    Iterable<Board> neighbors = current.getBoard().neighbors();
    String debugNeighbors = "";

    for (Board neighbor : neighbors ) {
      if (!neighbor.equals(current.getBoard()) && isNotNeighborParent(neighbor, current)) {
        queue.insert(new Node(neighbor, current));
        debugNeighbors = debugNeighbors + (new Node(neighbor, current)).toString() + "\n";
      }
    }

    if(debug) {
      System.out.println(current.toString());
      System.out.println("-----Neighbors:-----");
      System.out.println(debugNeighbors);
      System.out.println("--------------------\n");
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }

  private boolean isNotNeighborParent(Board neighbor, Node current) {
    return current.getParent() == null || !neighbor.equals(current.getParent().getBoard());
  }

  public boolean isSolvable() {
    MinPQ<Node> queueTrue = new MinPQ<>();
    MinPQ<Node> queueFalse = new MinPQ<>();
    queueTrue.insert(new Node(board, null));
    queueFalse.insert(new Node(board.twin(), null));

    Node currentTrue;
    Node currentFalse;

    while (true) {
      currentTrue = queueTrue.delMin();
      currentFalse = queueFalse.delMin();

      //queueTrue = new MinPQ<>();
      //queueFalse = new MinPQ<>();

      insertNeighbors(queueTrue, currentTrue, false);
      insertNeighbors(queueFalse, currentFalse, false);

      if( currentTrue.getBoard().isGoal() ) return true;
      if( currentFalse.getBoard().isGoal() ) return false;

    }

  }

  public int moves() {
    if( moves == -1 && isSolvable() ) solve();

    return moves;
  }

  public Iterable<Board> solution() {
    if( solution == null) solve();

    return solution;
  }

  public static void main(String[] args) {

  }

  private static class Node implements Comparable<Node> {

    private Board board;
    private Node parent;
    private int moves;
    private int priority;

    public Node (Board boardArg, Node parentArg) {
      board = boardArg;
      parent = parentArg;

      if( parentArg == null ) {
        moves = 0;
      } else {
        moves = parentArg.moves + 1;
      }

      priority = board.manhattan() + moves;
    }

    public int priority() {
      return priority;
    }

    public int moves() {
      return moves;
    }

    public Board getBoard() {
      return board;
    }

    public Node getParent() {
      return parent;
    }

    public int compareTo(Node other) {
      Node that = other;
      if ( this.priority() == that.priority() ){
        return 0;
      }else {
        return this.priority() > that.priority() ? 1 : -1;
      }
    }

    /*public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("\n" + getBoard().toString());
      builder.append("\nmoves: " + moves);
      builder.append("\npriority: " + priority());

      return builder.toString();
    }*/

  }

}