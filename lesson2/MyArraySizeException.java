package lesson2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    public MyArraySizeException() {
        System.out.println("Введите массив размером 4*4");
    }
}
