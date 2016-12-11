package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.facade;

public class digitalPanel extends JPanel implements Observer{

	private static final long serialVersionUID =1L;
	Observable controller;
	private JLabel label_Hora;
	private JLabel label_Pontos;
	private JLabel label_Minutos;
	private int minute,hour,second;
	private static final digitalPanel INSTANCE = new digitalPanel();
	private digitalPanel()
	{
		second = 0;
		
		label_Hora = new JLabel();
		label_Hora.setBounds(0, 0, 125, 150);
		label_Hora.setFont(new Font("Serif",Font.BOLD,120));
		label_Hora.setForeground(Color.BLACK);
		
		
		label_Pontos = new JLabel();
		label_Pontos.setBounds(125, 0,25 , 150);
		label_Pontos.setFont(new Font("Serif",Font.PLAIN,80));
		label_Pontos.setForeground(Color.BLACK);
		
		label_Minutos = new JLabel();
		label_Minutos.setBounds(155, 0, 125, 150);
		label_Minutos.setFont(new Font("Serif",Font.BOLD,120));
		label_Minutos.setForeground(Color.BLACK);
		controller = facade.getInstance();
		controller.addObserver(this);
		
	}
	public void changeLabelColor(facade fac)
	{
		if(!fac.HourIsRed && !fac.MinuteIsRed && !fac.AlreadyPaintedHourLabel)
		{
			label_Hora.setForeground(Color.RED);
			fac.HourIsRed = true;
			fac.AlreadyPaintedHourLabel = true;
		}
		else if(fac.HourIsRed && !fac.MinuteIsRed)
		{
			label_Hora.setForeground(Color.BLACK);
			label_Minutos.setForeground(Color.RED);
			fac.MinuteIsRed = true;
			fac.HourIsRed = false;
		}
		else if(!fac.HourIsRed && !fac.MinuteIsRed && fac.AlreadyPaintedHourLabel)
		{
			label_Minutos.setForeground(Color.RED);
			fac.MinuteIsRed = true;
		}
		else if(!fac.HourIsRed && fac.MinuteIsRed )
		{
			label_Minutos.setForeground(Color.BLACK);
			fac.AlreadyPaintedHourLabel = false;
			fac.MinuteIsRed = false;
		}
		
		
		super.repaint();
	}
	public static digitalPanel getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public void paintComponent(Graphics graphic)
	{
		super.paintComponent(graphic);
		label_Hora.setText(String.format("%02d",hour));
		label_Pontos.setText(":");
		label_Minutos.setText(String.format("%02d", minute));
		
		add(label_Hora);
		add(label_Pontos);
		add(label_Minutos);
		
	}
	
	@Override
	public void update(Observable facadeInfSubject, Object arg) {
		if( facadeInfSubject instanceof facade)
		{
			facade temp = (facade)facadeInfSubject;
			hour = temp.getHour()%24;
			minute = temp.getMinute()%60;
			second = temp.getSecond()%60;
			super.repaint();
			
		}
		
	}
	
}
