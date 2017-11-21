package ru.gva.demo.synch;

/**
 * Данный класс содержит реализацию задачи "Совместное использование ресурсов"
 *
 *@author Gavrikov V. 15IT18.
 */

public class Synch extends Thread {
    public volatile static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1_000; j++) {
            add();
            System.out.println(i);
        }
    }

    /**
     * Данный метод увеличивает значение переменной I на 1.
     */
    public static synchronized void add(){
        i++;

    }

    public static void main(String[] args) {
        Synch synch = new Synch();
        Synch synch1 = new Synch();

        synch.start();
        synch1.start();
    }
}
