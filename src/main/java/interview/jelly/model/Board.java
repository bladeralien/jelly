package interview.jelly.model;

import java.util.Random;

/**
 * Created by bladeralien on 2017/2/8.
 */
public class Board {

    private Jelly jellies[][];
    private int rowLen;
    private int colLen;

    public Board() {
        this.rowLen = 8;
        this.colLen = 8;
        this.jellies = new Jelly[8][8];
    }

    public Board(int rowLen, int colLen) {
        this.rowLen = rowLen;
        this.colLen = colLen;
        this.jellies = new Jelly[rowLen][colLen];
    }

    public Board(String stringRepr) {
        String[] rows = stringRepr.split("\n");
        this.rowLen = rows.length;
        this.colLen = rows[0].length();
        this.jellies = new Jelly[this.rowLen][this.colLen];
        for (int i = 0; i < this.rowLen; i++) {
            for (int j = 0; j < this.colLen; j++) {
                this.jellies[i][j] = char2Jelly(rows[i].charAt(j), i, j);
            }
        }
    }

    public String toString() {
        String stringRepr = "";
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (this.jellies[i][j] != null) {
                    stringRepr += this.jellies[i][j].charCode();
                } else {
                    stringRepr += ' ';
                }
            }
            stringRepr += "\n";
        }
        return stringRepr.substring(0, stringRepr.length() - 1);
    }

    public void initialize() {
        for (int i = 0; i < this.rowLen; i++) {
            for (int j = 0; j < this.colLen; j++) {
                this.jellies[i][j] = generateJelly(i, j);
            }
        }
    }

    public void erase(Jelly jelly) {
        if (jelly != null) {
            this.jellies[jelly.getRow()][jelly.getCol()] = null;
            jelly.trigger();
        }
    }

    public void erase(int upLeftRow, int upLeftCol, int downRightRow, int downRightCol) {
        for (int i = upLeftRow; i <= downRightRow; i++) {
            for (int j = upLeftCol; j <= downRightCol; j++) {
                this.erase(jellies[i][j]);
            }
        }
    }

    public void fall() {
        // 所有能爆炸的 jelly 都炸完之后才开始下落
        for (int i = this.rowLen - 1; i >= 0; i--) {
            for (int j = 0; j < this.rowLen; j++) {
                if (this.getJellies()[i][j] != null) {
                    this.getJellies()[i][j].fall();
                }
            }
        }
    }

    public void fill() {
        Boolean filled = false;
        while (!filled) {
            Boolean anyFell = false;
            for (int j = 0; j < this.colLen; j++) {
                if (this.jellies[0][j] == null) {
                    this.jellies[0][j] = generateJelly(0, j);
                    Boolean fell = this.jellies[0][j].fall();
                    if (fell) {
                        anyFell = true;
                    }
                }
            }
            if (!anyFell) {
                filled = true;
            }
        }
    }

    public Jelly generateJelly(int row, int col) {
        Random random = new Random();
        int code = random.nextInt(4);
        Jelly jelly;
        if (code == 0) jelly = new RowExplodableJelly();
        else if (code == 1) jelly = new ColExplodableJelly();
        else if (code == 2) jelly = new SquareExplodableJelly();
        else jelly = new VanillaJelly();
        jelly.initialize(this, row, col);
        return jelly;
    }

    public Jelly char2Jelly(char c, int row, int col) {
        Jelly jelly;
        if (c == 'H') jelly = new RowExplodableJelly();
        else if (c == 'V') jelly = new ColExplodableJelly();
        else if (c == 'S') jelly = new SquareExplodableJelly();
        else jelly = new VanillaJelly();
        jelly.initialize(this, row, col);
        return jelly;
    }

    public Jelly[][] getJellies() {
        return jellies;
    }

    public void setJellies(Jelly[][] jellies) {
        this.jellies = jellies;
    }

    public int getRowLen() {
        return rowLen;
    }

    public void setRowLen(int rowLen) {
        this.rowLen = rowLen;
    }

    public int getColLen() {
        return colLen;
    }

    public void setColLen(int colLen) {
        this.colLen = colLen;
    }
}
