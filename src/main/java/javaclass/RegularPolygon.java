package javaclass;

/**
 * Created by tianhe on 2017/7/7.
 */
class Shape{
    private int i;
    private int j;

    public Shape(int i, int j) {
        this.i = i;
        this.j = j;
    }
    @Override
    public String toString() {
        return "Shape{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
public enum RegularPolygon {
//
//    TRIANGLE(3){
//        public void a(){
//            System.out.println("aaaaaaaaaaa");
//        }
//    },
//    SQUARE(4){
//        public void a(){
//            System.out.println("bbbbbbbbbbbbbbbbb");
//        }
//    },
    PENTAGON(5){
        public void a(){
            System.out.println("cccccccccccccc");
        }
    };

    public abstract void  a();
    private Shape shape;

    public Shape getShape(){
        return  shape;
    }

    private RegularPolygon(int  sides) {
        switch (sides){
//            case 3: shape =  new Shape(3,3);break;
//            case 4: shape =  new Shape(4,4);break;
            case 5: shape =  new Shape(5,5);break;
        }
    }
}
