package interview.jelly.model;

/**
 * Created by bladeralien on 2017/2/8.
 */
public class SquareExplodableJelly extends ExplodableJelly {
    public void trigger() {
        for (int i = Math.max(0, this.getRow() - 1); i < Math.min(this.getBoard().getRowLen(), this.getRow() + 1) ; i++) {
            for (int j = Math.max(0, this.getCol() - 1); j < Math.min(this.getBoard().getColLen(), this.getCol() + 1); j++) {
                if (!(i == this.getRow() && j == this.getCol())) {
                    Jelly jelly = this.getBoard().getJellies()[i][j];
                    if (jelly != null) {
                        this.getBoard().erase(jelly);
                    }
                }
            }
        }
    }

    public char charCode() {
        return 'S';
    }
}
