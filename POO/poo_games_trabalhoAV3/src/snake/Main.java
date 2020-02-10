package snake;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import drawables.Circulo;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public Main() {
		int largura = 500, altura = 530;
		setTitle("Snake 2D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new JPanelSnake(largura, altura));
		setSize(largura+Circulo.SIZE*2, altura+Circulo.SIZE*2);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().setVisible(true);
			}
		});
	}
}
