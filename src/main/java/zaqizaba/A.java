package zaqizaba;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * Created by tianhe on 2017/7/28.
 */

class B{
    int i;

}
public class A {
    static  B  testA(B b){
        try{
            b.i=9;
            return b;
        }finally {
            System.out.println("test");
            b.i=10;
        }
    }

    public static void main(String[] args) {
//        B b = testA(new B());
//        System.out.println(b.i);
        BigDecimal b = BigDecimal.valueOf(1497).divide(BigDecimal.valueOf(408720),8,BigDecimal.ROUND_HALF_UP);
        System.out.println(b);

    }

}
