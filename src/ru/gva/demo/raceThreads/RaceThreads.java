package ru.gva.demo.raceThreads;

/**
 * Данный класс содержит реализованную задачу "Догонялки".
 *
 * @author Gavrikov V. 15IT18
 */
public class RaceThreads extends Thread {

    private static final int MAX_COUNT_OF_STEPS = 200_000;
    private static final int SLEEP_THREAD = 5; // кол-во на который поток приостанавливается(5 = 0.005 секунд)

    private int i;

    @Override
    public void run() {

        for (i = 1; i <= MAX_COUNT_OF_STEPS; i++) {

            System.out.println(getName() + " - " + i);

            try {

                sleep(SLEEP_THREAD);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        RaceThreads raceThreads = new RaceThreads();
        RaceThreads raceThreads1 = new RaceThreads();

        raceThreads.setPriority(MAX_PRIORITY);
        raceThreads1.setPriority(MIN_PRIORITY);

        raceThreads.start();
        raceThreads1.start();

        while (raceThreads.isAlive() || raceThreads1.isAlive()) {

            if (raceThreads.i % 1000 == 0) {

                raceThreads1.setPriority(MAX_PRIORITY);
                raceThreads.setPriority(MIN_PRIORITY);

            }

            if (raceThreads1.i % 2000 == 0) {

                raceThreads1.setPriority(MIN_PRIORITY);
                raceThreads.setPriority(MAX_PRIORITY);

            }
        }
    }
}
