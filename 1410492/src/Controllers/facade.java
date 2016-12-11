/* facade class
 * Essa classe é responsavel pelo facade da aplicação.
 * Todas as ações são passadas para essa classe que delega essa ação
 * para aonde for necessário.
 * 
 * Modelagem de Software
 * Igor Duarte Milanez Vieira
 * igordmv1995@gmail.com
 */
package Controllers;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;


import Models.timer;
import State.ClockState;
import Views.digitalPanel;
public class facade extends Observable implements Observer{

	 //Variaveis
	  private static final facade INSTANCE = new facade();
	  private timer timer;
	  Observable controller;
	  private ClockState state;
	  public boolean AlreadyChangedHour = false;
	  public boolean AlreadyChangedMinute = false;
	  public boolean HourIsRed = false;
	  public boolean MinuteIsRed = false;
	  public boolean AlreadyPaintedHourLabel = false;
	 //Variaveis end
	 
	//construtor
	private facade()
	{	
		controller = timer.getInstance();
		controller.addObserver(this);
		timer = timer.getInstance();
		state = ClockState.getInicialState(this);
		timer.startTimer();
	}
	//construtor end
	

	//Chama a ação dinâmica de acordo com o estado corrente
	public void clickButtonA(){
		ClockState temp = state.ClickButtonA();
		if(temp != null)
			state = temp;
		
		setChanged();
		notifyObservers();
	}
	//clickButtonA end
	
	//incia o tempo
	public void startTimer()
	{
		timer.startTimer();
	}
	//starttimer end
	
	//interrompe o relogio
	public void stopTimer()
	{
		timer.stopTimer();
	}
	//stopTimer end
	
	//Chama a ação dinâmica de acordo com o estado corrente
	public void clickButtonB(){
		ClockState temp = state.ClickButtonB();
		if(temp != null)
			state = temp;
		
		setChanged();
		notifyObservers();
	}
	//clickButtonB end
	
	//Retorna o status de acordo com o estado corrente
	public String status()
	{
		return state.status();
	}
	//status end
	
	
	//Incrementa a hora, alterando em todos os Observeds
	public void increaseHour()
	{
		timer.increaseHour();
	}
	//increaseHour end
	
	//Incrementa o minuto, alterando em todos os Observeds
	public void increaseMinute()
	{
		timer.increaseMinute();
	}
	//increaseMinute end
	
	//retorna a hora corrente
	public int getHour()
	{
		return timer.getHour();
	}
	//getHour end
	
	//Retorna quantos segundos o relogio se encontra
	public int getSecond()
	{
		return timer.getSecond();
	}
	//getSecond end
	
	//retorna quantos minutos o relogio se encontra
	public int getMinute()
	{
		return timer.getMinute();
	}
	//getMinute end
	
	//Retorna os segundos totais (usado pro ponteiro de hora correr
	// conforme os segundos passam).
	public int getSecondsAtAll()
	{
		return timer.getSecondsAtAll();
	}
	//getSecondsAtAll end
	
	//Singleton
	public static facade getInstance()
	{
		return INSTANCE;
	}
	//Singleton end
	
	//Recebe a ação feita pelo usuário e passa para as classes responsáveis
	public Color buttonClick(Color a, Color b,int clicked) // valida as cores dos botoes
	{  
		if(a==Color.GRAY && b == Color.GRAY && clicked == 1)
		{
			clickButtonA();
			digitalPanel.getInstance().changeLabelColor(this);
			return Color.GREEN;
		}
		else if(a==Color.GRAY && b == Color.GRAY && clicked == 2)
		{
			clickButtonB();
			return Color.GREEN;
		}
		else if(a==Color.GREEN && b == Color.GRAY && clicked == 1)
		{
			//digital.changeLabelColor(timer);
			clickButtonA();
			return Color.GRAY;
		}
		else if(a==Color.GREEN && b == Color.GRAY && clicked == 2)
		{
			clickButtonB();
			return Color.GRAY;
		}
		else if(a==Color.GRAY && b== Color.GREEN && clicked == 1)
		{
			clickButtonA();
			return Color.GRAY;
		}
		else //(a.getBackground()==Color.GRAY && b.getBackground() == Color.GREEN && clicked == 2)
		{
			clickButtonB();
			return Color.GRAY;
		}
	}
	//buttonClick end


	@Override
	public void update(Observable timerInfSubject, Object arg1) {
		if( timerInfSubject instanceof timer)
		{
			setChanged();
			notifyObservers();
		}
		
	}
}
