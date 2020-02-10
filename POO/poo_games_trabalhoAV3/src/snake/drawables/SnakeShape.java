package snake.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import core.BaseLimitedDrawable;
import drawables.Circulo;
import drawables.PacMan;

public class SnakeShape extends BaseLimitedDrawable {
	private final Color HEAD_COLOR = Color.RED;
	private final PacMan HEAD;// = new Point(0, 0);
	private final List<Circulo> TAIL = new ArrayList<>();
	public boolean cima = false;
	public boolean baixo = false;
	public boolean direita = false;
	public boolean esquerda = true;
	
	public int fluxoX=-Circulo.SIZE, fluxoY=0; 
	
	public SnakeShape(double xMax, double yMax) {
		super(xMax, yMax);
		HEAD = new PacMan((MAX_X-Circulo.SIZE*2), (MAX_Y-Circulo.SIZE*2), HEAD_COLOR);
		for(int i = 1; i <= 4; i++)
			TAIL.add(new Circulo(Circulo.SIZE * i, 0, Color.WHITE));
		TAIL.get(0).moveTO(HEAD.X, HEAD.Y);
		for(int i = 1; i < TAIL.size(); i++)
			TAIL.get(i).moveTO(TAIL.get(i - 1).X, TAIL.get(i - 1).Y);
		
	}
	
	
	
	public PacMan getHEAD() { return HEAD; }
	
	
	
	public void addToTail(Circulo p) { TAIL.add(p); }
	
	
	public void BaterObstaculo() {
		HEAD.moveIncremental(-fluxoX, -fluxoY);  
		fluxoX = -fluxoX;
		fluxoY = -fluxoY;
		
	}
	
	public void encostarBorda() {
		HEAD.moveIncremental(-fluxoX, -fluxoY);
		fluxoX = 0;
		fluxoY = 0;
	}
	
	public void up() {
		if(fluxoY==0) {
			fluxoY = Circulo.SIZE;
			fluxoX = 0;
			cima = true;
			baixo = false;
			esquerda = false;
			direita = false;
		} 
	}
	public void down() {
		if(fluxoY==0) {
			fluxoY = -Circulo.SIZE;
			fluxoX = 0;
			cima = false;
			baixo = true;
			esquerda = false;
			direita = false;
		} 
	}
	public void left() {
		if(fluxoX==0) {
			fluxoY = 0;
			fluxoX = -Circulo.SIZE;
			cima = false;
			baixo = false;
			esquerda = true;
			direita = false; 
		} 
	}
	public void right() {
		if(fluxoX==0) {
			fluxoY = 0;
			fluxoX = Circulo.SIZE;
			cima = false;
			baixo = false;
			esquerda = false;
			direita = true;
			
		} 
	}
	public void listenKey(KeyEvent e) { 
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) 		this.up();
		else if(k == KeyEvent.VK_DOWN) 	this.down();
		else if(k == KeyEvent.VK_LEFT) 	this.left();
		else if(k == KeyEvent.VK_RIGHT) this.right();
	}
	public void update() {
		updateTail();
		updateHead();
	}
	private void updateHead() { 
		HEAD.moveIncremental(fluxoX, fluxoY);
		if(HEAD.X+Circulo.SIZE*2>MAX_X) encostarBorda();
		else if(HEAD.X-Circulo.SIZE<-MAX_X) encostarBorda();
		else if(HEAD.Y+Circulo.SIZE*2>MAX_Y) encostarBorda();
		else if(HEAD.Y-Circulo.SIZE<-MAX_Y) encostarBorda();
	}
	private void updateTail() { 
		for(int i = TAIL.size() - 1; i > 0; i--)
			TAIL.get(i).moveTO(TAIL.get(i - 1).X, TAIL.get(i - 1).Y);
		TAIL.get(0).moveTO(HEAD.X, HEAD.Y);
	}
	@Override
	public void draw(Graphics2D g2d) {
		TAIL.forEach(p -> p.draw(g2d));
		Color cor = g2d.getColor();
		g2d.setColor(HEAD_COLOR);
		HEAD.draw(g2d);
		g2d.setColor(cor);
	}
}

/* Observação sobre Movimento:
 * fluxoX diz o sentido que cobra segue na coordenada X do plano cartesiano;
 * fluxoY diz o sentido que cobra segue na coordenada Y do plano cartesiano;
 * Em fluxoX==0 ou fluxoY==0 diz que esse movimento é estável, 
 * respectivamente para sentidos X e Y, não existindo movimento;
 * fluxoX ou fluxoY caminham ao passo +-Point.SIZE.
 */

