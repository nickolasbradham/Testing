package nbradham.testing;

import com.fazecast.jSerialComm.SerialPort;

final class Tester {

	private static final String[] MESSAGES = { "Beep. Boop.", "I'm a computer.", "I'm sorry, Dave.",
			"Stealing your\nbank details...", "2 + 2 = Fish" };

	public static void main(String[] args) throws InterruptedException {
		SerialPort sp = SerialPort.getCommPort("COM6");
		sp.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		sp.clearDTR();
		sp.clearRTS();
		sp.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
		System.out.println(sp.openPort());
		for (byte n = 0; n < 50; ++n) {
			int r = n % MESSAGES.length;
			System.out.println("Sending: " + MESSAGES[r]);
			sp.writeBytes(MESSAGES[r].getBytes(), 50);
			Thread.sleep(2000);
		}
		sp.closePort();
	}
}