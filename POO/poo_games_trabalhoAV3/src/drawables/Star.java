package drawables;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

import core.BaseLimitedDrawable;

public class Star extends BaseLimitedDrawable {
	private int X = 0;
	private int Y = 0;
	private int SIZE = 0;
	
	public Star(double maxX, double maxY) {
		super(maxX, maxY);
	}
	public Star(double maxX, double maxY, int _size) {
		this(maxX, maxY);
		this.SIZE = _size;
	}
	public void setSize(int _size) { this.SIZE = _size; }
	public void moveTO(int _x, int _y) {
		this.X = _x;
		this.Y = _y;
	}
	public void moveIncremental(int _x, int _y) {
		this.X += _x;
		this.Y += _y;
	}
	public void randomize() {
		Random r = new Random();
		this.X = r.nextInt()%((int)this.MAX_X);
		this.Y = r.nextInt()%((int)this.MAX_Y);
	}
	public static Shape createStar(int num_pontas, int x, int y, double raioOut, double raioIn) {
	    double angle = Math.PI / num_pontas;

	    GeneralPath path = new GeneralPath();

	    for (int i = 0; i < 2 * num_pontas; i++) {
	        double r = (i & 1) == 0 ? raioOut : raioIn;
	        Point2D.Double p = new Point2D.Double(
	            x + Math.cos(i * angle) * r, 
	            y + Math.sin(i * angle) * r);
	        if (i == 0) {
	            path.moveTo(p.getX(), p.getY());
	        }
	        else {
	            path.lineTo(p.getX(), p.getY());
	        }
	    }
	    path.closePath();
	    return path;
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.draw(createStar(5, this.X, this.Y, 2, 4+SIZE));
	}
}
