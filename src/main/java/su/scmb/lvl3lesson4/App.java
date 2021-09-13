package su.scmb.lvl3lesson4;

import su.scmb.lvl3lesson4.pe.ParallelExecutor;

import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private volatile char currentLetter = 'A';
    private final ParallelExecutor parallelExecutor = new ParallelExecutor();

    public static void main(String[] args) {
        App app = new App();
        app.start();

    }

    public void start() {
        parallelExecutor.initialParallelDegree(4);
        parallelExecutor.startThread(() -> { printA(0);});
        parallelExecutor.startThread(() -> { printB(0);});
        parallelExecutor.startThread(() -> { printC(1);});
        parallelExecutor.startThread(() -> { printD(1);});
        parallelExecutor.stopAllThread();

    }

    public void printA(int indexMonitor) {
        Object monitor = parallelExecutor.acquireMonitor(indexMonitor);
        synchronized (monitor) {
            try {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void printB(int indexMonitor) {
        Object monitor = parallelExecutor.acquireMonitor(indexMonitor);
        synchronized (monitor) {
            try {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void printC(int indexMonitor) {
        Object monitor = parallelExecutor.acquireMonitor(indexMonitor);
        synchronized (monitor) {
            try {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'D';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void printD(int indexMonitor) {
        Object monitor = parallelExecutor.acquireMonitor(indexMonitor);
        synchronized (monitor) {
            try {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'D') {
                        monitor.wait();
                    }
                    System.out.print("D");
                    currentLetter = 'A';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

