package ru.gva.demo.readAndWriteThroughStreams;

import java.io.*;


/**
 * Данный класс содержит реализацию задачи "Многопоточное считывание данных из файлов".
 *
 * @author Gavrikov V. 15it18.
 */

public class ReadAndWriteThroughStreams extends Thread {



    private volatile static BufferedWriter bufferedWriter;//для записи в файл

    private String addres;//для передачи в поток адреса файла для считываение

    public ReadAndWriteThroughStreams(String addres) {
        this.addres = addres;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();

        String string;

        try (BufferedReader br = new BufferedReader(new FileReader(addres))) {

            while ((string = br.readLine()) != null) {

                write(string);

            }
        } catch (IOException e) {

            e.printStackTrace();

        }

        System.out.println((System.currentTimeMillis()-time));
    }


    /**
     * Данный класс записывает в файл строку.
     *
     * @param string строка записываемая в файл.
     * @throws IOException InputOutputException
     */
    private static synchronized void write(String string) throws IOException {

        bufferedWriter.write(string + "\n");

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        bufferedWriter = new BufferedWriter(new FileWriter(new File("src/ru/gva/demo/readAndWriteThroughStreams/combo.txt")));

        ReadAndWriteThroughStreams throughStreams = new ReadAndWriteThroughStreams("src/ru/gva/demo/readAndWriteThroughStreams/index.html");
        ReadAndWriteThroughStreams throughStreams_1 = new ReadAndWriteThroughStreams("src/ru/gva/demo/readAndWriteThroughStreams/modals.html");

        throughStreams.start();
        throughStreams_1.start();

        if (throughStreams.isAlive())
            throughStreams.join();

        if (throughStreams_1.isAlive())
            throughStreams_1.join();

        bufferedWriter.close();
    }
}
