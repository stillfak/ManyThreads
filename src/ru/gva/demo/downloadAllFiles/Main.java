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
        Pattern urlPattern = Pattern.compile("^http[s]?:\\/\\/.*$");
        Matcher matcher = pattern.matcher(pathOrUrl);
        Matcher matcher1 = urlPattern.matcher(pathOrUrl);

        if (matcher.find()) {

            String extes = Input.inputInConsole("Введите расширение файла");

            new UrlInFiles(matcher.group(), extes).start();

        } else if (matcher1.find()){

            String pathInOS = Input.inputInConsole("Введите путь, куда сохранаять файл, его имя и расширение ");

            new UrlInConsole(matcher1.group(), pathInOS).download();
        }


    }


}
