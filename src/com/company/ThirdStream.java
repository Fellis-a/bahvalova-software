package com.company;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Collections;

public class ThirdStream extends Thread {
    //Флаг для остановки потока
    private boolean stopped = false;
    private int result;

    private final ArrayList<Integer> bufferSecondStream; //буфер второго потока

    ThirdStream(SecondStream valueFinder) {
        bufferSecondStream = valueFinder.getBuffer();
    }

    //Метод, останавливающий поток
    public void stopThread() {
        stopped = true;
    }

    /**
     * запуск третьего потока
     */
    @Override
    public void run() {
        int min;

        while (!stopped) synchronized (bufferSecondStream) {
                {
                    while (bufferSecondStream.isEmpty()) {
                        try {
                            bufferSecondStream.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    min = minValue(bufferSecondStream);
                    bufferSecondStream.remove(bufferSecondStream.indexOf(min));
                    result = min / 3;

                    System.out.printf(String.format("третий поток: %d res: %d\r\n", min, result));

                    bufferSecondStream.notifyAll();
                }

            }

    }

    public Integer minValue(ArrayList<Integer> list){
            return Collections.min(list);
        }
    }





