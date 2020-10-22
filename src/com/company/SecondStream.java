package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class SecondStream extends Thread
{
    private final int N = 25;

    private final ArrayList<Integer> buffer = new ArrayList<>(25); //буфер второго потока
    private final ArrayList<Integer> bufferGenerator; // буфер первого потока

    SecondStream(GeneratedBuffer generator) {
        bufferGenerator = generator.getBuffer();
    }

    ArrayList<Integer> getBuffer() {
        return buffer;
    }


    public boolean bufferIsFull() {
        return buffer.size() == N;
    }

    /**
     * запуск второго потока
     * Нахождение минимума среди значений первого буфера
     * с последующим удалением минимума из буфера первого потока
     * и записью в буфер второго потока
     */
    @Override
    public void run() {
        int min, result;
        for (int i = 0; i < 99; i++) {
            synchronized (bufferGenerator) {
                while (bufferGenerator.isEmpty()) {
                    try {
                        bufferGenerator.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int index = bufferGenerator.size()-1;

                min = bufferGenerator.get(index);
                result = min - 20;

                bufferGenerator.remove(index);
                bufferGenerator.notifyAll();
            }

            synchronized (buffer) {
                while (bufferIsFull()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                buffer.add(result);
                System.out.printf("второй поток, число %d минус 20: %s%n", i , result);
                buffer.notifyAll();
            }
        }
    }

}





