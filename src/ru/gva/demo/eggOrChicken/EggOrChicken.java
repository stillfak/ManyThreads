package ru.gva.demo.eggOrChicken;

/**
 * Данный класс содержит реализованную задачу "Курица или яйцо".
 *
 * @author Gavrikov V. 15it18.
 */
public class EggOrChicken extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            setPriority(1);
            try{
                sleep(200);		//Приостанавливает поток на 1 секунду
        }catch(InterruptedException e){}
        System.out.println("яйцо!" + i);
    }
    //Слово «яйцо» сказано 5 раз
}

    public static void main(String[] args) {
        EggOrChicken eggOrChicken = new EggOrChicken();//Создание потока
        EggOrChicken eggOrChicken1 =new EggOrChicken();
        Thread thread = Thread.currentThread();
        thread.setPriority(10);

        System.out.println("Спор начат...");
        eggOrChicken.start(); //Запуск потока

        for(int i = 0; i < 100; i++) {
            try{
                Thread.sleep(200);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            System.out.println("курица!" + i);
        }

        //Слово «курица» сказано 5 раз

        if(eggOrChicken.isAlive()){	//Если оппонент еще не сказал последнее слово

            try{
                eggOrChicken.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException e){}

            System.out.println("Первым появилось яйцо!");
        }
        else{	//если оппонент уже закончил высказываться
            System.out.println("Первой появилась курица!");
        }
        System.out.println("Спор закончен!");
    }
}

