package thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianhe on 2017/7/24.
 */
 class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();
    public ConnectionPool(int intialSize){
        if(intialSize>0){
            for(int i=intialSize; i>0; i--){
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized (pool){
                pool.add(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws  InterruptedException{
        synchronized (pool){
            if(mills<=0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remain = mills;
                while(pool.isEmpty() && remain >0){
                    pool.wait(remain);
                    remain = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }

                return result;
            }
        }
    }
}

class ConnectionDriver{
    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionHandler());
    }
}

public class ConnnectionPoolTest{
     static ConnectionPool pool = new ConnectionPool(10);
     static CountDownLatch start = new CountDownLatch(1);
     static CountDownLatch end;

    public static void main(String[] args) throws  Exception{
        int threadCount = 40;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger(0);
        AtomicInteger notGot = new AtomicInteger(0);
        for(int i=0; i< threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot), "ConnectionRunnerThread: " + i);
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke" + (threadCount*count));
        System.out.println("got conneciton: " + got);
        System.out.println("not got conneciton: " + notGot);
    }


    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        static int out=0;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;

            this.notGot = notGot;
        }

        @Override
        public void run() {
            try{
                start.await();
            }catch (Exception e){

            }
            while(count>0){
                try{
                    Connection connection = pool.fetchConnection(10);
                    if(connection!=null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                        System.out.println("获取失败");
                    }
                }catch (Exception e){

                }finally {
                    count--;
                    out++;
                }

            }
            System.out.println(Thread.currentThread() + "got " + got);
            System.out.println(Thread.currentThread() +"notGot " + notGot);
            end.countDown();
        }
    }
}