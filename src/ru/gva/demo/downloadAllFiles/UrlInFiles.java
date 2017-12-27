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
 * Данный класс считывает url адреса из файла и созжает экземпляры объекта скачивания файлов.
 *
 * @author Gavrikov V. 15it18.
 */

public class UrlInFiles extends Thread {
    private List<String> urls;
    private String pathInOS;
    private String url;

    public UrlInFiles(String pathInOS, String url) {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    protected void addline(String str) throws NullPointerException{
        urls.add(str);
    }

    void addlines(String pathInOS) throws IOException {
        urls.addAll(Files.readAllLines(Paths.get(pathInOS)));
    }


}
