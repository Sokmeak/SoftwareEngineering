import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

/**
 * CustomKeyListener
 */
public class CustomKeyListener implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        // System.out.println( "You pressed:
        // "+NativeKeyEvent.getKeyText(e.getKeyCode()));
        // NativeKeyListener.super.nativeKeyPressed(e);
        // detect if user input
        // System.out.println( "You pressed:
        // "+NativeKeyEvent.getKeyText(e.getKeyCode()));

        // ConstainVariable.message =
        // NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase();

        if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            ConstainVariable.isRunning = false;

        }
        ConstainVariable.message = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
    }

}