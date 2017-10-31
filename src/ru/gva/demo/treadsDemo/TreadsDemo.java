package ru.gva.demo.treadsDemo;

/**
 * Класс, демонстрирует использование
 * методов класса Tread
 * в главном потоке программы
 *
 * @author Gavrikov V 15it18.
 */

public class TreadsDemo {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        //
        System.out.println(thread);
        System.out.println("name "+thread.getName());
//
//        System.out.println(thread.getPriority());
        System.out.println("приоретет " +thread.getPriority());
        System.out.println("гркппа потока "+ thread.getThreadGroup());
        System.out.println("индификатор потока " +thread.getId());
        System.out.println("состояние потока " +thread.getState());
        thread.setName("asdsda");
        thread.setPriority(10);
        System.out.println(thread);

        for (int i = 5; i >0 ; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("поток завершился");
            }

        }




    }
}
