/* timer class
 * Essa classe tem todas as informações relevantes sobre o tempo.
 * É uma classe especialista, ou seja, todas as informações sobre
 * tempo só são encontrados nela.
 * timer é uma classe que está sendo observada pela classe que
 * controla a aplicação, o facade , além de ser um Singleton
 * 
 * Modelagem de Software
 * Igor Duarte Milanez Vieira
 * igordmv1995@gmail.com
 */
package Models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.TimerTask;
import javax.swing.Timer;

import Controllers.facade;
public class timer extends Observable implements ActionListener {
	//Variables
      private javax.swing.Timer tim;
	  private int interval;	 
	  private int minute;
	  private int hour;
	  private int second;
	  private int secondsAtAll;
	  //singleton declaration
	  private static final timer INSTANCE = new timer();
	//Variables end
	  
	//constructor
	private timer()
	{
	    interval = 1000;  // interval of 1/10 sec
	    tim = new Timer(interval,this);
	    tim.start();
	}
	//constructor end
	
	//altera a hora
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	//set hour end
	
	//altera os minutos
		public void setMinute(int minute)
		{
			this.minute = minute;
		}
	//setMinute end
		
	//altera os segundos reais
	public void setSeconds(int second)
	{
		this.second = second;
	}
	//setSeconds end
	
	//altera os segundos em geral, pro ponteiro de hora
	public void setSecondsAtAll(int secondsAtAll)
	{
		this.secondsAtAll = secondsAtAll;
	}
	//SetSecondsAtAll end
	
	//retorna a hora corrente
	public int getHour()
	{
		return hour;
	}
	//getHour end
	
	//Retorna quantos segundos o relogio se encontra
	public int getSecond()
	{
		return second;
	}
	//getSecond end
	
	//retorna quantos minutos o relogio se encontra
	public int getMinute()
	{
		return minute;
	}
	//getMinute end
	
	//Retorna os segundos totais (usado pro ponteiro de hora correr
	// conforme os segundos passam).
	public int getSecondsAtAll()
	{
		return secondsAtAll;
	}
	//getSecondsAtAll end
	
	public void increaseMinute()
	{
		minute++;
		secondsAtAll += 60;
		secondsAtAll%=43200;
		if(minute == 60)
		{
			minute = 0;
			hour++;
			if(hour == 24)
				hour = 0;
		}
        setChanged();
		notifyObservers();
	}
	
	public void increaseHour()
	{
		hour++;
		secondsAtAll += 3600;
		secondsAtAll%=43200;
		if(hour == 24)
			hour = 0;

        setChanged();
		notifyObservers();
	}
	
	//Singleton
	public static timer getInstance()
	{
		return INSTANCE;
	}
	//getInstance end
	
	//Responsável pelo travamento do relógio
	public void stopTimer()
	{
		tim.stop();
	}
	//stopTimer end
	
	//Responsável pela iniciação do timer
	public void startTimer()
	{
		tim.restart();
		}
	//StartTimer end
	
	//Funcao responsável por alterar o horário
	public void setTime(int hour, int minute, int second)
	{
		this.hour =  hour;
		this.minute = minute;
		this.second = second;
	}
	//setTime end

	@Override
	public void actionPerformed(ActionEvent e) {
		
		second++;
        secondsAtAll++;
        if(second > 59)
        {
        	second = 0;
        	minute++;
        }
        if(minute > 59)
        {
        	minute =0;
        	hour ++;
        }
        if(hour >23)
        	hour=0;
        
        setChanged();
		notifyObservers();
	}
}
