package en.artembert.proxyLogger;

import en.artembert.proxyLogger.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    static CalculatorLogInterface createWrappedClass(CalculatorLogInterface implementation) {
        InvocationHandler handler = new LoggerInvocationHandler(implementation);
        return (CalculatorLogInterface) Proxy.newProxyInstance(
                Ioc.class.getClassLoader(), new Class<?>[] {CalculatorLogInterface.class}, handler);
    }

    static class LoggerInvocationHandler implements InvocationHandler {
        private final CalculatorLogInterface wrappedClass;
        private final Map<Method, Boolean> methodAnnotationCache = new HashMap<>();

        LoggerInvocationHandler(CalculatorLogInterface wrappedClass) {
            this.wrappedClass = wrappedClass;
            cacheMethodAnnotations();
        }

        private void cacheMethodAnnotations() {
            for (Method method : wrappedClass.getClass().getMethods()) {
                if (method.isAnnotationPresent(Log.class)) {
                    methodAnnotationCache.put(method, true);
                } else {
                    methodAnnotationCache.put(method, false);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method implMethod = wrappedClass.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (methodAnnotationCache.getOrDefault(implMethod, false)) {
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
