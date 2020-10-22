package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @param <T> - принимаемое значение любого числового формата
 *            данный класс предназначен для реализации поведения сущности - буфер
 */
class Buffer<T extends Comparable<T>>//принимаемое значение любого числового формата
{
    private int bufferSize; //размерность буфера
    private ArrayList<T> buffer; //буфер

    Buffer(int size) {
        bufferSize = size;
        buffer = new ArrayList<>(size);
    }

    // i - число, добавляемое к буферу
    void add(T i) {
        buffer.add(i);
    }

    //i - число, удаляемое из буфера
    void remove(T i) {
        buffer.remove(i);
    }

    //полон ли буфер
    boolean IsFull() {
        return buffer.size() == bufferSize;
    }

    //пуст ли буфер
    boolean isEmpty() {
        return buffer.isEmpty();
    }

    //последнее значение в списке
    T lastValue() {
        return buffer.get(bufferSize - 1);
    }

    T value() {
        return buffer.get(bufferSize);
    }
    //генерация рандомных чисел
    int randValue() {
        int i = (int)Math.random() * (23 - 20 + 1) + 20;
        return i;
    }
    //минус 20 от значения


    //поиск минимального значения
    T minValue() {
        return Collections.min(buffer);
    }
}

