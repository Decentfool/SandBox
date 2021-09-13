package su.scmb.lvl3lesson4.pe;

import su.scmb.lvl3lesson4.App;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ParallelExecutor {
    private static final Logger LOGGER = Logger.getLogger(ParallelExecutor.class.getName());
    private final HashMap<Integer, Object> monitors = new HashMap<>();
    private ExecutorService threadPool;

    public void initialParallelDegree(int count) {
        threadPool = Executors.newFixedThreadPool(count);
    }

    public void startThread(Runnable command) {
        threadPool.execute(command);
    }

    public void stopAllThread() {
        threadPool.shutdown();
        //monitors.clear();
    }

    public Object acquireMonitor(int numberOfMonitors) {
        if (monitors.get(numberOfMonitors) != null) {
            //LOGGER.info("Monitor for thread " + Thread.currentThread().getName() + " was found");
            return monitors.get(numberOfMonitors);
        } else {
            monitors.putIfAbsent(numberOfMonitors, new Object());
            LOGGER.info("Monitor with index " + numberOfMonitors + " for thread " + Thread.currentThread().getName() + " not found, creating new monitor");
            return monitors.get(numberOfMonitors);
        }
    }
}
