
import java.util.Scanner;
public class ThreadGenerator {

    public static void main(String[] args) throws Exception{
        int numThread;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of threads: ");
        numThread = scanner.nextInt();

        for (int i  = 0; i< numThread; i++){
            int index = i;

            Thread thread = new Thread(()->{
                System.out.println(index);
            });

            thread.start();
            // Wait other threads util they have finished  or dead. 
           thread.join();
        }

        scanner.close();



    }
    
}
