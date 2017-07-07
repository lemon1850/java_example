package java8.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Created by tianhe on 2017/6/20.
 */
public class PredicateExample {
    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate){
        ArrayList<T> list1 = new ArrayList<T>();
        for(T t: list){
            if(predicate.test(t)){
                list1.add(t);
            }
        }
        return list1;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a","ab","c"));

        list = filterList(list, (String s) -> s.contains("a"));
        System.out.println(list);
        int portNumber = 1137;
        Runnable r = ()-> {int b=2121;int i=2; i=3;System.out.println(portNumber);};
        r.run();
    }
}
