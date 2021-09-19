package su.scmb.lvl3lesson5;

public interface IRace {
    default void announceAboutStart() {
        System.out.println("Attention! We starting new Race!");
    }
}
