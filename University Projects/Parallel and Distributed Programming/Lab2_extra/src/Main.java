import concurrent.ThreadPool;

public class Main {

    public static void main(String[] args) throws Exception {

        ThreadPool threadPool = new ThreadPool(3, 5);

        for (int i = 1; i <= 10; i++) {
            int taskNo = i;
            threadPool.execute( () -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println(message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();
    }
}