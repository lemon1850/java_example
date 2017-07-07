package thread;

/**
 * Created by tianhe on 2017/7/7.
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "Damon thread");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements  Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("dameonthread finall run.");
            }
        }
    }
}
