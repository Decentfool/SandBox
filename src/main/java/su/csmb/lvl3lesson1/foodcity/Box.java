package su.csmb.lvl3lesson1.foodcity;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit, V> {
    private ArrayList<T> warehouse;
    private Float weight;

    public Box() {
    }

    public void createWarehouse(T[] rawMaterial) {
        this.warehouse = new ArrayList(Arrays.asList(rawMaterial));

    }

    public Float getWeight(Float weight) {
        this.weight = warehouse.size() * weight;
        return this.weight;
    }



    public Boolean compare(Float anotherWeight) {
        if (anotherWeight == this.weight) {
            return true;
        }
        return false;
    }
}
