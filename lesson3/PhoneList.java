package lesson3;

import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneList {

    private TreeMap<String, TreeSet<String>> phoneList = new TreeMap<>();

    public void add(String name, String phone) {
        if (!phoneList.containsKey(name)) {
            phoneList.put(name, new TreeSet<String>());
        }
        phoneList.get(name).add(phone);
    }


    public String get(String name) {

        if (phoneList.containsKey(name)) {
            return name + ": " + phoneList.get(name).toString();
        } else {
            return name + ": телефон отсутствует";
        }
    }
}
