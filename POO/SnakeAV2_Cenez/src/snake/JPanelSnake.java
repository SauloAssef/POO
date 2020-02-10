package snake;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import core.JPanelDraw;
import drawables.CartesianPlane;
import drawables.Point;
import snake.drawables.Snake;

@SuppressWarnings("serial")
public class JPanelSnake extends JPanelDraw {
	private Snake cobra;
	private CartesianPlane plano;
	private Point comida;
	private Point comida2;
	private Point comidaPodre;
	public final static Score SCORE = new Score();

	
	public JPanelSnake(int largura, int altura) {
		super(largura, altura);
	}
	@Override
	protected void inicializar() { 
		cobra = new Snake(tela.halfWidth(), tela.halfHeight());
		plano = new CartesianPlane(tela.halfWidth(), tela.halfHeight());
		int[] xy = getRandomCoord();
		int[] xy2 = getRandomCoord();
		int[] xy3 = getRandomCoord();
		comida = new Point(xy[0], xy[1]);
		comida2 = new Point(xy2[0], xy[1]);
		comidaPodre = new Point(xy3[0],xy3[1]);
		
	}
	@Override
	public void loop() {
		this.sleeping(100);
		
		if(comida.equals(cobra.getHead())) {
			cobra.addToTail(new Point(comida.X, comida.Y));
			int[] xy = getRandomCoord();
			comida.moveTO(xy[0], xy[1]);
			SCORE.incPonto(10);
		}
		if(comida2.equals(cobra.getHead())) {
			cobra.addToTail(new Point(comida2.X, comida2.Y));
			int[] xy2 = getRandomCoord();
			comida2.moveTO(xy2[0], xy2[1]);
			SCORE.incPonto(3);
		}
		if(comidaPodre.equals(cobra.getHead())) {
			cobra.addToTail(new Point(comidaPodre.X, comidaPodre.Y));
			int[] xy3 = getRandomCoord();
			comidaPodre.moveTO(xy3[0], xy3[1]);
			SCORE.decPonto(2);
		}
		if(cobra.encostou() == true) {
			// Passou do limite da tela.
			SCORE.decPonto(1);
		}
		if(cobra.tocou() == true) {
			// Tocou na pripria tela.
			
		}
		cobra.update();
	}
	@Override
	protected void renderizar() {
		this.tela.draw(cobra);
		this.tela.draw(plano);
		this.tela.draw(Color.GREEN,comida);
		this.tela.draw(Color.WHITE,comida2);
		this.tela.draw(Color.RED,comidaPodre);
	}
	private int[] getRandomCoord() {
		int[] xy = new int[2];
		Random r = new Random();
		xy[0] = r.nextInt()%((int) tela.halfWidth() - Point.SIZE);
		xy[1] = r.nextInt()%((int) tela.halfHeight() - Point.SIZE);
		return xy;
	}
	
	@Override
	public void keyPressed(KeyEvent e) { 
		cobra.listenKey(e);
	}
	@Override
	public void keyTyped(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) { }
}
