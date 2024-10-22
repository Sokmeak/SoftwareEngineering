import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String question;
    List<String> options;
    int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

// Quiz process that handles questions, time, and results
class QuizProcess {
    private List<Question> questions;
    private int timeLimitPerQuestion; // in seconds
    private int score;
    private int mistakes;

    public QuizProcess(List<Question> questions, int timeLimitPerQuestion) {
        this.questions = questions;
        this.timeLimitPerQuestion = timeLimitPerQuestion;
        this.score = 0;
        this.mistakes = 0;
    }

    class Countdown implements Runnable {
        private int timeLimit;
        private volatile boolean timeUp;

        public Countdown(int timeLimit) {
            this.timeLimit = timeLimit;
            this.timeUp = false;
        }

        public boolean isTimeUp() {
            return timeUp;
        }

        @Override
        public void run() {
            try {
                for (int i = timeLimit; i > 0; i--) {
                    System.out.print("\rTime left: " + i + " seconds : ");
                    Thread.sleep(1000);
                }
                timeUp = true;
                System.out.println("\nTime's up!");
            } catch (InterruptedException e) {
                System.out.println("\nTimer interrupted.");
                // Properly stop when interrupted
                timeUp = true;
            }
        }
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        // Loop through all questions
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.question);
            for (int j = 0; j < question.options.size(); j++) {
                System.out.println((j + 1) + ". " + question.options.get(j));
            }

            // Start a timer thread
            Countdown countdown = new Countdown(timeLimitPerQuestion);
            Thread timerThread = new Thread(countdown);
            timerThread.start();

            // Capture user input in the main thread
            int answer = -1;
            boolean answered = false;

            // Wait for the user's input or until the time runs out
            while (timerThread.isAlive()) {
                if (scanner.hasNextInt() || countdown.isTimeUp()) {
                    answer = scanner.nextInt();
                    answered = true;
                    timerThread.interrupt(); // Stop the timer if user answers
                    break;
                }
                // Check if time is up, automatically move to next question
                if (countdown.isTimeUp()) {
                    break;
                }
            }

            // // Ensure the timer thread finishes before continuing
            // try {
            //     timerThread.join();
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            // Check the result
            if (!answered && countdown.isTimeUp()) {
                System.out.println("\nYou didn't answer in time.");
                mistakes++;
            } else if (answer == question.correctOption) {
                score++;
                System.out.println("Correct!");
            } else if (answered) {
                mistakes++;
                System.out.println("Wrong answer.");
            }
        }

        // scanner.close();
    }

    public int getScore() {
        return score;
    }

    public int getMistakes() {
        return mistakes;
    }
}

// Main class to run the quiz
public class quizApp {
    public static void main(String[] args) throws Exception {
        // Sample questions
        List<Question> questions = new ArrayList<>();
        List<String> options1 = new ArrayList<>();
        options1.add("2");
        options1.add("3");
        options1.add("4");
        options1.add("5");
        questions.add(new Question("What is 2 + 2?", options1, 3));

        List<String> options2 = new ArrayList<>();
        options2.add("Earth");
        options2.add("Mars");
        options2.add("Venus");
        options2.add("Jupiter");
        questions.add(new Question("Which planet is known as the Red Planet?", options2, 2));

        // Add more questions as needed...

        // Time limit per question (5 seconds for this example)
        int timeLimitPerQuestion = 5;

        // Create and start the quiz process
        QuizProcess quiz = new QuizProcess(questions, timeLimitPerQuestion);
        quiz.startQuiz();

        // Display the results
        System.out.println("\n ==== Quiz over ==== ");
        System.out.println("Score: " + quiz.getScore());
        System.out.println("Mistakes: " + quiz.getMistakes());
    }
}
