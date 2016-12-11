package Views;

import javax.swing.JFrame;

public class digitalFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private int height = 200;
	private int width = 300;
	
	private digitalPanel panel;
	
	
	public digitalFrame()
	{
		setSize(width,height);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		panel = digitalPanel.getInstance();
		panel.setBounds(0, 0, width, height);
		add(panel);
		
		setTitle("Relógio Digital");
		
		setVisible(true);
		
	}
}
