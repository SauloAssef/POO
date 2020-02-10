package core;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public abstract class JPanelDraw extends JPanel implements Runnable, KeyListener {
	protected final Tela tela;
	private final JProgressBar bar = new JProgressBar(0, 100);
	private int sleeper = 0;
	
	public JPanelDraw(int largura, int altura) {
		super();
		this.setSize(largura, altura);
		this.tela = new Tela(this);
		this.setFocusable(true);
		addKeyListener(this);
	}
	@Override
	public final void addNotify() {
		super.addNotify();
		new Thread(this).start();
	}
	
	/*
	 * loop() roda constantemente. 
	 * O tempo de espera é dado por sleeping(tempo) no filho! */
	@Override
	public final void run() {
		this.load();
		inicializar();
		while(true) {
			loop();			
			tela.clear();
			this.renderizar();
			tela.plot();
			try { Thread.sleep(Math.abs(sleeper)); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	protected final void sleeping(int _sleeper) { this.sleeper = _sleeper; }

	/*Inicializacao/carga */
	private final void load() { 
		this.add(bar); 
		for(int i = 0; i<100;i++) {
			try { Thread.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
			bar.setValue(i);
		} 
		this.remove(bar);
	}

	protected abstract void inicializar();
	protected abstract void loop();
	protected abstract void renderizar();
}
