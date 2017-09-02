package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by tianhe on 2017/7/8.
 */
public class Mutex {
    private static  class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());

                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        Condition newCOndition(){
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();
    public void lock(){
        sync.acquire(1);
    }
    public boolean trylock(){
        return sync.tryAcquire(1);
    }
    public void unlock(){
        sync.release(1);
    }
    public Condition newCondition(){
        return sync.newCOndition();
    }
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
    public boolean hasQueueThreads(){
        return sync.hasQueuedThreads();
    }
    public void lockInterruptibly() throws InterruptedException{
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
