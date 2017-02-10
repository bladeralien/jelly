package interview.jelly.model;

/**
 * Created by bladeralien on 2017/2/8.
 */
public class RowExplodableJelly extends ExplodableJelly {
    public void trigger() {
        for (Jelly jelly : this.getBoard().getJellies()[this.getRow()]) {
            if (jelly != null) {
                this.getBoard().erase(jelly);
            }
        }
    }

    public char charCode() {
        return 'H';
    }
}
