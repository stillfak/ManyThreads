package ru.gva.demo.copyFiles;

import java.io.*;

/**
 * Данный класс содержит объект поток, для реализации задачи "Копирование файлов".
 *
 * @author Gavrikov V. 15it18.
 */

public class CopyFiles extends Thread{

    private String addresSource;
    private String addresRecipient;
    private long timeRun;

    CopyFiles(String addresSource, String addresRecipient) {
        this.addresSource = addresSource;
        this.addresRecipient = addresRecipient;
    }

    long getTimeRun() {
        return timeRun;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(addresSource));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(addresRecipient))) {

            String copyLine;

            while ((copyLine = bufferedReader.readLine()) !=null){

                bufferedWriter.write(copyLine);

            }
            timeRun = System.currentTimeMillis()-time;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
