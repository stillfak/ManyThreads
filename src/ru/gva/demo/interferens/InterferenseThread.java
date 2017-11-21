package ru.gva.demo.interferens;

public class InterferenseThread extends Thread {
    private final  InterferenceExample checker;

    private volatile static int i;

    public InterferenseThread(String name ,InterferenceExample checker) {

        super(name);
        this.checker = checker;

    }

    @Override
    public void run() {
        System.out.println(this.getName() + " запущен");
        while (!checker.stop()){
            increment();
        }
        System.out.println(this.getName()+ " Завершен");
    }

    private static synchronized void increment() { ++i; }

    public int getI(){return i;}
}
