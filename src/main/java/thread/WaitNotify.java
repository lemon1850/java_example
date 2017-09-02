package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/7/7.
 */
public class WaitNotify {
    static boolean flag = true;
    static  Object object = new Object();

    public static void main(String[] args) {
        Thread a = new Thread(new Wait(), "Wait");
        a.start();
        Thread b = new Thread(new Notify(), "Notify");
        b.start();
//        a.interrupt();
    }
    static class Notify implements  Runnable {
        @Override
        public void run(){
            synchronized (object){
                System.out.println("Notify得到线程");
                flag = false;
                object.notify();
                System.out.println("我已经notify了");
                SleepUtils.second(5);
                System.out.println("notify之后过了2秒");
            }
            synchronized (object){
                System.out.println("Notify继续成功抢到锁");
                SleepUtils.second(1);
            }

        }
    }
    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (object){
                while(flag){
                    System.out.println("wait线程拿到锁了");
                    try{
                        object.wait(1);
                    }catch (InterruptedException e){
                        SleepUtils.second(100);
                        System.out.println(e);
                        System.out.println("我继续干活");
                    }

                    System.out.println("我终于又回来了");
                }
            }
            System.out.println("我终于等到flag变成" + flag);

        }
    }
}
