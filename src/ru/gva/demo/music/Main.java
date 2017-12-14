package ru.gva.demo.music;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.regex.*;
import java.util.stream.Collectors;


public class Main {
    private static final String IN_FILE_TXT = "src/ru/gva/demo/music/InFile.txt";
    private static final String OUT_FILE_TXT = "src/ru/gva/demo/music/downloadmusic/outFile.txt";
    private static final String PATH_TO_MUSIC = "src/ru/gva/demo/music/downloadmusic/music";

    public static void main(String[] args) {
        String Url;
        String result;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
                Matcher matcher = email_pattern.matcher(result);
                int i = 0;
                while (matcher.find() && i < 2) {
                    outFile.write(matcher.group() + "\r\n");
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
//            String music;
//            int count = 0;
//            try {
//                while ((music = musicFile.readLine()) != null) {
//                    downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
//                    count++;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
