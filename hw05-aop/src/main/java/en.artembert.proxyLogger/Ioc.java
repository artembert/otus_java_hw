package en.artembert.proxyLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new LoggerInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class LoggerInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;

        LoggerInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            logger.info("[{}] invoking method:{}", new Date().toInstant(), method);
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "LoggerInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
