package virtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhe on 2017/6/15.
 */
//-XX:PermSize=1M -XX:MaxPermSize=1M
//        Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=1M; support was removed in 8.0
//        Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=1M; support was removed in 8.0
public class RunTimeConstantPollOOm {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
