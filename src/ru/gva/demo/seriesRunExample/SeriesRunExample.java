package ru.gva.demo.seriesRunExample;

public class SeriesRunExample extends Thread {
    private static int currentMax = 1;
    private int mainId;
    private final Object waitObject;

    public SeriesRunExample(int mainId, Object waitObject) {
        this.mainId = mainId;
        this.waitObject = waitObject;
    }

    public static void example(){
        Object waitObject = new Object();
        for (int i = currentMax; i < 10; ++i) {
            Thread thread = new SeriesRunExample(i, waitObject);
            thread.start();
        }
    }

    public void run() {
        System.out.println("Стартовал поток " + mainId);
        //запускаются в беспорядке
        synchronized (waitObject){
            while (mainId > currentMax){
                try {
                    waitObject.wait();
                    System.out.println("После wait'a " + mainId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentMax++; //сюда проходят по порядку
            System.out.println("Отработал поток " + mainId);
            waitObject.notifyAll();
        }
    }

    public static void main(String[] args) {
        example();
    }
}
