package nbradham.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

final class Tester {

	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime()
				.exec(new String[] { "C:\\Program Files (x86)\\Steam\\steamapps\\common\\U3DS\\Unturned.exe" });
		Scanner s = new Scanner(p.getInputStream());
		PrintWriter pw = new PrintWriter(p.getOutputStream(), true);
		Scanner sSTD = new Scanner(System.in);
		new Thread(() -> {
			while (s.hasNext())
				System.out.printf("[CHILD] %s%n", s.nextLine());
			System.out.println("Thread ended.");
		}).start();
		String st;
		while (p.isAlive()) {
			st = sSTD.nextLine();
			System.out.printf("[HOST] Sending to child: %s%n", st);
			pw.println(st);
		}
		sSTD.close();
	}
}