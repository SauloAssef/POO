package core;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.VolatileImage;

public class Tela {
	public static int WIDTH;
	public static int HEIGHT;
	
	private Graphics2D PINTOR; 
	private VolatileImage IMAGEM_BUF; 
	private Component CONTAINER;
	
	public Tela(Component container) {
		this.CONTAINER = container;
		container.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	IMAGEM_BUF = CONTAINER.createVolatileImage(width(), height());
		    	PINTOR = IMAGEM_BUF.createGraphics();
		    	adaptCartesianCoordinates(getPINTOR());
				clear();
				System.out.println(WIDTH+":"+HEIGHT);
		    }
		});
	}
	private void adaptCartesianCoordinates(Graphics2D g2d) {
		double xTrans = ((double)CONTAINER.getWidth())/2.0;
		double yTrans = ((double)CONTAINER.getHeight())/2.0;
		g2d.translate(xTrans, yTrans);
		g2d.scale(1, -1);
	}
	public void draw(IDrawable obj) { obj.draw(this.PINTOR); }//, halfWidth(), halfHeight()); }
	
	public void clear() {
		Graphics2D g2d = getPINTOR();
		g2d.setColor(Color.BLACK);
		g2d.clearRect(-1*((int)halfWidth()), -1*((int)halfHeight()), width(), height());
		g2d.fill(new Rectangle2D.Double(-1*halfWidth(),  -1*halfHeight(),  width(),  height()));
		g2d.setColor(Color.WHITE);
	}
	public void plot() { 
		Graphics g = this.CONTAINER.getGraphics();
		g.drawImage(IMAGEM_BUF, 0, 0, null);
		g.dispose(); //memory free
	}
	public int width() { return WIDTH = this.CONTAINER.getWidth(); }
	public int height() { return HEIGHT = this.CONTAINER.getHeight(); }
	public double halfWidth() { return this.CONTAINER.getWidth()/2.0; }
	public double halfHeight() { return this.CONTAINER.getHeight()/2.0; }
	public Graphics2D getPINTOR() {
		return PINTOR;
	}
}
