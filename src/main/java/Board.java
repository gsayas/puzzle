import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        if ( isWrongValue(i, j) && blocks[i][j] != 0 )
          manhattan += getShift(blocks[i][j], i, j);
      }
    }

    return manhattan;
  } // sum of Manhattan distances between blocks and goal

  private int getShift(int value, int actualRow, int actualCol) {
    int expectedRow = value / dimension();
    int expectedCol = value - expectedRow*dimension() - 1;
    return Math.abs(actualRow - expectedRow) + Math.abs(actualCol - expectedCol);
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
    return new Board(randomTwinBlocks());
  } // a board that is obtained by exchanging any pair of blocks

  private int[][] randomTwinBlocks() {
    Cell a = getRandomCell();
    Cell b;

    do {
      b = getRandomCell();
    } while (b.equals(a));

    return swapBlocks(a, b);
  }

  private int[][] swapBlocks(Cell a, Cell b) {
    int[][] twin = copyBlocks(blocks);
    twin[a.row][a.col] = blocks[b.row][b.col];
    twin[b.row][b.col] = blocks[a.row][a.col];
    return twin;
  }

  private int[][] copyBlocks(int[][] blocks) {
    int [][] copy = new int[dimension()][dimension()];

    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        copy[i][j] = blocks[i][j];
      }
    }

    return copy;
  }

  @Override
  public boolean equals(Object y) {
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

  private Cell getRandomCell() {
    Cell zero = findZero();
    Cell b;
    do {
      b = new Cell(getRandomIndex(), getRandomIndex());
    } while (b.equals(zero));

    return b;
  }

  private int getRandomIndex() {
    Random r = new Random();
    return r.ints(0, dimension()).findFirst().getAsInt();
  }

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