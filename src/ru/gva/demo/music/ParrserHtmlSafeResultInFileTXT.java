package ru.gva.demo.music;


import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Данный класс содержит методы которые позволяют искать в html документе
 * ссылки на скачивание музыки, и сохранять их в файл
 *
 * @author Gavrikov V. 15it18.
 */
public class ParrserHtmlSafeResultInFileTXT extends Thread {
    private Pattern pattern;
    private BufferedReader bufferedReader;

    private static volatile BufferedWriter outFile;

    private ParrserHtmlSafeResultInFileTXT(Pattern pattern, BufferedReader bufferedReader) {
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
     *
     * @throws IOException          InputOutput
     * @throws InterruptedException checked exception
     */
    static void parrseout(Map<String, String> pathAndURL) throws IOException, InterruptedException {

        outFile = new BufferedWriter(new FileWriter("src/ru/gva/demo/music/downloadmusic/outFile.txt"));

        ParrserHtmlSafeResultInFileTXT[] patternOuts = createdAndStart(/**pathAndURL.keySet().toArray(new String[0]),*/ pathAndURL);

        aliveAndJoin(patternOuts);

        outFile.close();

    }

    /**
     *
     * @return
     */

    private static ParrserHtmlSafeResultInFileTXT[] createdAndStart(Map<String, String> pathAndURL) {
        String[] key = pathAndURL.keySet().toArray(new String[0]);
        ParrserHtmlSafeResultInFileTXT[] patternOuts = new ParrserHtmlSafeResultInFileTXT[key.length];

        for (int i = 0; i < key.length; i++) {

            try {
                patternOuts[i] = new ParrserHtmlSafeResultInFileTXT(Pattern.compile(pathAndURL.get(key[i])), new BufferedReader(new InputStreamReader(new URL(key[i]).openStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }

            patternOuts[i].start();
        }
        return patternOuts;
    }

    /**
     * Данный метод проверяет выполнение потоков, по очередно
     * если поток выполняется то он ждет когда он выполниться.
     *
     * @param patternOuts массив с потоками.
     * @throws InterruptedException checked exception
     */
    private static void aliveAndJoin(ParrserHtmlSafeResultInFileTXT[] patternOuts) throws InterruptedException {

        for (ParrserHtmlSafeResultInFileTXT patternOut : patternOuts) {
            if (patternOut.isAlive()) {
                patternOut.join();
            }
        }
    }
}
