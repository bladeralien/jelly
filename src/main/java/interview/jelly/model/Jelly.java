package interview.jelly.model;

/**
 * Created by bladeralien on 2017/2/8.
 */
public abstract class Jelly {

    private Board board;
    private int row;
    private int col;

    public void initialize(Board board, int row, int col) {
        this.board = board;
        this.row = row;
        this.col = col;
    }

    public Boolean fall() {
        int destRow = this.row;
        for (int i = this.row + 1; i < this.board.getRowLen(); i++) {
            if (this.board.getJellies()[i][this.col] == null) {
                destRow = i;
            } else {
                break;
            }
        }

        if (destRow != this.row) {
            int originalRow = this.row;
            this.row = destRow;
            this.board.getJellies()[destRow][this.col] = this;
            this.board.getJellies()[originalRow][this.col] = null;
            return true;
        } else {
            return false;
        }
    }

    public abstract void trigger();

    public abstract char charCode();

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
