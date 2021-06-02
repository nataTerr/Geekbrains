package lesson2;

import jdk.nashorn.internal.runtime.arrays.ArrayData;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int i, int j) {
        super("Не удалось преобразовать элемент массива к числу: строка " + (i + 1) + " колонка " + (j + 1));
    }
}
