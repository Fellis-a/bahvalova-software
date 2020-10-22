package com.company;

import sun.rmi.rmic.Generator;

public class Main {

    public static void main(String[] args) {

        GeneratedBuffer generator = new GeneratedBuffer();
        SecondStream secFinder = new SecondStream(generator);
        ThirdStream thirdFinder = new ThirdStream(secFinder);

        generator.start();
        secFinder.start();
        thirdFinder.start();

        try {
            generator.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был остановлен");
        }

        try {
            thirdFinder.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был остановлен");
        }


        try {
            secFinder.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был остановлен");
        }

        generator.interrupt();
        secFinder.interrupt();
        thirdFinder.stopThread();
    }
}

