package su.csmb.lvl3lesson1.utils;

import su.csmb.lvl3lesson1.exception.ScaleLessThanTwoNumber;

import java.util.*;
import java.util.logging.Logger;

public class Array<T, V extends Collection> {
    private static final Logger logger = Logger.getLogger(Array.class.getName());

    public V swapRandomTwoElement(V inCollection) {
        int[] indx = getTwoRandomNumber(inCollection.size());
        if (indx != null) {
            Object firstValue;
            Object secondValue;
            if (inCollection instanceof List) {
                List collectionToList = (List) inCollection;
                firstValue = collectionToList.get(indx[0]);
                secondValue = collectionToList.get(indx[1]);
                logger.info("[List] first element: " + firstValue);
                logger.info("[List] second element: " + secondValue);
                collectionToList.set(indx[0], secondValue);
                collectionToList.set(indx[1], firstValue);
                return (V) collectionToList;
            }
            if (inCollection instanceof Map) {
                Map collectionToMap = (Map) inCollection;
                Object firstKey = collectionToMap.keySet().toArray()[indx[0]];
                Object secondKey = collectionToMap.keySet().toArray()[indx[1]];
                firstValue = collectionToMap.get(firstKey);
                secondValue = collectionToMap.get(secondKey);
                collectionToMap.put(firstKey, secondValue);
                collectionToMap.put(secondKey, firstValue);
                return (V) collectionToMap;
            }
        }
        return null;
    }

    public ArrayList<T> arrayToCollection(T[] inwardArray) {
        ArrayList<T> tArrayList = new ArrayList<>(Arrays.asList(inwardArray));
        return tArrayList;

    }

    private static int[] getTwoRandomNumber(int scale) {
        try {
            checkScale(scale);
            final Random random = new Random();
            int firstNumber = random.nextInt(scale);
            int secondNumber = random.nextInt(scale);
            while (firstNumber == secondNumber) {
                secondNumber = random.nextInt(scale);
            }
            int[] result = {firstNumber, secondNumber};
            return result;
        } catch (ScaleLessThanTwoNumber scaleLessThanTwoNumber) {
            scaleLessThanTwoNumber.printStackTrace();
            return null;
        }
    }

    private static void checkScale(int scale) throws ScaleLessThanTwoNumber {
        if (scale < 2) {
            throw new ScaleLessThanTwoNumber();
        }
    }
}
