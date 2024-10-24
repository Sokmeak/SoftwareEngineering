import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PrimeCounterThread extends Thread {
    private int start, end, index;

    private List<Integer> primeNumbers;

    PrimeCounterThread(int start, int end, int ind) {
        this.start = start;
        this.end = end;
        this.index = ind;

        this.primeNumbers = new ArrayList<>();
    }

    List<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public void run() {

        for (int i = start; i <= end ; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
                System.out.println("t" + index + "-" + i);
            }

        }
    }

    private boolean isPrime(int num) {
        // inline condition
        if (num <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

}

public class PrimeNumberThread {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start, end, range, threadCount;
        System.out.print("Input start: ");
        start = scanner.nextInt();
        System.out.print("Input end: ");
        end = scanner.nextInt();

        // Calculate range, threadCount

        range = end - start + 1;
        // The ceil function returns the smallest integer value which is greater than or
        // equal to the specified number[10.5] = 11
        threadCount = (int) Math.ceil(range / 100.0);

        System.out.println("Running " + threadCount + " theads.");

        // construct the start threads

        List<PrimeCounterThread> threads = new ArrayList<>();

        for (int k = 0; k < threadCount; k++) {
            int threadStart = start + (k * 100);
            int threadEnd = Math.min(threadStart + 99, end);

            PrimeCounterThread pct = new PrimeCounterThread(threadStart, threadEnd, k);

            threads.add(pct);
            pct.start();
            
            try {
                pct.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        int totalPrime = 0;
        for (PrimeCounterThread th : threads) {

           
            totalPrime += th.getPrimeNumbers().size();

        }
        System.out.println("Sum of Primes = " + totalPrime);

        scanner.close();

    }

}
