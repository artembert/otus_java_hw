package en.artembert.proxyLogger;

import en.artembert.proxyLogger.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {
    }

    static CalculatorLogInterface createWrappedClass() {
        InvocationHandler handler = new LoggerInvocationHandler(new CalculatorImpl());
        return (CalculatorLogInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{CalculatorLogInterface.class}, handler);
    }

    static class LoggerInvocationHandler implements InvocationHandler {
        private final CalculatorLogInterface wrappedClass;

        LoggerInvocationHandler(CalculatorLogInterface wrappedClass) {
            this.wrappedClass = wrappedClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method implMethod = wrappedClass.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (implMethod.isAnnotationPresent(Log.class)) {
                logger.info("[{}] invoking method:{}", Instant.now(), implMethod);
            }
            return implMethod.invoke(wrappedClass, args);
        }

        @Override
        public String toString() {
            return "LoggerInvocationHandler{" + "wrappedClass=" + wrappedClass + "}";
        }
    }
}
