package ru.gva.demo.downloadAllFiles;

import ru.gva.demo.download.DownloadUsingNIO;

/**
 * Класс для скачивание по url, полученную через консоль.
 *
 * @author Gavrikov V 15it18.
 */
public class UrlInConsole {

    private String urlForConsole;
    private String path;

    public UrlInConsole(String urlForConsole, String path) {
        this.urlForConsole = urlForConsole;
        this.path = path;
    }

    public void download(){
        new DownloadUsingNIO(urlForConsole,path).start();
    }
}
