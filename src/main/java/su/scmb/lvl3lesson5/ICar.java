package su.scmb.lvl3lesson5;

public interface ICar {
    default void prepareCar(String name) {
        System.out.println(name + " prepare car");
    }
}
