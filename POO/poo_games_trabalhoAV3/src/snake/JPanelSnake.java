package snake;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import core.JPanelDraw;
import drawables.Circulo;
import drawables.PacMan;
import drawables.Retangulo;
import snake.drawables.SnakeShape;

@SuppressWarnings("serial")
public class JPanelSnake extends JPanelDraw {
	private SnakeShape cobra;
	private Retangulo borda;
	private Retangulo obstaculo1, obstaculo2, obstaculo3;
	private Circulo comida;//comida não pode surgir sobre a cobra
	private KeyEvent e = null;
	private PacMan test;
	public JPanelSnake(int largura, int altura) {
		super(((int)largura/Circulo.SIZE)*Circulo.SIZE, ((int)altura/Circulo.SIZE)*Circulo.SIZE);
	}
	@Override
	protected void inicializar() { 
		cobra = new SnakeShape(tela.halfWidth(), tela.halfHeight());
		test = new PacMan(0, 0, Color.RED);
		borda = new Retangulo(tela.halfWidth(), tela.halfHeight());
		obstaculo1 = new Retangulo(50, 50, 20, 100, Color.RED, true);
		obstaculo2 = new Retangulo(-70, 50, 20, 100, Color.RED, true);
		obstaculo3 = new Retangulo( -50, -40, 100, 20, Color.RED, true);
		int[] xy = getRandomCoord();
		comida = new Circulo(xy[0], xy[1], Color.RED);
	}
	@Override
	public void loop() {
		this.sleeping(100);
		
		if(this.cobra.cima == true) {
			if(this.cobra.getHEAD().finall == 0 || this.cobra.getHEAD().finall == 25) {
				this.cobra.getHEAD().finall += 25;
				this.cobra.getHEAD().start = 250;
			}else if(this.cobra.getHEAD().finall == 50 || this.cobra.getHEAD().finall == 25 ) {
				this.cobra.getHEAD().finall -= 25;
				this.cobra.getHEAD().start = 250;
				
			}
		}
		
		if(this.cobra.baixo == true) {
			if(this.cobra.getHEAD().finall == 0 || this.cobra.getHEAD().finall == 25) {
				this.cobra.getHEAD().finall += 25;
				this.cobra.getHEAD().start = 50;
			}else if(this.cobra.getHEAD().finall == 50 || this.cobra.getHEAD().finall == 25 ) {
				this.cobra.getHEAD().finall -= 25;
				this.cobra.getHEAD().start = 50;
				
			}
		}
		
		if(this.cobra.esquerda == true) {
			if(this.cobra.getHEAD().finall == 0 || this.cobra.getHEAD().finall == 25) {
				this.cobra.getHEAD().finall += 25;
				this.cobra.getHEAD().start = 150;
			}else if(this.cobra.getHEAD().finall == 50 || this.cobra.getHEAD().finall == 25 ) {
				this.cobra.getHEAD().finall -= 25;
				this.cobra.getHEAD().start = 150;
			
		}
	}
		
		if(this.cobra.direita == true) {
			if(this.cobra.getHEAD().finall == 0 || this.cobra.getHEAD().finall == 25) {
				this.cobra.getHEAD().finall += 25;
				this.cobra.getHEAD().start = 350;
			}else if(this.cobra.getHEAD().finall == 50 || this.cobra.getHEAD().finall == 25 ) {
				this.cobra.getHEAD().finall -= 25;
				this.cobra.getHEAD().start = 350;
			
		}
	}
		
		
		
		
		if(comida.equals(cobra.getHEAD())) {
			cobra.addToTail(new Circulo(comida.X, comida.Y, Color.WHITE));
			int[] xy = getRandomCoord();
			comida.moveTO(xy[0], xy[1]);
		}
		if(e!=null) cobra.listenKey(e);
		
		if(obstaculo1.intersects(comida.CIRCULO)) {
			System.out.println("comida");
			int[] xy = getRandomCoord();
			comida.moveTO(xy[0], xy[1]);
		}
		
		if(obstaculo2.intersects(comida.CIRCULO)) {
			System.out.println("comida");
			int[] xy = getRandomCoord();
			comida.moveTO(xy[0], xy[1]);
		}
		
		if(obstaculo3.intersects(comida.CIRCULO)) {
			System.out.println("comida");
			int[] xy = getRandomCoord();
			comida.moveTO(xy[0], xy[1]);
		}
		
		if(obstaculo1.intersects(cobra.getHEAD().CIRCULO)){
				cobra.BaterObstaculo();
			
		}
		
		if(obstaculo2.intersects(cobra.getHEAD().CIRCULO)) {
			System.out.println("Intersects....");
			cobra.BaterObstaculo();
		}
		
		if(obstaculo3.intersects(cobra.getHEAD().CIRCULO)) {
			System.out.println("Intersects....");
			cobra.BaterObstaculo();
		}
//		if(Math.abs(cobra.getHEAD().X) >= tela.halfWidth() - cobra.getHEAD().SIZE*2) {
//			System.out.println("deu");
//			cobra.encostarBorda();
//		}else if(Math.abs(cobra.getHEAD().Y) >= tela.halfWidth() - cobra.getHEAD().SIZE*2) {
//			cobra.encostarBorda();
//		}
			
		
		
		cobra.update();
		
	}
	@Override
	protected void renderizar() {
		this.tela.draw(borda);
		this.tela.draw(cobra);
		this.tela.draw(comida);
		this.tela.draw(obstaculo1);
		this.tela.draw(obstaculo2);
		this.tela.draw(obstaculo3);
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) { 
		this.e = e;
	}
	@Override
	public void keyTyped(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) { }
	
	private int[] getRandomCoord() { 
		Random r = new Random();
		int[] xy = new int[2];
		xy[0] = r.nextInt()*2%((int)tela.halfWidth()-Circulo.SIZE*2);
		xy[1] = r.nextInt()*2%((int)tela.halfHeight()-Circulo.SIZE*2);
		return xy;
	}
}
