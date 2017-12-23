package ru.gva.demo.downloadJPG;

import ru.gva.demo.download.DownloadUsingNIO;

/**
 * Данный класс содержит реализацию задачи "скачивание фотографий".
 *
 * @author Gavrikov V. 15it18.
 */
public class Main {
    private static final String PATH_TO_JPEG ="src/ru/gva/demo/downloadJPG/";

    public static void main(String[] args) {

        String url = "https://cdn.pixabay.com/photo/2017/05/15/17/43/cup-2315554_960_720.jpg";

        new DownloadUsingNIO(url,PATH_TO_JPEG + "1"+".jpg").start();

    }
}
