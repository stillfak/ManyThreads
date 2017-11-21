package ru.gva.demo.runexample;



public class RandomRunExample extends Thread{


    public void run() {
        System.out.println(getName());
    }

    public static void example() {
        for (int i = 0; i <10 ; i++) {
            Thread thread = new RandomRunExample();
            thread.start();
        }

    }
}
