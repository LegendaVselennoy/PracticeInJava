package cuncurrnet;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadPoolExecutor implements Runnable{
    private final int taskId;

    public SimpleThreadPoolExecutor(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Исполнение операции "+taskId
        +" посредством "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue=new LinkedBlockingQueue<>(5);
        final AtomicInteger counter=new AtomicInteger();

        ThreadFactory threadFactory=(Runnable r)->{
            System.out.println("Создание новой нити Cool-Thread-"+counter.incrementAndGet());
            return new Thread(r,"Cool-Thread-"+counter.get());
        };

        RejectedExecutionHandler rejectedHandler=
                (Runnable r, ThreadPoolExecutor executor)->{
            if (r instanceof SimpleThreadPoolExecutor){
                SimpleThreadPoolExecutor task=(SimpleThreadPoolExecutor) r;
                System.out.println("Отклонение операции "+task.taskId);
            }
                };

        ThreadPoolExecutor executor=new ThreadPoolExecutor(10,20,1,
                TimeUnit.SECONDS,queue,threadFactory,rejectedHandler);

        for (int i = 0; i < 50; i++) {
            executor.execute(new SimpleThreadPoolExecutor(i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(
                    Integer.MAX_VALUE,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
