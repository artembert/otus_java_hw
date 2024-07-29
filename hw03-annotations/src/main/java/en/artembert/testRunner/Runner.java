package en.artembert.testRunner;

import en.artembert.testRunner.reflection.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(TestResult.class);

    public static void run(Class<?> testingClass) {
        Runner runner = new Runner();
        TestResult result = runner.runTests(testingClass);
        printTestResult(result);
    }

    private static void printTestResult(TestResult testResult) {
        logger.info("Total: {}", testResult.getTotal());
        logger.info("Successful: {}", testResult.getSuccessful());
        logger.info("Failed: {}", testResult.getFailed());
    }

    private TestResult runTests(Class<?> testingClass) {
        TestResult testResult = new TestResult();
        ITestingClassBlueprint blueprint = new TestingClassBlueprint(testingClass);
        for (Method testMethod : blueprint.getTestMethods()) {
            try {
                Object object = ReflectionHelper.instantiate(testingClass);
                ReflectionHelper.callMethods(object, blueprint.getBeforeMethods());
                ReflectionHelper.callMethod(object, testMethod.getName());
                ReflectionHelper.callMethods(object, blueprint.getAfterMethods());
                testResult.incrementSuccessful();
            } catch (Exception e) {
                logger.error("Test '{}' failed. Exception: {}", testMethod.getName(), e.toString());
                testResult.incrementFailed();
            }
        }
        return testResult;
    }
}
