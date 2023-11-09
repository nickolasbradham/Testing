package nbradham.testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

final class Tester {

	public static void main(String[] args) throws NativeHookException {
		GlobalScreen.registerNativeHook();
		Clicker c = new Clicker();
		c.start();
		GlobalScreen.addNativeMouseListener(new NativeMouseListener() {
			@Override
			public void nativeMouseClicked(NativeMouseEvent nativeEvent) {
				if (nativeEvent.getButton() == NativeMouseEvent.BUTTON3)
					c.toggle();
			}
		});
		GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {
				if (e.getKeyCode() == NativeKeyEvent.VC_F1) {
					c.kill();
					try {
						GlobalScreen.unregisterNativeHook();
					} catch (NativeHookException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private static final class Clicker extends Thread {

		private boolean run = true, click = false;

		private void toggle() {
			click = !click;
			synchronized (this) {
				notify();
			}
		}

		private void kill() {
			run = false;
			click = false;
			synchronized (this) {
				notify();
			}
		}

		@Override
		public void run() {
			Robot r = null;
			try {
				r = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			while (run) {
				while (click) {
					r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (run)
					try {
						synchronized (this) {
							wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}
}