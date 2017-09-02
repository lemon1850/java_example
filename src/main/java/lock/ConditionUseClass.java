package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianhe on 2017/7/25.
 */
public class ConditionUseClass {
    public static void main(String[] args) throws InterruptedException{
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
    }
}
