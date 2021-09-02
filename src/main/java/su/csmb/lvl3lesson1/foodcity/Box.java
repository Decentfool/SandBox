package su.csmb.lvl3lesson1.foodcity;


import java.util.ArrayList;
import java.util.logging.Logger;

public class Box<T extends Fruit, V> {
    private static final Logger logger = Logger.getLogger(Box.class.getName());
    private T fruitType;
    private int countInBox;
    private final ArrayList<String> warehouse = new ArrayList<>(countInBox);

    public Box(T fruitType, int countInBox) {
        this.fruitType = fruitType;
        this.countInBox = countInBox;
    }
    public T getObjectType() {
        return fruitType;
    }
    public void loadWarehouse() {
        if (warehouse.size() == countInBox) {
            warehouse.clear();
            logger.warning("Warehouse is full, cleaning...");
        }
        for (int i = 0; i < countInBox; i++) {
            warehouse.add(fruitType.toString());
        }
    }
    public void addInWarehouse() {
        if (warehouse.size() == countInBox) {
            logger.warning("Warehouse is full");
        } else {
            warehouse.add(fruitType.toString());
            logger.info("Object added to warehouse");
        }
    }
    public void removeFromWarehouseByIndex(int indx) {
        try {
            warehouse.remove(indx);
            logger.info("Graduate, Object was removed");
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Opps... this object is absent");
        }
    }
    public ArrayList<String> getWarehouse() {
        return warehouse;
    }
    public void moveBoxToAnother(Box<T, V> newBox) {
        warehouse.forEach(x -> newBox.addInWarehouse());
        warehouse.clear();
        logger.info("Box was moved");
    }

    public float getWeight() {
        return fruitType.getWeight() * warehouse.size();
    }
    public Boolean compare(Box<?, V> inBox) {
        return fruitType.getWeight() == inBox.getWeight();
    }

}
