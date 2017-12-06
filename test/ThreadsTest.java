
import static org.junit.Assert.*;

import threads.IntegralCalculator;
import threads.MyFunction;
import junit.framework.TestCase;
import org.junit.Test;

public class ThreadsTest {

    @Test
    public void functionTest() {
        double x = -1;
        double exp = .3;
        double res = MyFunction.f(x);
        double d = .01;
        assertEquals(exp, res, d);
    }

    @Test
    public void calculatorTest() throws Exception {
        double a = -1, b = 0;
        int n = 10;
        IntegralCalculator calculator = new IntegralCalculator(a, b, n, MyFunction::f);
        double exp = 0.3;
        double res = calculator.calculate();
        double d = .015;
        assertEquals(exp,res,d);
    }
}