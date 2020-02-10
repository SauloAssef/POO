package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import core.IDrawable;

public class SpaceCraft implements IDrawable {
	public double X, Y;
	public static int SIZE;
	private String file = "nave.png";
	
	protected ImageObserver observer = null;
	public SpaceCraft(int size, ImageObserver o, double x, double y) {
		SpaceCraft.SIZE = size;
		this.observer = o;
		this.X = x; this.Y = y;
	}
	
	@Override
	public void draw(Graphics2D g2d) {//, double maxX, double maxY) {
		draw(g2d, Color.WHITE);
	}
	public void draw(Graphics2D g2d, Color cor) {
		g2d.setColor(cor);
		g2d.drawArc((int) this.X, (int) this.Y, SpaceCraft.SIZE, SpaceCraft.SIZE, 180, 180);
		if(observer!=null) {
			Image nave = new ImageIcon(this.getClass().getResource(file)).getImage();
			g2d.drawImage(nave, (int)this.X, (int)this.Y, observer);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SpaceCraft) {
			SpaceCraft p = (SpaceCraft) obj;
			return this.X==p.X && this.Y==p.Y;
		}
		return false;
	}
	public void setXY(double _x, double _y) {
		this.X = _x;
		this.Y = _y;
	}
	public void move(double _x, double _y) {
		this.X += _x;
		this.Y += _y;
	}
}
