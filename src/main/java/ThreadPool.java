
import jdk.internal.util.xml.impl.ReaderUTF16;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    // 任务队列大小
    private final int nThreads;
    // 线程池
    private final PoolWorker[] threads;
    // 任务池
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            new Thread(threads[i]).start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task); // 向队列中添加任务
            queue.notify();  // 唤醒一个线程
        }
    }

    private class PoolWorker implements Runnable {

        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait(); // 线程没有任务，进入睡眠
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    // 线程被唤醒之后，会顺利执行到这里
                    task = queue.poll(); // 获取任务
                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    task.run(); // 执行任务
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }
}