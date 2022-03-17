package ru.geekbrains;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task2Test extends TestCase {

    private static Task2 arr;

    @BeforeAll
    public static void task3() {
        arr = new Task2();
    }

    @ParameterizedTest
    @MethodSource("dataForArray")

    public void testArrayAfterValue(int[] inArray, int[] outArray) {
        try {
            Assertions.assertArrayEquals(outArray, arr.arrayAfterValue(inArray));
        } catch (RuntimeException e) {
            Assertions.assertThrows(RuntimeException.class, () -> arr.arrayAfterValue(inArray));
        }
    }

    public static Stream<Arguments> dataForArray() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.arguments(new int[]{4, 4, 4}, new int[]{}));
        out.add(Arguments.arguments(new int[]{0, 2, 3}, new int[]{}));
        out.add(Arguments.arguments(new int[]{4, -4, 3}, new int[]{3}));
        out.add(Arguments.arguments(new int[]{-4, 10, 0}, new int[]{10, 0}));
        out.add(Arguments.arguments(new int[]{-4, -4, 0}, new int[]{0}));
        out.add(Arguments.arguments(new int[]{}, new int[]{}));
        return out.stream();
    }
}