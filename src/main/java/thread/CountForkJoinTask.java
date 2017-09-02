package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by tianhe on 2017/8/2.
 */
public class CountForkJoinTask extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountForkJoinTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end-start)<= THRESHOLD;
        if(canCompute){
            for(int i =start; i <=end; i++){
                sum+=start;
            }
        }else {
            int middle = (start + end)/2;
            CountForkJoinTask leftTask = new CountForkJoinTask(start, middle);
            CountForkJoinTask rightTask = new CountForkJoinTask(middle+1, end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rigResult = rightTask.join();
            sum = leftResult + rigResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountForkJoinTask task = new CountForkJoinTask(1,1000);
        Future<Integer> result = forkJoinPool.submit(task);
        try{
            System.out.println(result.get());
        }catch (Exception e ){

        }
    }
}
