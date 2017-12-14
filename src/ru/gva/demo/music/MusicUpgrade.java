package ru.gva.demo.music;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.*;

public class MusicUpgrade extends Thread {
    private int i;

    public MusicUpgrade(int i) {
        this.i = i;
    }

    private static final String IN_FILE_TXT = "src/ru/gva/demo/music/InFile.txt";
    private static final String OUT_FILE_TXT = "src/ru/gva/demo/music/downloadmusic/outFile.txt";
    private static final String PATH_TO_MUSIC = "src/ru/gva/demo/music/downloadmusic/music";


    @Override
    public void run() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    if (count%2 ==i) {
                        downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                    }
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        String[] url = new String[2];
        String[] pattern = {"\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")" ,"\\s*(?<=audio src=\")[^>]*\\.mp3"};
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {

            for (int i = 0; i <url.length ; i++) {

                url[i] = inFile.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PatternOut.parrseout(url,pattern);


        MusicUpgrade music1 = new MusicUpgrade(0);
        MusicUpgrade music2 = new MusicUpgrade(1);

       // music1.start();
       // music2.start();
    }



    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}
