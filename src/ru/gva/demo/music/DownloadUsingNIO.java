package ru.gva.demo.music;

import java.io.*;
import java.net.*;
import java.nio.channels.*;


public class DownloadUsingNIO extends Thread {

    private String strUrl;
    private String file;

    DownloadUsingNIO(String strUrl, String file) {
        this.strUrl = strUrl;
        this.file = file;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel byteChannel = null;
        try {
            assert url != null;
            byteChannel = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert stream != null;
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert byteChannel != null;
            byteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
