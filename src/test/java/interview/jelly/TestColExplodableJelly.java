package interview.jelly;

import interview.jelly.model.Board;
import org.junit.Test;

/**
 * Created by bladeralien on 2017/2/12.
 */
public class TestColExplodableJelly {
    @Test
    public void testColExplodableJellyTrigger() {
        Board board = new Board("VBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB\nBBBBBBBB");
        board.erase(board.getJellies()[0][0]);
        assert board.toString().equals(" BBBBBBB\n BBBBBBB\n BBBBBBB\n BBBBBBB\n BBBBBBB\n BBBBBBB\n BBBBBBB\n BBBBBBB");
    }
}
