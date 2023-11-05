package nbradham.testing;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

final class Tester extends JPanel {
	private static final long serialVersionUID = 1L;

	private final CardManager cm = new MapCardManager();

	private Tester() throws IOException {
		super();
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public final void keyTyped(KeyEvent e) {
				repaint();
			}
		});
	}

	@Override
	public final void paint(Graphics g) {
		long start = System.currentTimeMillis();
		for (short i = 0; i < 5000; ++i)
			cm.drawCard(g);
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1366, 768);
			try {
				frame.setContentPane(new Tester());
			} catch (IOException e) {
				e.printStackTrace();
			}
			frame.setVisible(true);
		});
	}
}