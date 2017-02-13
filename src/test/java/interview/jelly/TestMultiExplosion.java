package interview.jelly;

import interview.jelly.model.Board;
import org.junit.Test;

/**
 * Created by bladeralien on 2017/2/12.
 */
public class TestMultiExplosion {
    @Test
    public void testMultiExplosion() {
        Board board = new Board("BBBBBBBB\nBBVBBBBB\nBBBSBBBB\nBBBBHBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
        board.erase(board.getJellies()[2][3]);
        assert board.toString().equals("BB BBBBB\nBB   BBB\nBB   BBB\n        \nBB BBBBB\nBB BBBBB\nBB BBBBB\nBB BBBBB");
    }
}
