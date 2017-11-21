package ru.gva.demo.interferens;

/**
 * Содержит поток
 *
 * @author Gavrikov V. 15it18.
 */
public class InterferenseThread extends Thread {
    private final  InterferenceExample checker;

    private static int i;

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

    /**
     * Данный метод увеличивает переменную i на 1
     */
    private void increment() { ++i; }

    public int getI(){return i;}
}
