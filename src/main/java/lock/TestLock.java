package lock;

import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tianhe on 2017/7/21.
 */
public class TestLock {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        threadLocal.set(99);
        System.out.println(threadLocal.get());
        System.out.println(System.getProperty("user.dir"));

    }
}
