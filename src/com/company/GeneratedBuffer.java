package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
     * вариант 21
     * Модель поведения первого потока
     * Генерация чисел от 20 до 23
     */
    public class GeneratedBuffer extends Thread
    {
        //Размер буфера
        private final int N = 25;
        //буфер со сгенерированными числами
        private final ArrayList<Integer> generatedBuffer = new ArrayList<>(25);

        public ArrayList<Integer> getBuffer() {//для передачи массива, в другой класс
            return generatedBuffer;
        }
        //Метод проверки заполненности буфера
        public boolean bufferIsFull() {
            return generatedBuffer.size() == N;
        }


        public Random random = new Random();



        //запуск потока
         // генерация чисел от 1 до 100 с последующим перемещением в буфер

        @Override
        public void run() {

            int min = 20;
            int max = 23;
            int diff = max - min;
            //Заполняем буфер числами
            for (int i = 1; i <= 100; i++) {

                //Синхронизируем потоки
                synchronized (generatedBuffer) {

                    //Пока буфер полный
                    while (bufferIsFull()) {
                        try {
                            generatedBuffer.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Первый поток был остановлен");
                        }
                    }
                    int ran = random.nextInt(diff + 1) + 20;
                    generatedBuffer.add(ran);

                    System.out.printf("Число номер: %d равно: %s%n", i , ran);
                    generatedBuffer.notifyAll();
                }
            }
        }
    }


