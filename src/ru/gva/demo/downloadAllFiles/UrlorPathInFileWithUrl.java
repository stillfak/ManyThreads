package ru.gva.demo.downloadAllFiles;

import ru.gva.demo.download.DownloadUsingNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Данный класс добавляет url адрес-а в List и считывает urlOrPath адреса из List и создает экземпляры объекта скачивания файлов.
 *
 * @author Gavrikov V. 15it18.
 */

public class UrlorPathInFileWithUrl extends Thread {
    private List<String> urls;
    private String pathInOS;
    private String urlOrPath;

    UrlorPathInFileWithUrl(String pathInOS, String urlOrPath) {
        this.urlOrPath = urlOrPath;
        this.pathInOS = pathInOS;
        this.urls = new ArrayList<>();

    }

    @Override
    public void run() {
        try {
            DownloadUsingNIO download;
            String[] path = pathInOS.split("\\.");
            typeStr(urlOrPath);


            for (int i = 0; i < urls.size(); i++) {
                download = new DownloadUsingNIO(urls.get(i) , path[0]+ String.valueOf(i) + "." + path[1]);
                download.start();
                download.join();

            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Данный метод определяет "чем является строка, путем до файла или urlOrPath"
     *
     * @param pathOrUrl строка
     * @throws IOException InputOutput
     */
    private void typeStr(String pathOrUrl) throws IOException {
        Pattern url = Pattern.compile("^http[s]?:.*$");
        Pattern pathINOS = Pattern.compile(".*\\.txt$");

        Matcher mUrl = url.matcher(pathOrUrl);
        Matcher mPathInOS = pathINOS.matcher(pathOrUrl);

        if (mUrl.find()){
            addline(pathOrUrl);

        }
        if (mPathInOS.find()){
            addlines(pathOrUrl);
        }

    }

    /**
     *Данынй метод добавляет в List urlOrPath адрес
     *
     * @param url адрес
     */

    private void addline(String url) {
        urls.add(url);
    }

    /**
     * Данный метод добавляет в List urlOrPath адреса
     *
     * @param path путь до файла содрежащий urlOrPath адреса
     * @throws IOException InputOutput
     */
    private void addlines(String path) throws IOException {
        urls.addAll(Files.readAllLines(Paths.get(path)));
    }


}
