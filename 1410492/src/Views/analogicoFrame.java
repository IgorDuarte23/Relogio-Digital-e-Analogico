package Views;
import javax.swing.JFrame;

public class analogicoFrame extends JFrame{
int width = 500;
int height = 500;
	analogicoPanel panel;
	analogicoFrame()
	{
		setSize(width,height);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		panel = analogicoPanel.getInstance();
		panel.setBounds(0, 0, width, height);
		add(panel);
		setTitle("Relógio Analógico");
		setVisible(true);
	}
	
}
