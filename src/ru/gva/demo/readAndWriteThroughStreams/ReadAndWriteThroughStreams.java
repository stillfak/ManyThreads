package ru.gva.demo.readAndWriteThroughStreams;

import java.io.*;


/**
 * Данный класс содержит реализацию задачи "Многопоточное считывание данных из файлов".
 */

public class ReadAndWriteThroughStreams extends Thread {

    private volatile static BufferedWriter bufferedWriter;

    @Override
    public void run() {

        String string;

        try (BufferedReader br = new BufferedReader(new FileReader("src/ru/gva/demo/readAndWriteThroughStreams/index.html"))) {

            while ((string = br.readLine()) != null) {

                write(string + "\n");

            }
        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /**
     * Данный класс записывает в файл строку.
     *
     *
     * @param string строка записываемая в файл.
     * @throws IOException
     */
    private synchronized static void write(String string) throws IOException {

        bufferedWriter.write(string);

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        bufferedWriter = new BufferedWriter(new FileWriter( new File("src/ru/gva/demo/readAndWriteThroughStreams/combo.txt")));

        ReadAndWriteThroughStreams throughStreams = new ReadAndWriteThroughStreams();

        throughStreams.start();

        String string;

        try (BufferedReader br = new BufferedReader(new FileReader("src/ru/gva/demo/readAndWriteThroughStreams/modals.html"))) {

            while ((string = br.readLine()) != null) {

                write(string + "\n");

            }
        }

        if (throughStreams.isAlive()){

            throughStreams.join();

        }
        bufferedWriter.close();
    }
}
