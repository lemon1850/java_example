package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/7/7.
 */
public class Interrupted
{
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        SleepUtils.second(1);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println(sleepThread.isInterrupted());
        System.out.println(busyThread.isInterrupted());
        System.out.println(sleepThread.isInterrupted());
        System.out.println(busyThread.isInterrupted());
        SleepUtils.second(1);
        Thread.interrupted();

//        busyThread.join();

    }
    static class SleepRunner implements  Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(10);
            }
        }
    }
    static class BusyRunner implements  Runnable{
        @Override
        public void run() {
            while (true){
            }
        }
    }
}
