package su.csmb.lvl3lesson1;

import su.csmb.lvl3lesson1.foodcity.Apple;
import su.csmb.lvl3lesson1.foodcity.Box;
import su.csmb.lvl3lesson1.foodcity.Orange;
import su.csmb.lvl3lesson1.utils.Array;

import java.util.*;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String[] args) {
        Array arrayTools = new Array();
        /*-----------------------------------------*/
        Float[] legacyArrayF = {10.4f, 20.5f, 30.2f, 40.7f, 50.4f, 60.5f, 70.4f, 80.7f, 90.1f, 100.5f};
        logger.info("Array has start elements: [" + String.join("][", Arrays.toString(legacyArrayF) + "]"));
        Collection list = arrayTools.swapRandomTwoElement(arrayTools.arrayToCollection(legacyArrayF));
        logger.info("Array has finish elements: [" + String.join("][", Arrays.toString(list.toArray())) + "]");
        /*-----------------------------------------*/
        String[] legacyArrayS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        logger.info("Array has start elements: [" + String.join("][", Arrays.toString(legacyArrayS) + "]"));
        list = arrayTools.swapRandomTwoElement(arrayTools.arrayToCollection(legacyArrayS));
        logger.info("Array has finish elements: [" + String.join("][", Arrays.toString(list.toArray())) + "]");
        /*-----------------------------------------*/
        Integer[] legacyArrayI = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        logger.info("Array has start elements: [" + String.join("][", Arrays.toString(legacyArrayI) + "]"));
        list = arrayTools.swapRandomTwoElement(arrayTools.arrayToCollection(legacyArrayI));
        logger.info("Array has finish elements: [" + String.join("][", Arrays.toString(list.toArray())) + "]");
        //----------------------------------------------------------------------------------
        Box boxApple = new Box(new Apple(1.0f),3);
        boxApple.loadWarehouse();
        logger.info("Weight " + boxApple.getObjectType() + " box: " + boxApple.getWeight());
        Box boxOrange = new Box(new Orange(1.5f), 5);
        boxOrange.loadWarehouse();
        logger.info("Weight " + boxOrange.getObjectType() + " box: " + boxOrange.getWeight());
        logger.info("Weight equals? " + boxApple.compare(boxOrange));
        boxApple.removeFromWarehouseByIndex(100);
        boxApple.addInWarehouse();
        boxApple.removeFromWarehouseByIndex(1);
        boxApple.addInWarehouse();
        Box newBoxApple = new Box(new Apple(1.0f),3);
        logger.info("Count in old Box: " + boxApple.getWarehouse().size());
        logger.info("Count in new Box: " + newBoxApple.getWarehouse().size());
        boxApple.moveBoxToAnother(newBoxApple);
        logger.info("Count in old Box: " + boxApple.getWarehouse().size());
        logger.info("Count in new Box: " + newBoxApple.getWarehouse().size());

    }
}
