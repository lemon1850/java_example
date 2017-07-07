package invoke;

/**
 * Created by tianhe on 2017/6/17.
 */
//静态分派，编译器根据静态类型完成方法选择
public class InvokeStatic {

    abstract static  class Fruit{

    }
      static  class Apple extends Fruit{

    }
      static class Banana extends Fruit{

    }

     void eatFruit(Fruit f){
        System.out.println("eat fruit");
    }

     void eatFruit(Apple f){
        System.out.println("eat apple");
    }

     void eatFruit(Banana f){
        System.out.println("eat banana");
    }

    public static void main(String[] args) {
        InvokeStatic i = new InvokeStatic();
        Fruit a = new Apple();
        Fruit b = new Banana();
        i.eatFruit((Apple)a);
//        i.eatFruit(a);
//        i.eatFruit(b);

    }
}
