package interview.jelly;

import interview.jelly.model.Board;
import org.junit.Test;

/**
 * Created by bladeralien on 2017/2/12.
 */
public class TestRowExplodableJelly {
    @Test
    public void testRowExplodableJellyTrigger() {
        Board board = new Board("HBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
        board.erase(board.getJellies()[0][0]);
        assert board.toString().equals("        \nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
    }
}
