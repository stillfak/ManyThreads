package ru.gva.demo.copyFiles;

/**
 * Тестовый класс для задачи "Копирование файлов".
 *
 * @author Gavrikov V. 15oit18.
 */

public class Main {
    private static final String ADDRES = "src/ru/gva/demo/readAndWriteThroughStreams/index.html";

    public static void main(String[] args) throws InterruptedException {

        CopyFiles copyFiles = new CopyFiles(ADDRES, "src/ru/gva/demo/copyFiles/index(copy).txt");
        CopyFiles copyFiles1 = new CopyFiles(ADDRES, "src/ru/gva/demo/copyFiles/index(copy_2).txt");

        start(copyFiles);
        copyJoin(copyFiles);

        System.out.println("Задание первое -" + copyFiles.getTimeRun());

        copyFiles = new CopyFiles(ADDRES, "src/ru/gva/demo/copyFiles/index(copy).txt");

        start(copyFiles);
        copyJoin(copyFiles);

        start(copyFiles1);
        copyJoin(copyFiles1);

        System.out.println("Задание второе -" + (copyFiles.getTimeRun() + copyFiles1.getTimeRun()));

        copyFiles = new CopyFiles(ADDRES, "src/ru/gva/demo/copyFiles/index(copy).txt");
        copyFiles1 = new CopyFiles(ADDRES, "src/ru/gva/demo/copyFiles/index(copy_2).txt");

        start(copyFiles, copyFiles1);
        copyJoin(copyFiles, copyFiles1);

        System.out.println("Задание третье: ");
        System.out.println(copyFiles.getTimeRun());
        System.out.println(copyFiles1.getTimeRun());


    }

    /**
     * Метод запускает 2 потока.
     *
     * @param copyFiles 1 поток
     * @param copyFiles1 2 поток
     */
    private static void start(CopyFiles copyFiles, CopyFiles copyFiles1) {
        copyFiles.start();
        copyFiles1.start();

    }

    /**
     * Метод запускает один поток
     *
     * @param copyFiles поток
     */
     private static void start(CopyFiles copyFiles) {
        copyFiles.start();
    }

    /**
     * Метод вызывает метод join() для потоков, который ждет пока выполниться поток.
     *
     * @param copyFiles 1 поток
     * @param copyFiles1 2 поток
     * @throws InterruptedException исключение.
     */
    private static void copyJoin(CopyFiles copyFiles, CopyFiles copyFiles1) throws InterruptedException {
        copyFiles.join();
        copyFiles1.join();
    }

    /**
     * метод вызывает метод join() для потока, который ждет пока поток выпониться.
     *
     * @param copyFiles поток.
     * @throws InterruptedException исключение.
     */
    private static void copyJoin(CopyFiles copyFiles) throws InterruptedException {
        copyFiles.join();
    }

}
