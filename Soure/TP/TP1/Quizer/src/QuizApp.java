import java.util.Random;
import java.util.Scanner;

public class QuizApp {
    private static boolean userInput = false;
    private static boolean timeExpired = false;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int correctAnswerCount = 0;
        int wrong = 0;
        int maxQuestions = 10;

        for (int i = 0; i < maxQuestions; i++) {
            int a = random.nextInt(9) + 1; // random value a / b from 1 to 9
            int b = random.nextInt(9) + 1;

            int correctAnswer = a + b;

            System.out.println(a + " + " + b + " = ?");

            userInput = false;
            timeExpired = false;

            Thread timer = new Thread(() -> {
                try {
                    Thread.sleep(3000); // sleep for 3 seconds
                    if (!userInput ) {
                        timeExpired = true;
                        System.out.println("Time is up! You missed the input!");
                    }
                } catch (InterruptedException e) {
                    // Thread interrupted, meaning user provided input
                }
            });

            timer.start();

            System.out.print("Your answer (you have 3 seconds): ");

            if (timeExpired) {
                // If the time expired, move to the next question
                System.out.println("time expired!");
                wrong++;
                continue;
            }
            String input = scanner.nextLine();

            userInput = true;
            timer.interrupt(); // Stop the timer if user input is received

            

            try {
                int userAnswer = Integer.parseInt(input);
                if (userAnswer == correctAnswer) {
                    correctAnswerCount++;
                    System.out.println("Correct!");
                } else {
                    wrong++;
                    System.out.println("Wrong!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please input a number.");
                wrong++;
            }
        }

        // Display the result
        System.out.println("Correct = " + correctAnswerCount);
        System.out.println("Wrong = " + wrong);

        if (correctAnswerCount == maxQuestions) {
            System.out.println("Master brain!");
        } else if (correctAnswerCount >= maxQuestions / 2) {
            System.out.println("Good job!");
        } else {
            System.out.println("Baby brain!");
        }

        scanner.close();
    }
}
