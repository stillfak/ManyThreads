package ru.gva.demo.downloadAllFiles;

import ru.gva.demo.download.DownloadUsingNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Данный класс считывает url адреса из файла и созжает экземпляры объекта скачивания файлов.
 *
 * @author Gavrikov V. 15it18.
 */

public class UrlInFiles extends Thread {
    private String pathToFilesURL;
    private String extesio;

    private static final String PATH_AND_NAME = "/home/vadim/Документы/ManyThreads/src/ru/gva/demo/downloadAllFiles/download/";


    public UrlInFiles(String pathToFilesURL, String extesio) {
        this.extesio = extesio;
        this.pathToFilesURL = pathToFilesURL;
    }

    @Override
    public void run() {
        try {
            List<String> urls = Files.readAllLines(Paths.get(pathToFilesURL));
            DownloadUsingNIO download;

            for (int i = 0; i < urls.size(); i++) {
                download = new DownloadUsingNIO(urls.get(i), PATH_AND_NAME + String.valueOf(i) + "." + extesio);
                download.start();
                download.join();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
