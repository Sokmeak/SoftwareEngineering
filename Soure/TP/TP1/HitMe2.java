
import java.util.Scanner;

class MyThread2 extends Thread {

    private boolean isRunning;
    private String currentText; // Text to display
    private final Scanner input;

    public MyThread2() {
        this.isRunning = true;
        this.currentText = "HitMe!"; // Start with "HitMe!"
        this.input = new Scanner(System.in);
    }

    public void stopThread() {
        this.isRunning = false;
    }

    public void run() {
        while (isRunning) {
            try {
                // Continuously print the current text
                System.out.print(currentText);

                // Sleep for a short duration to control print speed
                Thread.sleep(100);

                // Check if there's user input
                if (System.in.available() > 0) {
                    String text = input.nextLine();
                    if (text.isEmpty()) {
                        currentText = "HitMe!"; // Revert back to "HitMe!"
                    } else {
                        currentText = text; // Set currentText to user input

                        // Stop the thread if the user types "exit"
                        if (text.equalsIgnoreCase("exit")) {
                            stopThread();
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class HitMe2 {
    public static void main(String[] args) {

        MyThread2 myThread = new MyThread2();
        myThread.start();

        // Main thread could handle additional tasks if needed
    }
}
