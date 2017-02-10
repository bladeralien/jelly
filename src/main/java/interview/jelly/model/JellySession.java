package interview.jelly.model;

import javax.persistence.*;

/**
 * Created by bladeralien on 2017/2/9.
 */
@Entity
@Table( name = "jelly_session" )
public class JellySession {

    @Id
    private String id;
    private String board;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
