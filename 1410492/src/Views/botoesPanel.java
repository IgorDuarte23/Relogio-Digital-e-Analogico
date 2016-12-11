package Views;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controllers.facade;

public class botoesPanel extends JPanel{
	//Declaracoes
	private static final long serialVersionUID = 1L;
	private facade fac;
	private JButton botao_a;
	private JButton botao_b;
	//Construtor
	public botoesPanel(JButton a, JButton b)
	{	
		botao_a = a;
		botao_b = b;
		fac = facade.getInstance();
	}
	
	@Override
	public void paintComponent(Graphics graphic)
	{
		
		super.paintComponent(graphic);



		  botao_a.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	botao_a.setBackground(fac.buttonClick(botao_a.getBackground(), botao_b.getBackground(), 1));
		        	
		        }
		    });
		  
		  botao_b.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	botao_b.setBackground(fac.buttonClick(botao_a.getBackground(), botao_b.getBackground(), 2));  
		        	
		        }
		    });
		
		  
	}
}
