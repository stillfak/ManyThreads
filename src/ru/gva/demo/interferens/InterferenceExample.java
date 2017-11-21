package ru.gva.demo.interferens;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Содержит объект InterferenceExample.
 *
 * @author Gavrikov V. 15it18
 */
public class InterferenceExample {

    private static final int HUNDRED_MILLION = 100_000_000;

    private AtomicInteger counter = new AtomicInteger();

    public boolean stop(){
        return counter.incrementAndGet()> HUNDRED_MILLION;
    }

    /**
     * Данный метод создает 2 потока, запускает их, ждет когда они выполняться, и выводт результаты
     *
     * @throws InterruptedException
     */
    public void example() throws InterruptedException {
        InterferenseThread thread1 = new InterferenseThread("Поток 1", this);
        InterferenseThread thread2 = new InterferenseThread("Поток 2", this);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Ожидаем: " + HUNDRED_MILLION);
        System.out.println("Получаем: " + thread1.getI());

    }
}
