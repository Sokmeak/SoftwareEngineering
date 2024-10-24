import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Random;

public class quizApp {
    private static boolean isRunning = true; // To stop the app when needed
    private static boolean userInput = false; // To track if input was received
    private static int wrong = 0; // Track wrong answers
    private static String inputText = ""; // Store the user input for the answer
    private static int correctAnswerCount = 0; // Track correct answers

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int maxQuestions = 10;

        // Register global key listener
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                // Collect key presses to form an answer
                String keyPressed = NativeKeyEvent.getKeyText(e.getKeyCode());
                if (keyPressed.matches("[0-9]")) {
                    inputText += keyPressed; // Append number to inputText
                    userInput = true; // Mark that input is received
                }
            }

           
        });

        // Loop through questions
        for (int i = 0; i < maxQuestions; i++) {
            inputText = ""; // Reset input text for each question
            userInput = false; // Reset input flag

            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            int correctAnswer = a + b;

            System.out.println(a + " + " + b + " = ? (You have 5 seconds)");

            // Start a timer
            Thread timer = new Thread(() -> {
                try {
                    Thread.sleep(3000); // Wait 3 seconds
                    if (!userInput) {
                        System.out.println("\nTime is up! Moving to the next question...");
                        wrong++; // Count as wrong if no input
                    } else {
                        // Evaluate input when user has provided it
                        try {
                            int userAnswer = Integer.parseInt(inputText);
                            if (userAnswer == correctAnswer) {
                                correctAnswerCount++;
                                System.out.println("\nCorrect!");
                            } else {
                                wrong++;
                                System.out.println("\nWrong!");
                            }
                        } catch (NumberFormatException e) {
                            wrong++;
                            System.out.println("\nInvalid input. Please enter a number.");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            timer.start();

            // Wait until either input is received or 5 seconds are up
            while (!userInput && timer.isAlive()) {
                Thread.sleep(100); // Small delay to reduce CPU usage
            }

            // Ensure the timer finishes
            timer.join();
            System.out.println(); // Print a new line for clarity
        }

        // After all questions, display results
        System.out.println("Correct = " + correctAnswerCount);
        System.out.println("Wrong = " + wrong);

        if (correctAnswerCount == maxQuestions) {
            System.out.println("Master brain!");
        } else if (correctAnswerCount >= maxQuestions / 2) {
            System.out.println("Good job!");
        } else {
            System.out.println("Baby brain!");
        }

        isRunning = false;

        GlobalScreen.unregisterNativeHook(); // Unregister the native hook
    }
}

