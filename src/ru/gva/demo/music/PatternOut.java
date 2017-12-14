package ru.gva.demo.music;


import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 */
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

                try {
                    write(matcher.group());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            break;

        }

    }

    private static synchronized void write(String string) throws IOException {
        outFile.write(string + "\r\n");
    }

    public static void parrseout(String[] url, String[] patterns) throws IOException, InterruptedException {


        outFile = new BufferedWriter(new FileWriter("src/ru/gva/demo/music/downloadmusic/outFile.txt"));


        PatternOut[] patternOuts = new PatternOut[url.length];

        for (int i = 0; i < patternOuts.length; i++) {

            patternOuts[i] = new PatternOut(Pattern.compile(patterns[i]), new BufferedReader(new InputStreamReader(new URL(url[i]).openStream())));

            patternOuts[i].start();
        }

        aliveAndJoin(patternOuts);

        outFile.close();

    }

    private static void aliveAndJoin(PatternOut[] patternOuts) throws InterruptedException {

        for (PatternOut patternOut : patternOuts) {
            if (patternOut.isAlive()) {
                patternOut.join();
            }
        }
    }
}
