package lesson2;

/*
        1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
        подаче массива другого размера необходимо бросить исключение MyArraySizeException.

        2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
        просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
        ячейке лежит символ или текст вместо числа), должно быть брошено исключение
        MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.

        3 В методе main() вызвать полученный метод, обработать возможные исключения
        MyArraySizeException и MyArrayDataException и вывести результат расчета (сумму элементов,
        при условии что подали на вход корректный массив).

        Заметка: Для преобразования строки к числу используйте статический метод класса Integer:
        Integer.parseInt(сюда_подать_строку);
        Заметка: Если Java не сможет преобразовать входную строку (в строке число криво написано), то
        будет сгенерировано исключение NumberFormatException.
*/

public class Main {
    public static void main(String[] args) {

        String[][] array = arrayInput();

        try {
            doArray(array);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    static String[][] arrayInput() {

        String[][] array = {
                {"12", "2", "4"},
                {"1", "3", "6", "2"},
                {"9", "8", "6", "3"},
                {"5", "2", "7", "4"},
        };
        return array;
    }

    public static void doArray(String[][] array) {

        for (int i = 0; i < array.length; i++) {

            if (array.length != 4) {
                throw new MyArraySizeException();
            }

            for (int j = 0; j < array[i].length; j++) {
                if (array[i].length != 4) {
                    throw new MyArraySizeException();
                }
            }
        }

        int sum = 0;
        int inputString;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    inputString = Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
                sum += inputString;
            }
        }

        System.out.println(sum);
    }
}

