
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrowerImage {
	private Font font;
	private String text;
	private Graphics graphics;
	private BufferedImage image;

	private final int ROWS_COUNT = 30;
	private final int COLS_COUNT = 30;
	private final int FONT_SIZE = 20;
	private final int PHOTO_WIDTH = 600;
	private final int PHOTO_HEIGHT = 600;
	private final String FONT_NAME = "TimesRoman";

	private int curFontSize;

	public BufferedImage draw(String text, int rowCount, int colCount, int fontSize) {
		init(text, fontSize);
		drawText(rowCount, colCount);
		return image;
	}

	private void drawText(int rowCount, int colCount) {
		graphics = image.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.setFont(font);
		try {
			for (int i = 0; i < rowCount; i++)
				for (int j = 0; j < colCount; j++) {
					int x = j * curFontSize, y = i * rowCount + curFontSize;
					graphics.drawString(text.charAt(j + i * rowCount) + "", x, y);
				}
		} catch (Exception ex) {
		}
		graphics.dispose();
	}

	private void init(String text, int fontSize) {
		this.font = new Font(FONT_NAME, Font.BOLD, fontSize);
		this.image = new BufferedImage(PHOTO_WIDTH, PHOTO_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		this.curFontSize = fontSize;
		this.text = text;
	}

	public static void saveImage(BufferedImage img, String fileName, String filExtension) {
		try {
			ImageIO.write(img, "png", new File(fileName + "." + filExtension));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage draw(String text) {
		return draw(text, ROWS_COUNT, COLS_COUNT, FONT_SIZE);
	}
}
