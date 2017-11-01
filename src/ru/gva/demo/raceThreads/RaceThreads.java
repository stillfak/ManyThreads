package ru.gva.demo.raceThreads;

/**
 * Данный класс содержит реализованную задачу "Догонялки".
 *
 * @author Gavrikov V. 15IT18
 */
public class RaceThreads extends Thread {

    public static final int MAX_COUNT_OF_STEPS = 200;
    public static final int SLEEP_THREAD = 5; // кол-во на который поток приостанавливается(5 = 0.005 секунд)

    private int i;

    @Override
    public void run() {

        for (i = 1; i <= MAX_COUNT_OF_STEPS; i++) {

            System.out.println("raceThread - " + i);

            try {

                sleep(SLEEP_THREAD);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        RaceThreads raceThreads = new RaceThreads();
        Thread thread = Thread.currentThread();

        raceThreads.setPriority(MAX_PRIORITY);
        thread.setPriority(MIN_PRIORITY);

        raceThreads.start();

        for (int i = 1; i <= MAX_COUNT_OF_STEPS; i++) {

            System.out.println("Thread - " +  i);

            try {

                thread.sleep(SLEEP_THREAD);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            if (raceThreads.i % 5  == 0){

                thread.setPriority(MAX_PRIORITY);
                raceThreads.setPriority(MIN_PRIORITY);

            }
            if (i % 4 ==0){

                thread.setPriority(MIN_PRIORITY);
                raceThreads.setPriority(MAX_PRIORITY);

            }
        }
    }
}
