
import java.util.Random;
import java.util.Scanner;

public class quizApp {
    private  static boolean  userInput = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();

        int correctAnswerCount= 0;
        int wrong = 0;

        int maxQuestions = 10;


        for (int i  = 0; i < maxQuestions ; i++){
            int a = random.nextInt(9) + 1; // random value a / b from 1 to 9
            int b = random.nextInt(9)+1;

            int correctAnswer = a + b;

            System.out.println(a+" + "+b +" = ?" );

            userInput  = false;

            Thread timer = new Thread(() ->{
                
                try {
                    Thread.sleep(5000); // sleep for 5 seconds
                    if(!userInput){
                        System.out.println("Time is up! You missed the input!");
                        System.exit(0);
                    }
                    }catch(InterruptedException e){

                        // e.printStackTrace();



                    }   
                    
                
            } );   

            timer.start();


            System.out.print("Your answer (you have 5 seconds) : ");
            String input  = scanner.nextLine();

            userInput = true;

            timer.interrupt();

            try {
                int userAnswer = Integer.parseInt(input);
                if(userAnswer == correctAnswer){
                    correctAnswerCount++;

                    System.out.println("Correct!");
                }else{
                    wrong++;
                    System.out.println("Wrong!");
                }
                
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please input as a number");
                wrong++;
            }
        }


        // Display the result

        System.out.println("Correct = "+correctAnswerCount);
        System.out.println("Wrosng = "+wrong);
    

        if(correctAnswerCount == maxQuestions){
            System.out.println("Master brain!");
        }else if(correctAnswerCount >= maxQuestions/2){
            System.out.println("Good job!");
        }else{
            System.out.println("Baby brain!");
        }

        scanner.close();

         
                
    }

    
}
