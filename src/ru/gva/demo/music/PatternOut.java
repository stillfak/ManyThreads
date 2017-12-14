package ru.gva.demo.music;


import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PatternOut extends Thread {
    private Pattern pattern;
    private BufferedReader bufferedReader;

    public static volatile BufferedWriter outFile;

    public PatternOut(Pattern pattern, BufferedReader bufferedReader) {
        this.pattern = pattern;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        String result;

        while ((result = bufferedReader.lines().collect(Collectors.joining("\n"))) != null) {

            Pattern email_pattern = pattern; //Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
            Matcher matcher = email_pattern.matcher(result);

            while (matcher.find()) {

                write(matcher.group());

            }

            break;

        }

    }

    private static synchronized void write(String string) {
        try {

            outFile.write(string + "\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parrseout(String[] url, String[] patterns){

        try {
            outFile = new BufferedWriter(new FileWriter("src/ru/gva/demo/music/downloadmusic/outFile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PatternOut[] patternOuts = new PatternOut[url.length];

        for (int i = 0; i < url.length ; i++) {

            try {

                patternOuts[i] = new PatternOut(Pattern.compile(patterns[i]),new BufferedReader(new InputStreamReader(new URL(url[i]).openStream())));

            } catch (IOException e) {
                e.printStackTrace();
            }

            patternOuts[i].start();
        }

        aliveAndJoin(patternOuts);

        try {
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void aliveAndJoin(PatternOut[] patternOuts) {

        for (PatternOut patternOut : patternOuts) {

            if (patternOut.isAlive()) {
                try {
                    patternOut.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
