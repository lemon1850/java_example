package java8.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tianhe on 2017/6/20.
 */
public class FlagMapExample {


    public static void main(String[] args) {
        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);
        List<int []> result = num1.stream().flatMap(i -> num2.stream().map(j-> new int[]{i,j})).collect(Collectors.toList());
        for(int[] a : result){
            System.out.println(a);


        }
    }
}
