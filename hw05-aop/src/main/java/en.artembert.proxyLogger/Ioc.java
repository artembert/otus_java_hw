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

    static CalculatorInterface createWrappedClass() {
        InvocationHandler handler = new LoggerInvocationHandler(new CalculatorImpl());
        return (CalculatorInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{CalculatorInterface.class}, handler);
    }

    static class LoggerInvocationHandler implements InvocationHandler {
        private final CalculatorInterface wrappedClass;

        LoggerInvocationHandler(CalculatorInterface wrappedClass) {
            this.wrappedClass = wrappedClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            logger.info("[{}] invoking method:{}", new Date().toInstant(), method);
            return method.invoke(wrappedClass, args);
        }

        @Override
        public String toString() {
            return "LoggerInvocationHandler{" + "wrappedClass=" + wrappedClass + '}';
        }
    }
}
