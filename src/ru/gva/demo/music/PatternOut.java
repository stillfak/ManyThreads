package ru.gva.demo.music;


import java.io.*;
import java.net.URL;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 *Данный класс содержит методы которые позволяют искать в html документе
 *ссылки на скачивание музыки, и сохранять их в файл
 *
 * @author Gavrikov V. 15it18.
 */
public class PatternOut extends Thread {
    private Pattern pattern;
    private BufferedReader bufferedReader;

    private static volatile BufferedWriter outFile;

    private PatternOut(Pattern pattern, BufferedReader bufferedReader) {
        this.pattern = pattern;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        String result;

        while ((result = bufferedReader.lines().collect(Collectors.joining("\n"))) != null) {

            Pattern email_pattern = pattern;
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

    /**
     * Данный метод записывает все ссылки, со всех потоков,
     * на скачивание музыки
     *
     * @param string ссылка на скачивание
     * @throws IOException InputOutput
     */
    private static synchronized void write(String string) throws IOException {
        outFile.write(string + "\r\n");
    }

    /**
     * Тестовый метод, который создает n кол-во потоков,
     * запускает и ждет пока они выполняются.
     *
     * @param url - массив с url адресами сайтов
     * @param patterns - массив с шаблонами для регулярного вырожения.
     * @throws IOException InputOutput
     * @throws InterruptedException checked exception
     */
    static void parrseout(String[] url, String[] patterns) throws IOException, InterruptedException {


        outFile = new BufferedWriter(new FileWriter("src/ru/gva/demo/music/downloadmusic/outFile.txt"));


        PatternOut[] patternOuts = new PatternOut[url.length];

        for (int i = 0; i < patternOuts.length; i++) {

            patternOuts[i] = new PatternOut(Pattern.compile(patterns[i]), new BufferedReader(new InputStreamReader(new URL(url[i]).openStream())));

            patternOuts[i].start();
        }

        aliveAndJoin(patternOuts);

        outFile.close();

    }

    /**
     * Данный метод проверяет выполнение потоков, по очередно
     * если поток выполняется то он ждет когда он выполниться.
     *
     * @param patternOuts массив с потоками.
     * @throws InterruptedException checked exception
     */
    private static void aliveAndJoin(PatternOut[] patternOuts) throws InterruptedException {

        for (PatternOut patternOut : patternOuts) {
            if (patternOut.isAlive()) {
                patternOut.join();
            }
        }
    }
}
