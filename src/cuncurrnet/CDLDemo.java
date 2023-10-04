package cuncurrnet;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {
    public static void main(String[] args) {
        CountDownLatch cdl=new CountDownLatch(5);
        System.out.println("Начало");
        new Thread(new MyThread(cdl)).start();
        try {
            cdl.await();
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
        System.out.println("Конец");
    }
}

class MyThread implements Runnable{
    CountDownLatch latch;

    MyThread(CountDownLatch c){
        latch=c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            latch.countDown();
        }
    }
}
