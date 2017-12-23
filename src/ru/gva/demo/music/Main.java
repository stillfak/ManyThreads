package ru.gva.demo.music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс считывает url адреса странички и регулярные вырожении,
 * разделяет их(строка представляет собой: адрес \\s регулярное вырожение),
 * отправляет в ассоциативный массив(ключ - адрес саайта, значение - регулярное вырожение),
 * после всех переданных ключей и значений в ассоциативный массив,
 * далбше он передается в класс ParrserHtmlSafeResultInFileTXT,
 * запуск скачивание музыки.
 *
 * @author Gavrikov V. 15it18.
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

                pathAndURL.put(splitStr[0], splitStr[1]);


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ParrserHtmlSafeResultInFileTXT.parrseout(pathAndURL);


        MusicDownload download = new MusicDownload();


        download.start();
    }


}
