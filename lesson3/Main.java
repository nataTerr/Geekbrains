package lesson3;

import java.util.HashSet;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        onlyWords();

        phoneList();
    }

    /*
    1 Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и
    вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    Посчитать, сколько раз встречается каждое слово.

    2 Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и
    телефонных номеров. В этот телефонный справочник с помощью метода add() можно
    добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует
    учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не добавлять
    лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем
    через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного
    справочника.
     */
    public static void onlyWords() {

        String[] words = {"Pen", "Mother", "Father", "Beer", "Mother", "Pen", "Beer", "Phone", "Phone", "Mother"};

        TreeMap<String, Integer> setWords = new TreeMap<>();

        for (int i = 0; i < words.length; i++) {

            if (setWords.containsKey(words[i])) {
                setWords.put(words[i], setWords.get(words[i]) + 1);
            } else {
                setWords.put(words[i], 1);
            }
        }
        System.out.println(setWords);
    }

    public static void phoneList() {

        PhoneList phoneList = new PhoneList();

        phoneList.add("Ivanov", "79232895115");
        phoneList.add("Ivanov", "79232895117");
        phoneList.add("Petrov", "79232820503");
        phoneList.add("Petrov", "79232820506");
        phoneList.add("Sidorov", "79232820502");

        System.out.println(phoneList.get("Ivanov"));
        System.out.println(phoneList.get("Petrov"));
        System.out.println(phoneList.get("Sidorov"));
        System.out.println(phoneList.get("Sid"));
    }
}
