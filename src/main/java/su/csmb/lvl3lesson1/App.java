package su.csmb.lvl3lesson1;

import su.csmb.lvl3lesson1.foodcity.Apple;
import su.csmb.lvl3lesson1.foodcity.Box;
import su.csmb.lvl3lesson1.foodcity.Orange;
import su.csmb.lvl3lesson1.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class App {
    static final Logger logger = Logger.getLogger(App.class.getName());
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
        Box boxApple = new Box();
        boxApple.createWarehouse(new Apple());
        //Box boxOrange = new Box(new Orange());

    }
}
