package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task3Test {
    private static Task3 arr;

    @BeforeAll
    public static void task3() {
        arr = new Task3();
    }

    @ParameterizedTest
    @MethodSource("dataForArray")
    public void testArrayOneAndFour(int[] array, boolean res) {
        try {
            Assertions.assertEquals(res, arr.arrayOneAndFour(array));
        } catch (RuntimeException e) {
            Assertions.assertThrows(RuntimeException.class, () -> arr.arrayOneAndFour(array));
        }
    }

    public static Stream<Arguments> dataForArray() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{1, 5, 1}, true));
        out.add(Arguments.arguments(new int[]{4, 4, 4}, true));
        out.add(Arguments.arguments(new int[]{0, 2, 3}, false));
        out.add(Arguments.arguments(new int[]{4, 2, 3}, true));
        out.add(Arguments.arguments(new int[]{-4, 10, 0}, true));
        out.add(Arguments.arguments(new int[]{-1, -1, 1}, true));
        out.add(Arguments.arguments(new int[]{0, 0, 0}, false));
        out.add(Arguments.arguments(new int[]{}, false));
        return out.stream();
    }
}