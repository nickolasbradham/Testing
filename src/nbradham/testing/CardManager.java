package nbradham.testing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

abstract class CardManager {

	static final short SH = 200, SW = 143, LH = 350, LW = 250;
	static final Random R = new Random(0);

	protected static final Image[] load() throws IOException {
		ArrayList<Image> arr = new ArrayList<>();
		for (File f : new File("C:\\Users\\bradh\\Downloads\\Sram Deck").listFiles())
			arr.add(ImageIO.read(f).getScaledInstance(LW, LH, BufferedImage.SCALE_SMOOTH));
		return arr.toArray(new Image[0]);
	}

	protected abstract void drawCard(Graphics g);
}