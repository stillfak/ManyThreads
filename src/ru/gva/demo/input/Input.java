package ru.gva.demo.input;

import java.util.Scanner;

/**
 * Данный класс служит для ввода данных.
 */

public class Input {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Метод служит для ввода строки
     *
     * @return строка
     */
    public static String inputInConsole(){
        return sc.next();
    }

    /**
     * Метод для ввода строки с сообщением пользователю.
     *
     * @param message сообщение
     * @return строка
     */
    public static String inputInConsole(String message){
        System.out.println(message);
        return sc.next();
    }

}
