package ru.gva.demo.music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Main {

    private static final String IN_FILE_TXT = "src/ru/gva/demo/music/InFile.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, String> pathAndURL = new HashMap<>();

        String str;
        String[] splitStr;

        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {

            while ((str = inFile.readLine()) != null) {
                splitStr = str.split(" ");
//                for (int i = 0; i <splitStr.length ; i++) {
//                 System.out.println(splitStr[0]);
//                System.out.println(splitStr[1]);
//                }
                pathAndURL.put(splitStr[0], splitStr[1]);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String[] strings = pathAndURL.keySet().toArray(new String[0]);
        for (int i = 0; i <strings.length ; i++) {
            System.out.println(pathAndURL.get(strings[i]));
        }
//        pathAndURL.put("https://zvonko.me/", "\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
//        pathAndURL.put("https://mp3up.me/", "\\s*(?<=audio\\s?src=\")[^>]*\\.mp3");
        //ParrserHtmlSafeResultInFileTXT.parrseout(pathAndURL);


     //   MusicDownload music1 = new MusicDownload();


       // music1.start();
    }


}
