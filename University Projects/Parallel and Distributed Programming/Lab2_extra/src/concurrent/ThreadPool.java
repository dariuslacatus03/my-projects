package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue = null;
    private final List<PoolThreadRunnable> runnableList = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);
        for (int i = 1; i <= noOfThreads; i++) {
            PoolThreadRunnable task = new PoolThreadRunnable(taskQueue);
            runnableList.add(task);
        }
        for (PoolThreadRunnable task : runnableList) {
            new Thread(task).start();
        }
    }

    public synchronized void execute(Runnable task) throws InterruptedException {
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        this.taskQueue.put(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for (PoolThreadRunnable task : runnableList) {
            task.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (!this.taskQueue.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}