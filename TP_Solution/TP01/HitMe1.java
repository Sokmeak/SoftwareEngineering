import java.util.Scanner;

class MyThread extends Thread {

    private boolean isRunning;

    
    public void runMyThread() {
        isRunning = true;
    }

    public void stopThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("HItMe!");

        }
    }

}

class HitMe1 {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();
        myThread.runMyThread();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.stopThread();
        System.out.println("\n Thank you!");
        scanner.close();

    }
}