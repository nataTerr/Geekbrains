package ru.geekbrains;

public class Task3 {

    public boolean arrayOneAndFour(int[] array) {

        final int valueFirst = 1;
        final int valueSecond = 4;

        if (array.length == 0) {
            throw new RuntimeException("Массив должен содержать хотя бы один элемент");
        }

        for (int i = 0; i < array.length; i++) {
            if (Math.abs(array[i]) == valueFirst || Math.abs(array[i]) == valueSecond) {
                return true;
            }
        }
        return false;
    }
}
