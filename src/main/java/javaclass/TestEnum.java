package javaclass;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/7/7.
 */
public class TestEnum {

    public static void main(String[] args) {
        RegularPolygon regularPolygon = RegularPolygon.PENTAGON;
//        Shape shape = regularPolygon.getShape();
//        System.out.println(shape);
        RegularPolygon.PENTAGON.a();

        new Thread().setPriority(Thread.MAX_PRIORITY);
    }
}
