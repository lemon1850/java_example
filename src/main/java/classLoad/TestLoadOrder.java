package classLoad;

/**
 * Created by tianhe on 2017/6/17.
 */
//被动引用
class Father{
    public static int value = 0;
    public final static int VALUE = 0;

    static {
        System.out.println("father init");
    }
}
class Son extends Father{
    static {
        System.out.println("son init");
    }
}
public class TestLoadOrder {
    public static void main(String[] args) {
//        System.out.println(Son.value);
//        Son[] sons = new Son[10];
        //final static没有触发类初始化

        System.out.println(Father.VALUE);
    }
}
