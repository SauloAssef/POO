package snake;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import snake.drawables.Snake;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public Main() {
		int largura = 400, altura = 430;
		setTitle("Snake 2D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel painelCobra = new JPanelSnake(largura,altura);
		add(painelCobra,BorderLayout.CENTER);
		
		add(JPanelSnake.SCORE, BorderLayout.NORTH);
		
		setSize(largura, altura);
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
