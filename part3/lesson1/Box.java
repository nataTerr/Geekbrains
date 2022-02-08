package lesson1;

import java.util.ArrayList;


/* 3. Большая задача:
         a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
         b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
         поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
         c. Для хранения фруктов внутри коробки можете использовать ArrayList;
         d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
         (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
         e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
         которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
         (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
         f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
         (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
         соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
         g. Не забываем про метод добавления фрукта в коробку.
         */

public class Box <T extends Fruit> implements Comparable<Box>{

    private ArrayList<T> box;

    public Box() {
        box = new ArrayList<T>();
    }

    public float getWeight(){
        float weight = 0.0f;
        for(T t : box){
            weight += t.getWeight();
        }
        return weight;
    }

    public void pourTo(Box<T> o){
        o.box.addAll(box);
        box.clear();
    }

    public void addFruit(T fruit, int amount){
        for(int i = 0;i < amount; i++){
            box.add(fruit);
        }
    }

    public int compareTo(Box o) {
        float eps = 0.000001f;
        if(Math.abs(getWeight() - o.getWeight()) < eps) {
            return 0;
        }
        return 1;
    }
}


