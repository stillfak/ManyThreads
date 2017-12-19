package ru.gva.demo.music;

import java.io.*;

/**
 * Данный класс содержит методы которые позволяют скачать музыку с удаленного сервера.
 */
public class MusicDownload extends Thread {

    private static final String OUT_FILE_TXT = "src/ru/gva/demo/music/downloadmusic/outFile.txt";
    private static final String PATH_TO_MUSIC = "src/ru/gva/demo/music/downloadmusic/music/music";


    @Override
    public void run() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {

            String music;

            try {
                for (int count = 0;(music = musicFile.readLine()) != null; count++) {

                    new DownloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3").start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
