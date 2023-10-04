package cuncurrnet;

import java.util.concurrent.Exchanger;

public class ExgrDemo {

    public static void main(String[] args) {
        Exchanger<String>exgr=new Exchanger<>();

        new Thread(new UseString(exgr)).start();
        new Thread(new MakeString(exgr)).start();
    }
}

// Поток, который конструирует строку
class MakeString implements Runnable{
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> c){
        ex=c;
        str=new String();
    }

    public void run(){
        char ch='A';
        for (int i = 0; i < 3; i++) {
            // Заполнить буфер
            for (int j = 0; j < 5; j++) {
                str += ch++;
            }
                try {
                    // Обменять полный буфер на пустой
                    str=ex.exchange(str);
                }catch (InterruptedException ex){
                    System.out.println(ex);
            }
        }
    }
}

// Поток, который использует строку
class UseString implements Runnable{
    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> c){
        ex=c;
    }

    public void run(){
        for (int i = 0; i < 3; i++) {
            try {
                // Обменять пустой буфер на полный
                str=ex.exchange(new String());
                System.out.println("Получено: "+str);
            }catch (InterruptedException ex){
                System.out.println(ex);
            }
        }
    }
}
