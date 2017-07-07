package virtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhe on 2017/6/15.
 */
//-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
