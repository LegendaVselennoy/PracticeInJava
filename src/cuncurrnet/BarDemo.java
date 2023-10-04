package cuncurrnet;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb=new CyclicBarrier(3,new BarAction());
        System.out.println("Начало");
        new Thread(new MyThread2(cb,"A")).start();
        new Thread(new MyThread2(cb,"B")).start();
        new Thread(new MyThread2(cb,"C")).start();
    }
}

// Поток выполнения, в котором применяется CyclicBarrier
class MyThread2 implements Runnable{
    CyclicBarrier cbar;
    String name;

    MyThread2(CyclicBarrier c,String n){
        cbar=c;
        name=n;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            cbar.await();
        }catch (BrokenBarrierException ex){
            System.out.println(ex);
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}

// Объект этого класса вызывается, когда заканчивается CyclicBarrier
class BarAction implements Runnable{
    @Override
    public void run() {
        System.out.println("Барьер достигнут!");
    }
}
