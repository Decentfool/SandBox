package su.scmb.lvl3lesson4.pe;

import su.scmb.lvl3lesson4.App;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ParallelExecutor {
    private static final Logger LOGGER = Logger.getLogger(ParallelExecutor.class.getName());
    private final Map<Integer, Object> monitors = new ConcurrentHashMap<>();
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
            return monitors.get(numberOfMonitors);
        } else {
            monitors.putIfAbsent(numberOfMonitors, new Object());
            return monitors.get(numberOfMonitors);
        }
    }
}
