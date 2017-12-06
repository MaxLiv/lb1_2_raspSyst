package concurrent;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@ManagedBean
public class ConcBean {
   private double a = -1;
   private double b = 0;
   private int n = 100_000;
   private int nThreads = 2;
   private double sum = 0;
    private long time;

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

    public int getnThreads() {
        return nThreads;
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void run() {
        sum = 0;
        long start = System.currentTimeMillis();
        double delta = (b-a)/nThreads;
        ExecutorService es = Executors.newFixedThreadPool(10);
        List<Future<Double>> futures = new ArrayList<>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Future<Double> f = es.submit(new CallableCalc(a + i * delta, a + (i + 1) * delta, n / nThreads, MyFunction::f));
            futures.add(f);
        }
        es.shutdown();

        try {
            for (Future<Double> future : futures) {
                sum += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        time = finish - start;
    }
}
