package lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] array = {"a", "b"};
        asList(array);
        swap(array, 0, 1);


        Integer[] arrayNum = {1, 4, 8};
        asList(arrayNum);
        swap(arrayNum, 2, 0);

        Box<Orange> box1 = new Box<Orange>();
        Box<Orange> box3 = new Box<Orange>();
        Box<Apple> box2 = new Box<Apple>();

        box1.addFruit(new Orange(), 11);
        box2.addFruit(new Apple(), 15);
        box3.addFruit(new Orange(), 2);

        if(box1.compareTo(box2) == 0) {
            System.out.println("сравнение: box1 весом " + box1.getWeight() + " и box2 весом " + box2.getWeight() + " равны");
        } else {
            System.out.println("сравнение: box1 весом " + box1.getWeight() + " и box2 весом " + box2.getWeight() + " не равны");
        }

        System.out.println("метод пересыпания до: box3: " + box3.getWeight() + ", box1: " + box1.getWeight());
        box1.pourTo(box3);
        System.out.println("метод пересыпания после: box3: " + box3.getWeight() + ", box1: " + box1.getWeight());

    }

    /*
     1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    */
        public static <T> void swap (T[] array, int i, int j){
            T t = array[i];
            array[i] = array[j];
            array[j] = t;
            System.out.println(Arrays.toString(array));
        }

        /*
     2. Написать метод, который преобразует массив в ArrayList;
    */
      public static <T> void asList (T[] array){
          List<T> list = new ArrayList<T>(Arrays.asList(array));
          for (T t: list ) {
              System.out.println(t);
          }
      }
    }
