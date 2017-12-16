package ru.gva.demo.music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

    private static final String IN_FILE_TXT = "src/ru/gva/demo/music/InFile.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] url = new String[2];
        String[] pattern = {"\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")", "\\s*(?<=audio src=\")[^>]*\\.mp3", "\\s*(?<=href=\")[^>]*\\.mp3"};

        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {

            for (int i = 0; i < url.length; i++) {

                url[i] = inFile.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ParrserHtmlSafeResultInFileTXT.parrseout(url, pattern);


        MusicDownload music1 = new MusicDownload(0);
        // MusicDownload music2 = new MusicDownload(1);

        music1.start();
        // music2.start();
    }

}
