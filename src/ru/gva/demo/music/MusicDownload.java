package ru.gva.demo.music;

import ru.gva.demo.download.DownloadUsingNIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Данный класс содержит методы которые позволяют скачать музыку с удаленного сервера.
 */
public class MusicDownload extends Thread {

    private static final String OUT_FILE_TXT = "src/ru/gva/demo/music/downloadmusic/outFile.txt";
    private static final String PATH_TO_MUSIC = "src/ru/gva/demo/music/downloadmusic/music/music";


    @Override
    public void run() {
        try {
            List<String> urls = Files.readAllLines(Paths.get(OUT_FILE_TXT));

            DownloadUsingNIO download;

            try {
                for (int count = 0; count < urls.size(); count++) {

                    download = new DownloadUsingNIO(urls.get(count), PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                    download.start();
                    download.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
