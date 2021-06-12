package lesson5;

import java.util.Arrays;

public class Main {
    static final int size = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        // System.out.println(Arrays.toString(arr));
    }

    public static void secondMethod() throws InterruptedException {

        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();

        final int sizeFirst = arr.length / 2;
        final float[] arrFirst = new float[sizeFirst];
        final float[] arrSecond = new float[sizeFirst];

        System.arraycopy(arr, 0, arrFirst, 0, sizeFirst);
        System.arraycopy(arr, sizeFirst, arrSecond, 0, sizeFirst);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrFirst.length; i++) {
                    arrFirst[i] = (float) (arrFirst[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                            Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrSecond.length; i++) {
                    arrSecond[i] = (float) (arrSecond[i] * Math.sin(0.2f + (i + sizeFirst) / 5) * Math.cos(0.2f +
                            (i + sizeFirst) / 5) * Math.cos(0.4f + (i + sizeFirst) / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //System.out.println(Arrays.toString(arrFirst));
        //System.out.println(Arrays.toString(arrSecond));

        System.arraycopy(arrFirst, 0, arr, 0, sizeFirst);
        System.arraycopy(arrSecond, 0, arr, sizeFirst, sizeFirst);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        //System.out.println(Arrays.toString(arr));
    }


}
