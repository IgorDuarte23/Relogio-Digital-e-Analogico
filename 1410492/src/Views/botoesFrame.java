package Views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;


public class botoesFrame extends JFrame{
int width = 300;
int height = 200;
private JButton botao_a;
private JButton botao_b;
	botoesPanel panel;
	public botoesFrame()
	{
		setSize(width,height);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		botao_a = new JButton("A");
		botao_b = new JButton("B");

		botao_a.setBounds(20, 20, 80, 80);
		botao_b.setBounds(200, 20, 80, 80);
	
		botao_a.setBackground(Color.GRAY);
		botao_b.setBackground(Color.GRAY);

		add(botao_a);
		add(botao_b);

		panel = new botoesPanel(botao_a,botao_b);
		panel.setBounds(0, 0, width, height);
		add(panel);
		setTitle("Botões");
		
		setVisible(true);
	}
	
}
