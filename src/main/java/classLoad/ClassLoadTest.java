package classLoad;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.ExportException;

/**
 * Created by tianhe on 2017/6/17.
 */
public class ClassLoadTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {

                    String fileName = name.indexOf(name.lastIndexOf('.') + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read();
                    defineClass(name, b, 0, b.length);
                    return super.loadClass(name);
                } catch (Exception e) {
                    System.out.println("fff");
                    throw new ClassNotFoundException();
                }
            }

        };
        Object Clazz1 = classLoader.loadClass("classLoad.ClassLoadTest");
        System.out.println(Clazz1.getClass());
        System.out.println(Clazz1 instanceof classLoad.ClassLoadTest);

    }
}