package interview.jelly.model;

/**
 * Created by bladeralien on 2017/2/8.
 */
public class ColExplodableJelly extends ExplodableJelly {
    public void trigger() {
        for (int i = 0; i < this.getBoard().getRowLen(); i++) {
            Jelly jelly = this.getBoard().getJellies()[i][this.getCol()];
            if (jelly != null) {
                this.getBoard().erase(jelly);
            }
        }
    }

    public char charCode() {
        return 'V';
    }
}
