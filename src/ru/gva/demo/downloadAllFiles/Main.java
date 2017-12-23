package ru.gva.demo.downloadAllFiles;


import ru.gva.demo.input.Input;

import java.util.regex.*;

/**
 * В классе реализованно простое консольное управление программой.
 *
 * @author Gavrikov V 15it18.
 */

public class Main {

    public static void main(String[] args) {
        String pathOrUrl = Input.inputInConsole("Введите адрес до скачиваемого Файла или путь до файла(.txt) с ссылками");

        Pattern pattern = Pattern.compile(".*\\.txt");
        Matcher matcher = pattern.matcher(pathOrUrl);

        if (matcher.find()) {

            String extes = Input.inputInConsole("Введите расширение файла");

            new UrlInFiles(matcher.group(), extes).start();

        } else {

            String pathInOS = Input.inputInConsole("Введите путь, куда сохранаять файл, его имя и расширение ");

            new UrlInConsole(pathOrUrl, pathInOS).download();
        }


    }


}
