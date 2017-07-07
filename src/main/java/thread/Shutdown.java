package thread;

import javax.management.relation.RoleUnresolved;

/**
 * Created by tianhe on 2017/7/7.
 */
public class Shutdown {

    public static void main(String[] args) {
        Run run = new Run();
        Thread t = new Thread(run, "stop by interupt");
        t.start();
        SleepUtils.second(1);
//        t.interrupt();
        run.changeStop();
    }

    static  class Run implements  Runnable{
        private  volatile boolean stop = false;
        int i = 0;
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !stop){
                i++;
                System.out.println(i);
            }
        }
        public void changeStop(){
            stop = true;
        }
    }
}
