package cuncurrnet;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phsr=new Phaser(1);
        int curPhase;

        System.out.println("Начало");

        new Thread(new MyThread3(phsr,"A")).start();
        new Thread(new MyThread3(phsr,"B")).start();
        new Thread(new MyThread3(phsr,"C")).start();

        // Ожидать завершения всеми потоками первой стадии
        curPhase=phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Стадия "+curPhase+" завершена");
        //  Ожидать завершения всеми потоками второй стадии
        curPhase=phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Стадия "+curPhase+" завершена");

        curPhase=phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Стадия "+curPhase+" завершена");

        // Отменить регистрацию главного потока
        phsr.arriveAndDeregister();

        if (phsr.isTerminated()){
            System.out.println("Объект Phaser закончил работу");
        }
    }
}

// Поток выполнения, который использует объект Phaser
class MyThread3 implements Runnable{
    Phaser pshr;
    String name;

    MyThread3(Phaser p,String n){
        pshr=p;
        name=n;
        pshr.register();
    }

    public void run(){
        System.out.println("Поток "+name+" начал первую стадию");
        pshr.arriveAndAwaitAdvance(); // Сигнализировать о прибытии
        // Организовать небольшую паузу, чтобы предотвратить беспорядочный вывод.
        // Это делается только в целях иллюстрации и не требуется
        // для корректной работы объекта Phaser
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Поток "+name+" начал вторую стадию");
        pshr.arriveAndAwaitAdvance(); // Сигнализировать о прибытии
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Поток "+name+" начал третью стадию");
        pshr.arriveAndDeregister(); // Сигнализировать о прибытии и отметить регистрацию
    }
}
