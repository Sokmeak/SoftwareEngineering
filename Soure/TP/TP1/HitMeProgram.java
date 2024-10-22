import java.util.Scanner;

class HitMeThread extends Thread {

    private volatile boolean isRunning = true; // Ensure visibility across threads
    private volatile boolean userInputReceived = false;
    String t = "HitMe!";
    private volatile String textToPrint = ""; // Default text is "HitMe!"

    public void stopThread() {
        isRunning = false; // Method to stop the thread
    }

    @Override
    public void run() {
        while (isRunning) {
            // Continuously print the text (either "HitMe!" or user input)

            if (!userInputReceived) {
                System.out.println(t);
            } else {
                System.out.println(textToPrint+"=============");
            }
            try {
                Thread.sleep(100); // Add small delay for readability
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update text to print
    public void setTextToPrint(String text) {
        this.userInputReceived = true;
        this.textToPrint = text;
    }
}

public class HitMeProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HitMeThread hitMeThread = new HitMeThread();

        // Start the "HitMe!" printing thread
        hitMeThread.start();

        // Main thread to handle user input
        while (true) {
            String userInput = scanner.nextLine();

            hitMeThread.setTextToPrint(userInput);

            if (userInput.isEmpty()) {
                // Stop the program if user presses ENTER with no input

                hitMeThread.stopThread();
                break;
            } else {
               

             //   String repeatedText = userInput.repeat(50); // Repeat the input character multiple times
                hitMeThread.setTextToPrint(userInput);
            }
        }
        System.out.println("Thank you");
        scanner.close();
    }
}
