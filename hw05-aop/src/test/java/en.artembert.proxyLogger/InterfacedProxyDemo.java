package en.artembert.proxyLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfacedProxyDemo {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    public static void main(String[] args) {
        CalculatorInterface instance = Ioc.createWrappedClass();
        logger.info(String.valueOf(instance.sum(1, 2)));
        logger.info(String.valueOf(instance.sum(1.000000, 2.0000001)));
        logger.info(String.valueOf(instance.sum(1_000_000_000, 1_000_000_000)));
    }
}