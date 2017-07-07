package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/7/7.
 */
public class SleepUtils {
    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            System.out.println("我被中断了" +
                    "");
        }
    }
}
