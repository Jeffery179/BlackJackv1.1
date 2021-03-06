import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(String img) {

		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image image) {
		this.img = image;
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
		setSize(size);
		setLayout(null);
	}


	public void paintComponent(Graphics g) { // Draw the image to the JPanel
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}

}