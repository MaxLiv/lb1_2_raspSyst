package threads;

/**
 * Created by eugeny on 22.03.2016.
 */
public class ThreadedCalculator extends Thread{
    IntegralCalculator calculator;
    ThreadBean threadBean;

    public ThreadedCalculator(IntegralCalculator calculator, ThreadBean threadBean) {
        this.calculator = calculator;
        this.threadBean = threadBean;
    }

    public void run() {
        double result = calculator.calculate();
        threadBean.sendResult(result);
    }
}
