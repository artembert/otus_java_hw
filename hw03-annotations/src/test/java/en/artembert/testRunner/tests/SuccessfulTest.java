package en.artembert.testRunner.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import en.artembert.testRunner.annotations.After;
import en.artembert.testRunner.annotations.Before;
import en.artembert.testRunner.annotations.Test;

public class SuccessfulTest {
    private int globalVariable = 0;

    @Before
    public void setUp() {
        globalVariable = 5;
    }

    @Test
    public void equalToConstantTest() {
        int newValue = globalVariable + 1 - 1;
        assertEquals(newValue, 5);
    }

    @Test
    public void simpleSumConstantTest() {
        assertEquals(2, 1 + 1);
    }

    @After
    public void tearDown() {
        assertTrue(true);
    }
}
