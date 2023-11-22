package nbradham.testing;

import java.io.IOException;

final class Tester {

	public static void main(String[] args) throws IOException {
		StringBuilder token;
		boolean run = true;
		int c;
		while (run) {
			token = new StringBuilder();
			while ((c = System.in.read()) != '\r') {
				switch (c) {
				default:
					token.append((char) c);
				}
			}
			System.out.println(token);
			run = !token.toString().equals("exit");
		}
	}
}