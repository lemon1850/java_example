package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by tianhe on 2017/6/14.
 */
interface Service {
    void sendEmail();
    void feedCat();
}

class ServiceImpl implements Service {
    public void sendEmail() {
        System.out.println("send email");
    }
    public void feedCat() {
        System.out.println("feed cat");
    }
}

class ServiceHandler implements InvocationHandler {
    Service service;

    public ServiceHandler(Service service) {
        this.service = service;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if (name == "feedCat") {
            System.out.println("我才不要喂猫");
        }
        return method.invoke(service, args);
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        ServiceImpl s = new ServiceImpl();
        Service service = (Service)Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[]{Service.class}, new ServiceHandler(s));
        service.sendEmail();
        service.feedCat();
    }
}