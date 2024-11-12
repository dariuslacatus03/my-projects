package concurrent;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {
    private Thread thread = null;
    private final BlockingQueue<Runnable> taskQueue;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> queue){
        taskQueue = queue;
    }

    public void run(){
        this.thread = Thread.currentThread();
        while(!isStopped()){
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}