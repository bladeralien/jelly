package interview.jelly;

import interview.jelly.model.Board;
import org.junit.Test;

/**
 * Created by bladeralien on 2017/2/12.
 */
public class TestSquareExplodableJelly {
    @Test
    public void testSquareExplodableJellyTriggerCorner() {
        Board board = new Board("SBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
        board.erase(board.getJellies()[0][0]);
        assert board.toString().equals("  BBBBBB\n  BBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
    }

    @Test
    public void testSquareExplodableJellyTriggerCenter() {
        Board board = new Board("BBBBBBBB\nBBBBBBBB\nBBBSBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
        board.erase(board.getJellies()[2][3]);
        assert board.toString().equals("BBBBBBBB\nBB   BBB\nBB   BBB\nBB   BBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
    }
}
