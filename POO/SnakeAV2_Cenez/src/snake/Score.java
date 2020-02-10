package snake;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JPanel{
	private JLabel label = new JLabel("Pontuacao: " + 0);
	private int pontos = 0;
	private Font f = new Font("TimesRoman",Font.BOLD,15);
	public Score() {
		this.setBackground(Color.DARK_GRAY);
		this.label.setFont(f);
		this.setSize(10, 10);
		label.setForeground(Color.YELLOW);
		this.add(label);
	}
	public void incPonto(int value) {
		pontos = pontos + value;
		if(pontos >= 10) {label.setForeground(Color.GREEN);}else if(pontos < 10 && pontos > 0) { label.setForeground(Color.yellow);}
		label.setText("Pontuacao: " + pontos);
	}
	public void decPonto(int value) {
		pontos = pontos - value;
		if(pontos <= 0) {label.setForeground(Color.RED);}else  if(pontos < 10 && pontos > 0) { label.setForeground(Color.YELLOW);}
		label.setText("Pontuacao: " + pontos);
	}
}
