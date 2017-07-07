package gc;

/**
 * Created by tianhe on 2017/6/15.
 */
//-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1   or 16
public class testTenuringThreshold {
    private static  int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] all1, all2, all3;
        all1 = new byte[_1MB/4];
        all2 = new byte[4*_1MB];
        all3 = new byte[4*_1MB];
        all3 = null;
        all3 = new byte[4*_1MB];
    }
}
