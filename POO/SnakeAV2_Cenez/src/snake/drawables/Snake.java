package snake.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import core.BaseLimitedDrawable;
import drawables.Point;
import snake.Score;

public class Snake extends BaseLimitedDrawable {
	private Point HEAD = new Point(0,0);
	private int fluxoX = -Point.SIZE; 
	private int fluxoY = 0;
	private final List<Point> TAIL = new ArrayList<>();
	public final static Score SCORE = new Score();
	
	public Snake(double xMax, double yMax) {
		super(xMax, yMax);
		for(int i = 1; i<=2; i++)
			TAIL.add(new Point(Point.SIZE*i, 0));
	}
	public void update() {
		this.updateTail();
		this.updateHead();
	}
	private void updateTail() { 
		for(int i = TAIL.size() - 1; i > 0; i--) {
			Point ultimo = TAIL.get(i);
			Point penultimo = TAIL.get(i-1);
			ultimo.moveTO(penultimo.X, penultimo.Y);
		}
		TAIL.get(0).moveTO(HEAD.X, HEAD.Y);
	}
	
	private void updateHead() {
		//if(Math.abs(HEAD.X) > this.MAX_X) {HEAD.X = -HEAD.X;}
		//if(Math.abs(HEAD.Y) > this.MAX_Y) { HEAD.Y = -HEAD.Y;}
		
		HEAD.moveIncremental(fluxoX, fluxoY);
		if(HEAD.X+Point.SIZE*2>MAX_X) HEAD.X = -(MAX_X)+Point.SIZE;
		else if(HEAD.X-Point.SIZE<-MAX_X) HEAD.X = (MAX_X-Point.SIZE*2);
		else if(HEAD.Y+Point.SIZE*2>MAX_Y) HEAD.Y = -(MAX_Y)+Point.SIZE;
		else if(HEAD.Y-Point.SIZE<-MAX_Y) HEAD.Y = (MAX_Y-Point.SIZE*2);
	}
	public boolean encostou() {
		//passou pelo limite da tela.
		if(Math.abs(HEAD.Y) > this.MAX_Y) {
			return true;
		}else
		if(Math.abs(HEAD.X) > this.MAX_X) {
			return true;
		}else
			return false;
	}
	
	public boolean tocou() {
		//Tocou na propria cauda.
		for(int i = TAIL.size() - 1; i > 0; i--) {
			if((HEAD.X) == TAIL.get(i).X && (HEAD.Y) == TAIL.get(i).Y) {
				throw new RuntimeException();
				
			}
			
		}
		return false;
		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		Color cor = g2d.getColor();
		
		TAIL.forEach(p -> { p.draw(g2d); });
		
		g2d.setColor(Color.RED);
		HEAD.draw(g2d);
		g2d.setColor(cor);
	}
	public void listenKey(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_ESCAPE) {System.exit(0);;}
		
		else if(k==KeyEvent.VK_UP) this.up();
		else if(k==KeyEvent.VK_DOWN) this.down();
		else if(k==KeyEvent.VK_LEFT) this.left();
		else if(k==KeyEvent.VK_RIGHT) this.right();
	}
	private void up() {
		if(fluxoY==0) {
			fluxoY = Point.SIZE;
			fluxoX = 0;
		}
	}
	private void left() {
		if(fluxoX==0) {
			fluxoX = -Point.SIZE;
			fluxoY = 0;
		}
	}
	private void down() {
		if(fluxoY==0) {
			fluxoY = -Point.SIZE;
			fluxoX = 0;
		}
	}
	private void right() {
		if(fluxoX==0) {
			fluxoX = Point.SIZE;
			fluxoY = 0;
		}
	}
	public void addToTail(Point p) {
		TAIL.add(p);
	}
	public Point getHead() {
		return HEAD;
	}
}


