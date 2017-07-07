package javaclass;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/6/16.
 */
public class TestClass {
    private int m;
    public int inc(){
        return m+1;
    }
    public int c(){
        int x;
        try{
            x = 1;
            return x;
        }catch (Exception e){
            x = 2;
            return  x;
        }finally {
            x = 3;

        }
    }
}
