package threads;

import javax.faces.bean.ManagedBean;

/**
 * Created by eugeny on 22.03.2016.
 */
@ManagedBean
public class ThreadBean {
   private double a = -1;
   private double b = 0;
   private int n = 100_000;
   private int totalThreads = 2;
   private long time;

  private double total = 0;
  private int runningThreads = 0;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getTotalThreads() {
        return totalThreads;
    }

    public void setTotalThreads(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //    public static void main(String[] args) {
//        new ConcBean().run();
//    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void run() {
        total = 0;

        long start = System.currentTimeMillis();

        double delta = (b-a)/totalThreads;
        runningThreads = totalThreads;
        for (int i = 0; i < totalThreads; i++) {
            new ThreadedCalculator(
                    new IntegralCalculator(a+i*delta, a+(i+1)*delta, n/totalThreads, MyFunction::f),
                    this
            ).start();
        }
//        IntegralCalculator calculator = new IntegralCalculator(a,b,n,MyFunction::f);
//        double result = calculator.calculate();

        try {
            synchronized (this) {
                while (runningThreads > 0) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();

      time = finish-start;
    }

    public synchronized void sendResult(double result) {
        total += result;
        runningThreads--;
        notify();
    }
}
