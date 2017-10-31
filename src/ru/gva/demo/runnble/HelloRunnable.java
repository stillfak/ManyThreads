package ru.gva.demo.runnble;

/**
 * Реализует интерфейс Runnable
 *
 * Интрефейс Runnable определяет один метод run,
 * предназначен для размещение кода, дополнительного потока,
 * Runnable-объект передается в конструктор Thread
 * и спомощью метода start() поток запускается.
 */

public class HelloRunnable implements Runnable {
    public void run(){
        try {


            while (true) {
                System.out.println("поток 2");
                Thread.sleep(1500);
            }
        }catch (InterruptedException e){
            System.out.println("поток завершился");
        }
    }

    public static void main(String[] args) {
        (new Thread(new HelloRunnable())).start();
        try {


            while (true) {
                System.out.println("поток 1");
                Thread.sleep(2700);
            }
        }catch (InterruptedException e){
            System.out.println("поток завершился");
        }
    }

}