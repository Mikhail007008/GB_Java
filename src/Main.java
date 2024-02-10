import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Set<Laptop> laptops = new HashSet<>();

        laptops.add(new Laptop("acer", 4, 256, "windows", "black"));
        laptops.add(new Laptop("hp", 16, 512, "windows", "black"));
        laptops.add(new Laptop("msi", 32, 1024, "windows", "red"));
        laptops.add(new Laptop("dell", 16, 256, "linux", "silver"));
        laptops.add(new Laptop("macbook", 8, 512, "macos", "silver"));
        laptops.add(new Laptop("lenovo", 16, 1024, "windows", "blue"));
        laptops.add(new Laptop("asus", 8, 256, "windows", "white"));

        map.put(1, "ОЗУ");
        map.put(2, "Объём ЖД");
        map.put(3, "Операционная система");
        map.put(4, "Цвет");
        map.put(5, "Поиск");

        Map<String, String> newMap = userInput(map);
        int ram = getIntValue(newMap.get("ОЗУ"));
        int hd = getIntValue(newMap.get("Объём ЖД"));
        printSet(filter(laptops, ram, hd, newMap.get("Операционная система"), newMap.get("Цвет")));
    }

    static Map<String, String> userInput(Map<Integer, String> map) {
        Map<String, String> newMap = new HashMap<>();
        boolean isTrue = true;
        int num;
        String word;
        Scanner sc = new Scanner(System.in);

        while (isTrue) {
            outMap(map);
            System.out.print("Выберите критерии фильтрации ноутбуков: ");
            try {
                num = Integer.parseInt(sc.next());
                switch (num) {
                    case 1:
                        System.out.println("Введите минимальное значение ОЗУ, в гигабайтах: ");
                        word = sc.next();
                        numCheck(map, newMap, num, word);
                        break;
                    case 2:
                        System.out.println("Введите минимальный объем ЖД, в гигабайтах: ");
                        word = sc.next();
                        numCheck(map, newMap, num, word);
                        break;
                    case 3:
                        System.out.println("Введите желаемую операционную систему(windows, linux, macos): ");
                        word = sc.next();
                        newMap.put(map.get(num), word);
                        break;
                    case 4:
                        System.out.println("Выберите цвет(white, black, silver, red, blue): ");
                        word = sc.next();
                        newMap.put(map.get(num), word);
                        break;
                    case 5:
                        isTrue = false;
                        break;
                    default:
                        System.out.println("\nТакого пункта нет, попробуйте еще раз\n");
                        break;
                }
                if (num == 5 && newMap.isEmpty()) {
                    System.out.println("\nВы ничего не ввели, нужно сделать выбор\n");
                    isTrue = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nНеверный ввод!\n");
            }
        }
        sc.close();
        return newMap;
    }

    static void outMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> el : map.entrySet()) {
            Integer key = el.getKey();
            String value = el.getValue();
            System.out.println(key + ". " + value);
        }
    }

    static boolean isNum(String str) {
        return str.matches("\\d+");
    }

    static void numCheck(Map<Integer, String> map, Map<String, String> newMap, int num, String word) {
        if (isNum(word)) {
            newMap.put(map.get(num), word);
        } else {
            System.out.println("\nВы ввели некорректные значения, попробуйте заново\n");
        }
    }

    static int getIntValue(String value) {
        if (value != null && !value.isEmpty()) {
            return Integer.parseInt(value);
        } else return 0;
    }

    static Set<Laptop> filter(Set<Laptop> laptops, int ram, int hd, String os, String color) {
        Set<Laptop> set = new HashSet<>();

        for (Laptop laptop : laptops) {
            if ((ram <= 0 || laptop.ram >= ram) &&
                (hd <= 0 || laptop.hardDrive >= hd) &&
                (os == null || os.isEmpty() || laptop.os.equals(os)) &&
                (color == null || color.isEmpty() || laptop.color.equals(color))) {
                set.add(laptop);
            }
        }
        return set;
    }

    static void printSet(Set<Laptop> set) {
        if (set.isEmpty()) {
            System.out.println("Нет подходящих ноутбуков");
        } else {
            for (Laptop laptop : set) {
                System.out.println(laptop);
            }
        }
    }
}

