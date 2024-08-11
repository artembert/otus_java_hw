package en.artembert.proxyLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfacedProxyDemo {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    public static void main(String[] args) {
        CalculatorLogInterface instance = Ioc.createWrappedClass(new CalculatorImpl());
        logger.info(String.valueOf(instance.sum(1, 2)));
        logger.info(String.valueOf(instance.sum(1.000000, 2.0000001)));
        logger.info(String.valueOf(instance.sum(1_000_000_000, 1_000_000_000)));

        CalculatorLogInterface computerMachine = Ioc.createWrappedClass(new ComputerMachineImp());
        logger.info(String.valueOf(computerMachine.sum(1, 2)));
        logger.info(String.valueOf(computerMachine.sum(1.000000, 2.0000001)));
        logger.info(String.valueOf(computerMachine.sum(1_000_000_000, 1_000_000_000)));
    }
}