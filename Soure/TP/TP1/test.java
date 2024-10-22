import java.util.Scanner;

class MyThread3 extends Thread {

    private boolean isRunning;
    private String currentText; // Text to display
    private final Scanner input;

    public MyThread3() {
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

                    // If user presses ENTER without typing, stop the thread
                    if (text.isEmpty()) {
                        stopThread(); // Stop the thread if ENTER is pressed
                    } else if (text.length() == 1) {
                        // If the user inputs one character, repeat that character
                        currentText = String.valueOf(text.charAt(0));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class test {
    public static void main(String[] args) {

        MyThread3 myThread = new MyThread3();
        myThread.start();

        // Main thread could handle additional tasks if needed
    }
}
