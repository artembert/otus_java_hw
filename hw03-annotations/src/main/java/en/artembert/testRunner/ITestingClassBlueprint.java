package en.artembert.testRunner;

import java.lang.reflect.Method;
import java.util.List;

public interface ITestingClassBlueprint {
    List<Method> getBeforeMethods();

    List<Method> getTestMethods();

    List<Method> getAfterMethods();
}
