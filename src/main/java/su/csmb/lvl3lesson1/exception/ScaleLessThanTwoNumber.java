package su.csmb.lvl3lesson1.exception;

public class ScaleLessThanTwoNumber extends Exception {
    public ScaleLessThanTwoNumber() {
        super("Scale has few elements (need more than 1)");
    }
}
