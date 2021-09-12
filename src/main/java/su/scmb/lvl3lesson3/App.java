package su.scmb.lvl3lesson3;

import su.scmb.lvl3lesson3.utils.PrepareData;

import java.util.ArrayList;
import java.util.List;

public class App {
    final static String path = "D:/MullinAA/";
    final static String fileName = "1";
    final static String extension = ".txt";
    public static void main(String[] args) {
        /* 1 */
        PrepareData.deleteFile(path, fileName + extension);
        PrepareData.createFile(path, fileName + extension);
        PrepareData.addDataInFile(path, fileName + extension, PrepareData.randomData(50), true);
        byte[] bytes = PrepareData.getDataInFile(path, fileName + extension);
        for (byte b : bytes){
            System.out.print((char) b + " ");
        }
        System.out.println(" ");
        /* 2 */
        List<String> pathsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PrepareData.deleteFile(path, fileName + "_" + i + extension);
            PrepareData.createFile(path, fileName + "_" + i + extension);
            PrepareData.addDataInFile(path, fileName + "_" + i + extension, PrepareData.randomData(10000000), true);
            pathsList.add(path + fileName + "_" + i + extension);
        }
        PrepareData.manyFileToOne(pathsList, path + "out" + extension);
        /* 3 */
        long startTime = System.currentTimeMillis();
        System.out.println("Start time: " + startTime);
        PrepareData.deleteFile(path, fileName + "_3_task" + extension);
        PrepareData.createFile(path, fileName + "_3_task" + extension);
        /* file size ~100mb*/
        PrepareData.addDataInFile(path, fileName + "_3_task" + extension, PrepareData.randomData(10000000), true);
        PrepareData.pagingData(path + fileName + "_3_task" + extension);
        long finishTime = System.currentTimeMillis();
        System.out.println("Finish time: " + finishTime);
        System.out.println("Result time: " + (float)(finishTime - startTime)/ 1000 + " sec");
    }
}
