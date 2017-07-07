package virtualMachine;

/**
 * Created by tianhe on 2017/6/15.
 */
//-Xss2M
public class JavaVMStackOOM {
    private void loop() {
        while (true) {

        }
    }

    void runNotStop() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    loop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM a = new JavaVMStackOOM();
        a.runNotStop();
    }
}
