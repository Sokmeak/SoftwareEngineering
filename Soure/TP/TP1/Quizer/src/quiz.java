import java.util.Random;
import java.util.Scanner;

public class quiz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int correctCount = 0;
        int wrongCount = 0;
        final int maxQuestions = 10;

        for (int i = 0; i < maxQuestions; i++) {
            int A = random.nextInt(9) + 1; // A is between 1 and 9
            int B = random.nextInt(9) + 1; // B is between 1 and 9
            int correctAnswer = A + B;

            System.out.println(A + " + " + B + " = ?");
            
            long startTime = System.currentTimeMillis();
            System.out.print("Your answer (You have 2 seconds): ");
            
            // Check for input within 2 seconds
            String userInput = "";
            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            }

            long timeTaken = System.currentTimeMillis() - startTime;
            if (timeTaken > 5000) {
                // System.out.println("Time's up! You missed the input.");
                continue;
            }

            try {
                int userAnswer = Integer.parseInt(userInput);
                if (userAnswer == correctAnswer) {
                    correctCount++;
                    System.out.println("Correct!");
                } else {
                    wrongCount++;
                    System.out.println("Wrong!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                wrongCount++;
            }
        }

        // Display the result
        System.out.println("\nResult: correct= " + correctCount + " and wrong= " + wrongCount);
        
        // Provide feedback based on correct answers
        if (correctCount == maxQuestions) {
            System.out.println("Master brain!");
        } else if (correctCount >= maxQuestions / 2) {
            System.out.println("Good job!");
        } else {
            System.out.println("Baby brain!");
        }

        scanner.close();
    }
}
