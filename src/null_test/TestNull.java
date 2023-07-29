package null_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TestNull {
    // Проверка ссылок на null в функциональном и императивном стилях программирования
    public List<Integer> evenIntegers(List<Integer> integers){
        if (integers==null){
            return Collections.EMPTY_LIST;
        }
        List<Integer>evens=new ArrayList<>();
        for (Integer even : evens) {
            if (even!=null && even%2==0){
                evens.add(even);
            }
        }
        return evens;
    }

    public static List<Integer> evenIntegers1(List<Integer> integers) {
        if (Objects. isNull (integers)) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> evens = new ArrayList<>();
        for (Integer nr: integers) {
            if (Objects.nonNull (nr) && nr % 2 == 0) {
                evens.add(nr);
            }
        }
        return evens;
    }

    public static int sumIntegers(List<Integer> integers) {
        if (integers == null) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }
        return integers.stream()
                .filter(i -> i != null)
                .mapToInt(Integer::intValue).sum();
    }

    public static boolean integersContainsNulls(List<Integer> integers) {
        if (integers == null) {
            return false;
        }
        return integers.stream()
                .anyMatch (Objects::isNull);
    }


//    public int hashCode() {
//        int hash = 7;
//        hash = 79 * hash + this.id;
//        hash = 79 * hash + Objects.hashCode(this.name);
//        return hash;
//    }public int hashCode() {
//        int hash = 7;
//        hash = 79 * hash + this.id;
//        hash = 79 * hash + Objects.hashCode(this.name);
//        return hash;
//    }

}
