package cuncurrnet;

// Простой пример применения базовой стратегии "разделяй и властвуй"

// Задача For kJoinTask, которая (через RecursiveAction) трансформирует
// элементы массива значений double в их квадратные корни

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SqrtTransform extends RecursiveAction {
    // Произвольно установить пороговое значение в этом примере в 1000
    // В реальном коде оптимальное пороговое значение может быть
    // выяснено за счет профилирования и экспериментирования
    final int seqThreshold=1000;
    // Массив, в который будет осуществляться доступ
    double[] data;
    // Определить, какую часть данных обрабатывать
    int start,end;

    SqrtTransform(double[] vals,int s,int e){
        data=vals;
        start=s;
        end=e;
    }

    // Метод, в котором будут происходить параллельные вычисления
    protected void compute(){
        // Если количество элементов меньше порогового значения
         // тогда обрабатывать последовательно
        if ((end-start)<seqThreshold){
            // Трансформировать каждый элемент в его квадратный корень
            for (int i = start; i < end; i++) {
                data[i]=Math.sqrt(data[i]);
            }
        }else {
            // В противном случае продолжить разделение данных на меньшие части.
            // Найти среднюю точку
            int middle=(start+end)/2;
            // Запустить новые задачи, используя дополнительно разделенные на части данные
            invokeAll(new SqrtTransform(data,start,middle),
                      new SqrtTransform(data,middle,end));
        }
    }
}

// Демонстрация параллельного выполнения

class ForkJoinDemo{
    public static void main(String[] args) {
        // Создать пул задач
        ForkJoinPool fjp=new ForkJoinPool();
        double[] nums=new double[100000];
        // Присвоить nums ряд значений
        for (int i = 0; i < nums.length; i++) {
            nums[i]=(double) i;
        }
        System.out.println("Часть исходной последовательности: ");

        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println("\n");
        SqrtTransform task=new SqrtTransform(nums,0,nums.length);
        // Запустить главную задачу ForkJoinTask
        fjp.invoke(task);
        System.out.println("Чacть трансформированной последовательности"+
                           "(с четырьмя знаками после десятичной точки): ");
        for (int i = 0; i < 10; i++) {
            System.out.format("%.4f ", nums[i]);
        }
        System.out.println();
    }
}
