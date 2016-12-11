package State;

import Controllers.facade;

public abstract class ClockState {

	public static facade bManager;
	
	public static ClockState getInicialState(facade facade)
	{
		bManager = facade;
		return new ClockRunning();
	}
	
	public abstract String status();
	public abstract ClockState ClickButtonA();
	public abstract ClockState ClickButtonB();


}
