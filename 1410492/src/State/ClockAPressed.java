package State;


public class ClockAPressed extends ClockState {
	public String status()
	{
		return State.Apressed.toString();
	}

	@Override
	public ClockState ClickButtonA() {
		if(!bManager.AlreadyChangedHour) //modo de alteracao das horas
		{
			return new ClockChangeHour();
		}
		else if (!bManager.AlreadyChangedMinute)//modo de alteracao dos minutos
		{
			return new ClockChangeMinute();
		}
		else // modo de exibição
		{
			bManager.AlreadyChangedHour = false;
			bManager.AlreadyChangedMinute = false;
			bManager.startTimer();
			return new ClockRunning();
		}
	}

	@Override
	public ClockState ClickButtonB() {
		// TODO Auto-generated method stub
		return null;
	}


}
