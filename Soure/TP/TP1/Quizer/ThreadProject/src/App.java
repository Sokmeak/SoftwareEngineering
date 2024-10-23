
import com.github.kwhat.jnativehook.GlobalScreen;
public class App {
    public static void main(String[] args) throws Exception {
// enble thread

       GlobalScreen.registerNativeHook(); 
// New key listener
       GlobalScreen.addNativeKeyListener(new CustomKeyListener());


    //    Ex1
    //    while ( ConstainVariable.isRunning) {
    //     System.out.print(ConstainVariable.message+" ");
    //     Thread.sleep(200);
        

    //    }
    //    System.out.println("\n" );
    //    System.out.println("Thank you!");


    //  Ex2
        while (ConstainVariable.isRunning) {
            System.out.print(ConstainVariable.message);

            Thread.sleep(100);
           
            
        }

        System.out.println("\n");
        System.out.println("Thank you!");



    }
}
