package thread;


/**
 * Created by tianhe on 2017/7/7.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){

        }
        m();
    }
    public static synchronized void m(){

    }
}
