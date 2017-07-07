package java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.ToIntFunction;

/**
 * Created by tianhe on 2017/6/20.
 */
public class ConsumerExample {
    public static <T> void forEach(List<T> list, Consumer<T> consumer){
        for(T t: list){
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        Consumer<Integer> consumer = (Integer i)-> {
            System.out.println(i);

        };
        forEach(Arrays.asList(1,2,3), consumer);
    }
}
