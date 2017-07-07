package gc;

/**
 * Created by tianhe on 2017/6/15.
 */
//-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
public class TestPretenureSizeThreshold {
        private static  int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allo;
        allo = new byte[4*_1MB];
    }
}
