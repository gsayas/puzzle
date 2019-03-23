public class Board {

  private int [][] blocks;

  public Board(int[][] argBlocks) {
    blocks = argBlocks;

  } // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)

  public int dimension() {
    return blocks.length;
  } // board dimension n

  public int hamming() {
    int hamming = 0;
    int dim = dimension();

    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if ( blocks[i][j] != goalValue(i, j) )
          hamming++;
      }
    }

    return hamming - 1;
  } // number of blocks out of place

  private int goalValue(int row, int col) {
    if (row == dimension() - 1  && col == dimension() - 1) {
      return 0;
    }
    return row*dimension() + col + 1;
  }

  public int manhattan() {
    return 0;
  } // sum of Manhattan distances between blocks and goal

  public boolean isGoal() {
    return false;
  } // is this board the goal board?

  public Board twin() {
    return null;
  } // a board that is obtained by exchanging any pair of blocks

  public boolean equals(Object y) {
    return false;
  } // does this board equal y?

  public Iterable<Board> neighbors() {
    return null;
  } // all neighboring boards

  public String toString() {
    return "";
  } // string representation of this board (in the output format specified below)

  public static void main(String[] args) {

  } // unit tests (not graded)

}