package ru.geekbrains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2 {

    public int[] arrayAfterValue(int[] inArray) {

        final int value = 4;
        boolean hasValue = false;

        if (inArray.length == 0) {
            throw new RuntimeException("Массив должен содержать хотя бы один элемент");
        }

        List<Integer> outArray = new ArrayList<>();

        for (int i = inArray.length - 1; i >= 0; i--) {
            if (Math.abs(inArray[i]) == value) {
                hasValue = true;
                break;
            }
            outArray.add(inArray[i]);
        }

        if (!hasValue)
            throw new RuntimeException("В массиве нет элемента, содержащего значение " + value);

        Collections.reverse(outArray);

        return outArray.stream().mapToInt(Integer::intValue).toArray();
    }
}
