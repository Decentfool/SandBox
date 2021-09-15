package su.scmb.lvl3lesson4;

import su.scmb.lvl3lesson4.pe.ParallelExecutor;

import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        ParallelExecutor parallelExecutor = new ParallelExecutor();
        Printable printable = new Printable();
        printable.setCurrentLetter('A');
        parallelExecutor.initialParallelDegree(4);
        parallelExecutor.startThread(() -> {
            printable.printLetter('A', 'B', 10);

        });
        parallelExecutor.startThread(() -> {
            printable.printLetter('B', 'C', 10);

        });
        parallelExecutor.startThread(() -> {
            printable.printLetter('C', 'D', 10);
        });
        parallelExecutor.startThread(() -> {
            printable.printLetter('D', 'A', 10);
        });
        parallelExecutor.stopAllThread();
    }
}

