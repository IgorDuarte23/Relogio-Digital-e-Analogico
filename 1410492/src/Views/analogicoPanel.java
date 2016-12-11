package Views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.facade;

public class analogicoPanel extends JPanel implements Observer {
	
	//Observed object
    Observable controller;
	
	private static final long serialVersionUID = 1L;
	private JLabel label_Imagem;
	private ImageIcon image = new ImageIcon( getClass().getResource("/analogico.jpg"));
	private BufferedImage bufferedImage;
	private Graphics2D g2d;
	private int hour,minute,second;
	private int secondsAtAll = 0;
	private double ajusteDoTamanhoDaSetaFinal = 0.8;
	private double ajusteDoTamanhoDaSetaInicial = -0.001;
	
	private static final analogicoPanel INSTANCE = new analogicoPanel();
	
	
	//sigleton
	private analogicoPanel()
	{
		controller = facade.getInstance();
		controller.addObserver(this);
		try{
			bufferedImage = ImageIO.read(getClass().getResource("/analogico.jpg"));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static analogicoPanel getInstance(){
		return INSTANCE;
	}

	@Override
	public void paintComponent(Graphics graphic)
	{
		graphic.drawImage(bufferedImage, 0, 0, null);
		g2d = (Graphics2D)graphic;
		normalizeSegundos (g2d, new Dimension(450,450));
		double thetaSecond = (2 * Math.PI * 1 / 60) * second;
		double thetaMinute = (2 * Math.PI * 1/60) * minute;
		double thetaHour = (2 * Math.PI * 1/43200) * secondsAtAll;
		
		
		//ponteiro dos minutos
		  //  normalizeSegundos (g2d, new Dimension(450,450));
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.setColor(Color.BLUE);
		    g2d.setStroke(new BasicStroke (0.04f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.draw (new Line2D.Double(-ajusteDoTamanhoDaSetaInicial*Math.sin(thetaMinute),-ajusteDoTamanhoDaSetaInicial*Math.cos(thetaMinute),
			    		-ajusteDoTamanhoDaSetaFinal*(-Math.sin(thetaMinute)),-ajusteDoTamanhoDaSetaFinal*(-Math.cos(thetaMinute))));
			
			
		//ponteiro das horas
		g2d.setStroke(new BasicStroke (0.04f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.RED);
		g2d.draw (new Line2D.Double(-ajusteDoTamanhoDaSetaInicial*Math.sin(thetaHour),-ajusteDoTamanhoDaSetaInicial*Math.cos(thetaHour),
	    		-0.6*(-Math.sin(thetaHour)),-0.6*(-Math.cos(thetaHour))));
		
		//ponteiro dos segundos
		g2d.setStroke(new BasicStroke(2f));
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke (0.02f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));	
	    g2d.draw (new Line2D.Double(ajusteDoTamanhoDaSetaInicial*Math.sin(thetaSecond),ajusteDoTamanhoDaSetaInicial*Math.cos(thetaSecond),
	    		ajusteDoTamanhoDaSetaFinal* Math.sin(thetaSecond), ajusteDoTamanhoDaSetaFinal*Math.cos(thetaSecond)));
	 
	
	    
	  
		
	}
	private void normalizeSegundos (Graphics2D g2d, Dimension dim) {
		g2d.translate(dim.width / 2, dim.height/2);
		g2d.scale((((dim.width)/2)), -(((dim.height)/2)));
	}

	@Override
	public void update(Observable facadeInfSubject, Object arg) {
		if( facadeInfSubject instanceof facade)
		{
			facade temp = (facade)facadeInfSubject;
			hour = temp.getHour();
			minute = temp.getMinute();
			second = temp.getSecond();
			secondsAtAll = temp.getSecondsAtAll();
			secondsAtAll %= 43200;
			super.repaint();
		}
		
	}
}
