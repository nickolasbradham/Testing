package nbradham.testing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

final class MapCardManager extends CardManager {

	private final BufferedImage bi;
	private final int c;
	private final byte w;

	MapCardManager() throws IOException {
		Image[] imgs = load();
		Graphics2D g = (bi = new BufferedImage((w = (byte) Math.ceil(Math.sqrt(c = imgs.length))) * LW,
				(int) (Math.ceil((c + 1) / w) * LH), BufferedImage.TYPE_INT_ARGB_PRE)).createGraphics();
		for (byte i = 0; i < c; ++i)
			g.drawImage(imgs[i], getX(i), getY(i), null);
		ImageIO.write(bi, "png", new FileImageOutputStream(new File("Out.png")));
	}

	private int getY(int i) {
		return i / w * LH;
	}

	private int getX(int i) {
		return i % w * LW;
	}

	@Override
	protected void drawCard(Graphics g) {
		int i = R.nextInt(c), sx = getX(i), sy = getY(i), dx = R.nextInt(1300), dy = R.nextInt(700);
		g.drawImage(bi, dx, dy, dx + SW, dy + SH, sx, sy, sx + LW, sy + LH, null);
	}
}