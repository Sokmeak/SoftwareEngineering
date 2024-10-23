import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class DemoThread {
    static boolean isRunning = true;
    static String text = "HitMe!";

    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyPressed(NativeKeyEvent e) {
                if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
                    isRunning = false;
                    // stop input anything
                    return;

                }
                text = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
                NativeKeyListener.super.nativeKeyPressed(e);
            }

        });

        while (isRunning) {
            System.out.print(text);
            Thread.sleep(500);
        }

        System.out.println("\n");

        System.out.print(text + " ");

    }

}
