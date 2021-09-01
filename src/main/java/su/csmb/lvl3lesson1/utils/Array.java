package su.csmb.lvl3lesson1.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class Array<T, V extends Collection> {
    static final Logger logger = Logger.getLogger(Array.class.getName());

    public V swapRandomTwoElement(V list) {
        //TODO will do refactoring to Stream api
        int firstElement = 0;
        int secondElement = 0;
        if (!list.isEmpty()) {
            final Random random = new Random();
            if (list instanceof List) {
                List listList = (List) list;
                while (firstElement == secondElement) {
                    firstElement = random.nextInt(list.size());
                    secondElement = random.nextInt(list.size());
                }
                Object firstElementVar = listList.get(firstElement);
                Object secondElementVar = listList.get(secondElement);
                logger.info("[List] first element: " + firstElementVar);
                logger.info("[List] second element: " + secondElementVar);
                listList.set(firstElement, secondElementVar);
                listList.set(secondElement, firstElementVar);
                return (V) listList;
            }
            if (list instanceof Map) {
                Map mapMap = (Map) list;

                final AtomicInteger currentIndx = new AtomicInteger();
                while (firstElement == secondElement) {
                    firstElement = random.nextInt(list.size());
                    secondElement = random.nextInt(list.size());
                }
                int finalFirstElement = firstElement;
                int finalSecondElement = secondElement;
                AtomicReference<Object> putFirstKey = new AtomicReference<>();
                AtomicReference<Object> putSecondKey = new AtomicReference<>();
                mapMap.forEach((x, y) -> {
                    if (currentIndx.get() == finalFirstElement) {
                        putFirstKey.set(mapMap.get(x));
                    }
                    if (currentIndx.get() == finalSecondElement) {
                        putSecondKey.set(mapMap.get(x));
                    }
                    currentIndx.getAndIncrement();
                });
                mapMap.put(putFirstKey, mapMap.get(putSecondKey));
                mapMap.put(putSecondKey, mapMap.get(putFirstKey));
                return (V) mapMap;
            }
        }
        return null;
    }
    public ArrayList<T> arrayToCollection(T[] inwardArray) {
        ArrayList<T> tArrayList = new ArrayList<>(Arrays.asList(inwardArray));
        return tArrayList;

    }
}
