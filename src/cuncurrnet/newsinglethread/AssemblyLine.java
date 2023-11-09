package cuncurrnet.newsinglethread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class AssemblyLine {
    private static final int MAX_PROD_TIME_MS=5*1000;
    private static final int MAX_CONS_TIME_MS=7*1000;
    private static final int TIMEOUT_MS=MAX_CONS_TIME_MS+1000;
    private static final Random rnd=new Random();
    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;
    private static ExecutorService produceService;
    private static ExecutorService consumerService;
    private static final TransferQueue<String>queue=new LinkedTransferQueue<>();
    private static Logger logger=Logger.getLogger("Name");
    private static Producer producer;
    private static Consumer consumer;

    private static class Producer implements Runnable{
        @Override
        public void run() {
            while (runningProducer){
                try {
                    String bulb="bulb-"+rnd.nextInt(1000);
                    Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));

                    boolean transfered=queue.tryTransfer(bulb,TIMEOUT_MS, TimeUnit.MILLISECONDS);

                    if (transfered){
                        logger.info(()->"Проверена: "+bulb);
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"Исключение: "+e);
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        @Override
        public void run() {
            while (runningConsumer){
                try {
                    String bulb=queue.poll(MAX_PROD_TIME_MS,TimeUnit.MILLISECONDS);

                    if (bulb!=null){
                        Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                        logger.info(()->"Упакована: "+bulb);
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"Исключение: "+e);
                    break;
                }
            }
        }
    }

    public static void startAssemblyLine(){
        if (runningProducer || runningConsumer){
            logger.info("Сборочная линия уже работает...");
            return;
        }
        logger.info("\n\nЗапуск сборочной линии...");
        logger.info(()->"Лампочки, оставшиеся после предыдущего прогона: \n"
        +queue+"\n\n");

        runningProducer=true;
        produceService= Executors.newSingleThreadExecutor();
        produceService.execute(producer);
        runningConsumer=true;
        consumerService=Executors.newSingleThreadExecutor();
        consumerService.execute(consumer);
    }

    public static void stopAssemblyLine(){
        logger.info("Остановка сборочной линии...");

        boolean isProducerDown=shutdownProducer();
        boolean isConsumerDown=shutdownConsumer();

        if (!isProducerDown || !isConsumerDown){
            logger.severe("Произошло что-то ненормальное во время выключения сборочной линии!");
            System.exit(0);
        }
    }

    private static boolean shutdownProducer(){
        runningProducer=false;
        return shutdownExecutor(produceService);
    }

    private static boolean shutdownConsumer(){
        runningConsumer=false;
        return shutdownExecutor(consumerService);
    }

    private static boolean shutdownExecutor(ExecutorService executor){
        executor.shutdown();

        try {
            if (!executor.awaitTermination(TIMEOUT_MS*2,TimeUnit.MILLISECONDS)){
                executor.shutdownNow();
                return executor.awaitTermination(TIMEOUT_MS*2,TimeUnit.MILLISECONDS);
            }
            return true;
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logger.severe(()->"Исключение: "+e);
        }
        return false;
    }

    public static void main(String[] args) {
        AssemblyLine.startAssemblyLine();
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AssemblyLine.stopAssemblyLine();
    }
}
