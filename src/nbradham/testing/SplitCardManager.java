package nbradham.testing;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

final class SplitCardManager extends CardManager {

	private final Image[] imgs;

	SplitCardManager() throws IOException {
		imgs = load();
	}

	@Override
	protected void drawCard(Graphics g) {
		g.drawImage(imgs[R.nextInt(imgs.length)], R.nextInt(1300), R.nextInt(700), SW, SH, null);
	}
}