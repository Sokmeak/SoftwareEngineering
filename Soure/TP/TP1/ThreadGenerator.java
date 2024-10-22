import java.util.Scanner;

class DemoThread extends Thread {
    private int index;

    public DemoThread(int ind) {
        this.index = ind;
    }

    public void run() {
        System.out.println("Thread index: " + index);
    }

}

public class ThreadGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numThread;

        while (true) {

            System.out.print("Input the number of threads to create: ");

            numThread = scanner.nextInt();
            if (numThread <= 0) {
                break;
            }
            Thread[] threads = new Thread[numThread];

            for (int i = 0; i < numThread; i++) {
                threads[i] = new DemoThread(i);
                threads[i].start();
                try {
                    threads[i].join();

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

        }

        scanner.close();

        System.out.println("All Threads had dead!");

    }

}
