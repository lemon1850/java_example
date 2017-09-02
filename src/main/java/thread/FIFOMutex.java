package thread;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianhe on 2017/7/8.
 */
public class FIFOMutex {



    class ReorderExample {
        int a = 0;
        boolean flag = false;
        public void writer() {
            a = 1;          //1
            flag = true;    //2
        }
        public void reader() {
            if (flag) {     //3
                int i = a * a; //4
            }
        }
    }
    private   final AtomicBoolean lock = new AtomicBoolean(false);
    private   final Queue<Thread>  waiters = new ConcurrentLinkedQueue<>();

    void lock(){

        boolean isInterrupt = false;
        Thread current = Thread.currentThread();
        waiters.add(current);


        while(waiters.peek()!=current || !lock.compareAndSet(false, true)){
            LockSupport.park(lock);
            if(Thread.interrupted()){
                isInterrupt=true;
            }
        }
        waiters.remove();
        if(isInterrupt){
            current.interrupt();
        }
    }

    void unlock(){
        lock.set(false);

        LockSupport.unpark(waiters.peek());
    }

    public static void main(String[] args) {
        FIFOMutex fifoMutex = new FIFOMutex();
         new Thread(new Runnable() {
            @Override
            public void run() {
                fifoMutex.lock();
                System.out.println("我来抢锁");
                SleepUtils.second(2);
                fifoMutex.unlock();
            }
        }, "Thread First").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                fifoMutex.lock();
                System.out.println("我是第二个抢锁");
                SleepUtils.second(5);
                fifoMutex.unlock();
            }
        }, "Thread Second").start();
    }
}
