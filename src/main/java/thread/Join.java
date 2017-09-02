package thread;

/**
 * Created by tianhe on 2017/7/24.
 */
public class Join {
    public static void main(String[] args) {
        Thread previout = Thread.currentThread();
        for(int i =0; i<10; i++){
            Thread thread = new Thread(new Test(previout), String.valueOf(i));
            thread.start();
            previout = thread;
        }
    }


    static class Test implements Runnable{
        private Thread thread;

        public Test(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try{
                thread.join();
            }catch (InterruptedException e){

            }
            System.out.println(Thread.currentThread().getName() + "  terminate.");
        }
    }
}
