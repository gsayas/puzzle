import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

  private final int [][] blocks;

  public Board(int[][] argBlocks) {
    blocks = copyBlocks(argBlocks);
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
        if ( isWrongValue(i, j) && blocks[i][j] != 0 )
          hamming++;
      }
    }

    return hamming;
  } // number of blocks out of place

  private int goalValue(int row, int col) {
    if (row == dimension() - 1  && col == dimension() - 1) {
      return 0;
    }
    return row*dimension() + col + 1;
  }

  public int manhattan() {
    int manhattan = 0;
    int dim = dimension();

    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if ( blocks[i][j] != 0 && isWrongValue(i, j) )
          manhattan += getShift(blocks[i][j], i, j);
      }
    }

    return manhattan;
  } // sum of Manhattan distances between blocks and goal

  private int getShift(int value, int actualRow, int actualCol) {
    int expectedRow = (value-1) / dimension();
    int expectedCol = Math.abs(value - expectedRow*dimension() - 1);
    int shift = Math.abs(actualRow - expectedRow) + Math.abs(actualCol - expectedCol);
    return shift;
  }

  private boolean isWrongValue(int row, int col) {
    return blocks[row][col] != goalValue(row, col);
  }

  public boolean isGoal() {
    int dim = dimension();

    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if ( blocks[i][j] != goalValue(i, j) )
          return false;
      }
    }
    return true;
  } // is this board the goal board?

  public Board twin() {
    return new Board(twinBlocks());
  } // a board that is obtained by exchanging any pair of blocks

  private int[][] twinBlocks() {
    Cell a = getCellIfNotZero(0,0);
    Cell b = getCellIfNotZero(0,1);


    return swapBlocks(a, b);
  }

  private Cell getCellIfNotZero(int x, int y) {
    Cell zero = findZero();
    Cell candidate = new Cell(x, y);

    return !zero.equals(candidate) ? candidate : new Cell(1, 1);
  }

  private int[][] swapBlocks(Cell a, Cell b) {
    int[][] twin = copyBlocks(blocks);
    twin[a.row][a.col] = blocks[b.row][b.col];
    twin[b.row][b.col] = blocks[a.row][a.col];
    return twin;
  }

  private int[][] copyBlocks(int[][] blocks) {
    int dimension = blocks.length;
    int [][] copy = new int[dimension][dimension];

    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        copy[i][j] = blocks[i][j];
      }
    }

    return copy;
  }

  @Override
  public boolean equals(Object y) {
    if(y == null) return false;
    if(y.getClass() != Board.class) return false;

    Board that = (Board) y;
    if(this == y) return true;

    if(this.dimension() != that.dimension()) return false;

    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        if (this.blocks[i][j] != that.blocks[i][j]) return false;
      }
    }

    return true;
  } // does this board equal y?

  public Iterable<Board> neighbors() {
    List<Board> neighbors = new ArrayList<>();
    Cell zero = findZero();
    Iterable<Cell> zeroNeighbors = zero.getNeighbors();

    for (Cell neighbor : zeroNeighbors) {
      neighbors.add(new Board(swapBlocks(zero, neighbor)));
    }

    return neighbors;
  } // all neighboring boards

  private Cell findZero() {
    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        if (this.blocks[i][j] == 0) return new Cell(i, j);
      }
    }

    return null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(dimension());

    for (int i = 0; i < dimension(); i++) {
      builder.append(System.lineSeparator());
      for (int j = 0; j < dimension(); j++) {
        builder.append(blocks[i][j]);
        if (j < dimension() - 1) builder.append(" ");
      }
    }
    //builder.append("\nmanhattan: " + manhattan());

    return builder.toString();
  } // string representation of this board (in the output format specified below)

  public static void main(String[] args) {

  } // unit tests (not graded)

  private class Cell {
    int row;
    int col;

    Cell(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public Iterable<Cell> getNeighbors() {
      List<Cell> neighbors = new ArrayList<>();

      addTopNeighbor(neighbors);
      addBottomNeighbor(neighbors);
      addLeftNeighbor(neighbors);
      addRightNeighbor(neighbors);

      return neighbors;
    }

    private void addRightNeighbor(List<Cell> neighbors) {
      if(col < dimension() - 1) neighbors.add(new Cell(row, col + 1));
    }

    private void addLeftNeighbor(List<Cell> neighbors) {
      if(col > 0) neighbors.add(new Cell(row, col - 1));
    }

    private void addBottomNeighbor(List<Cell> neighbors) {
      if(row < dimension()-1) neighbors.add(new Cell(row + 1, col));
    }

    private void addTopNeighbor(List<Cell> neighbors) {
      if(this.row > 0) neighbors.add(new Cell(row - 1, col));
    }

    public boolean equals(Object b){
      Cell that = (Cell)b;
      if(this.row != that.row || this.col != that.col) return false;

      return true;
    }

  }

}