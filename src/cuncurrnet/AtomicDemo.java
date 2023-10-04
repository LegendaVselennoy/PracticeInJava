package cuncurrnet;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
     new Thread(new AtomThread("A")).start();
     new Thread(new AtomThread("B")).start();
     new Thread(new AtomThread("C")).start();
    }
}

class Shared2{
    static AtomicInteger ai=new AtomicInteger(0);
}

class AtomThread implements Runnable{
    String name;

    AtomThread(String n){
        name=n;
    }

    @Override
    public void run() {
        System.out.println("Начало "+name);
        for (int i = 1; i <=3 ; i++) {
            System.out.println(name+" получено: "+Shared2.ai.getAndSet(i));
        }
    }
}

