package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

public class PacMan extends Circulo {

	public int start = 150;
	public int finall = 0;
	
	public PacMan(double x, double y, Color cor) {
		super(x, y, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.red);
		g2d.fillOval((int) this.X, (int) this.Y, 20, 20);
		
		g2d.setColor(Color.BLACK);
		
		g2d.fillArc((int) this.X, (int) this.Y, 19, 19, start, finall);
	
		//super.draw(g2d);
	}

}
