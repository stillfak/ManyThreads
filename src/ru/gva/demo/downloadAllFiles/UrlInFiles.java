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
 * Данный класс добавляет url адрес-а в List и считывает url адреса из List и создает экземпляры объекта скачивания файлов.
 *
 * @author Gavrikov V. 15it18.
 */

public class UrlInFiles extends Thread {
    private List<String> urls;
    private String pathInOS;
    private String url;

    UrlInFiles(String pathInOS, String url) {
        this.url = url;
        this.pathInOS = pathInOS;
        this.urls = new ArrayList<>();

    }

    @Override
    public void run() {
        try {
            DownloadUsingNIO download;
            String[] path = pathInOS.split("\\.");
            pattern(url);


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
     * Данный метод определяет "чем является строка, путем до файла или url"
     *
     * @param pathOrUrl строка
     * @throws IOException InputOutput
     */
    private void pattern(String pathOrUrl) throws IOException {
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
     *Данынй метод добавляет в List url адрес
     *
     * @param url адрес
     */

    private void addline(String url) {
        urls.add(url);
    }

    /**
     * Данный метод добавляет в List url адреса
     *
     * @param path путь до файла содрежащий url адреса
     * @throws IOException InputOutput
     */
    private void addlines(String path) throws IOException {
        urls.addAll(Files.readAllLines(Paths.get(path)));
    }


}
