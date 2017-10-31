package ru.gva.demo.helloThread;

/**
 * Создание потока Thread
 *
 * Класс Thread сам реализует интерфес Runnable
 * хотя его метол run() ничего не делает,
 * Подкласс класса Thread может
 * обеспечить собственную реализацию метода run().
 */

public class HelloThread extends Thread {

    public void run() {
        try {

            while (true) {
                System.out.println("hello from a thread");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }

    public static void main(String[] args) {
        (new HelloThread()).start();
        try {
            while (true) {
                System.out.println("hello from main thread");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
