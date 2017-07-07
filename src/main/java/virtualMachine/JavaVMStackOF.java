package virtualMachine;

/**
 * Created by tianhe on 2017/6/15.
 */
//-Xss180k
public class JavaVMStackOF {
     int stackLength = 1;
      void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackOF a = new JavaVMStackOF();
        try{
        a.stackLeak();

        }catch (Throwable t){
            System.out.println(a.stackLength);
            throw  t;
        }
    }
}
