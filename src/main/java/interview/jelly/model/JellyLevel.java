package interview.jelly.model;

import javax.persistence.*;

/**
 * Created by bladeralien on 2017/2/12.
 */
@Entity
@Table( name = "jelly_level" )
public class JellyLevel {

    @Id
    private int level;
    private String board;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
