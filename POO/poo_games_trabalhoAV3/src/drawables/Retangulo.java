package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import core.BaseLimitedDrawable;

public class Retangulo extends BaseLimitedDrawable {
	public final Rectangle RETANGULO;
	private boolean fill = false;
	private Color cor = Color.WHITE;
	
	public Retangulo(double maxX, double maxY) {
		super(maxX, maxY);
		RETANGULO = new Rectangle(
				(int) -maxX   + Circulo.SIZE, 
				(int) -maxY   + Circulo.SIZE, 
				(int)  maxX*2 - 2*Circulo.SIZE, 
				(int)  maxY*2 - 2*Circulo.SIZE
				);
	}
	public Retangulo(double maxX, double maxY, double w, double h, Color cor, boolean _fill) {
		super(maxX, maxY);
		RETANGULO = new Rectangle(
				(int) maxX, 
				(int) maxY, 
				(int) w, 
				(int) h
				);
		this.fill = _fill;
		this.cor = cor;
	}
	
	public boolean intersects(Shape shape) {
		return shape.intersects(RETANGULO);
	}
	
	@Override
	public void draw(Graphics2D g2d) { 
		Color tmp = g2d.getColor();
		g2d.setColor(cor);
		if(fill) g2d.fill(RETANGULO);
		g2d.draw(RETANGULO); 
		g2d.setColor(tmp);
	}
}
