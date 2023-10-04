package cuncurrnet;

import java.util.concurrent.Semaphore;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem=new Semaphore(1);

        new Thread(new IncThread(sem,"A")).start();
        new Thread(new DecThread(sem,"B")).start();
    }
}

// Общий ресурс
class Shared{
    static int count=0;
}

// Поток выполнения, который инкрементирует счетчик (переменную count )
class IncThread implements Runnable{
    String name;
    Semaphore sem;

    IncThread(Semaphore s,String n){
        sem=s;
        name=n;
    }

    @Override
    public void run() {
        System.out.println("Запуск "+name);
        try{
            // для начала получить разрешение
            System.out.println(name+" ожидает разрешения");
            sem.acquire();
            System.out.println(name+" получил разрешение");
            // Теперь получить доступ к общему ресурсу
            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name+": "+Shared.count);
                // Разрешить переключение контекста по возможности
                Thread.sleep(10);
            }
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
        // Освободить разрешение
        System.out.println(name+" освободил разрешение");
        sem.release();
    }
}

// Поток выполнения, который декрементирует счетчик (переменную count )
class DecThread implements Runnable{
    String name;
    Semaphore sem;

    DecThread(Semaphore s,String n){
        sem=s;
        name=n;
    }

    @Override
    public void run() {
        System.out.println("Запуск "+name);
        try {
            // Для начала получить разрешение
            System.out.println(name+" ожидает разрешения");
            sem.acquire();
            System.out.println(name+" получил разрешение");
            // Теперь получить доступ к общему ресурсу
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name+": "+Shared.count);
                // Разрешить переключение контекста по возможности
                Thread.sleep(10);
            }
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
        // Освободить разрешение
        System.out.println(name+" освободил разрешение");
        sem.release();
    }
}


