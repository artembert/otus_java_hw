package en.artembert.testRunner;

import en.artembert.testRunner.annotations.After;
import en.artembert.testRunner.annotations.Before;
import en.artembert.testRunner.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingClassBlueprint implements ITestingClassBlueprint {
    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();

    TestingClassBlueprint(Class<?> testingClass) {
        if (testingClass == null) {
            throw new IllegalArgumentException("Class is null");
        }
        Method[] declaredMethods = testingClass.getDeclaredMethods();
        if (declaredMethods.length == 0) {
            throw new IllegalArgumentException("No methods in class");
        }
        categorizeMethods(declaredMethods);
    }

    public void addBeforeMethod(Method method) {
        beforeMethods.add(method);
    }

    public void addTestMethod(Method method) {
        testMethods.add(method);
    }

    public void addAfterMethod(Method method) {
        afterMethods.add(method);
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    private void categorizeMethods(Method[] declaredMethods) {
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Before.class)) {
                addBeforeMethod(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                addTestMethod(method);
            } else if (method.isAnnotationPresent(After.class)) {
                addAfterMethod(method);
            }
        }
    }
}
