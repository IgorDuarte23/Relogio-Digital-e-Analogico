package State;

public class ClockChangeMinute extends ClockState {
	
	@Override
	public String status() {
		return State.ChangeMinute.toString();
	}

	@Override
	public ClockState ClickButtonB() {
		bManager.increaseMinute();
		return new ClockBPressed();
	}
	public ClockState ClickButtonA()
	{
		bManager.AlreadyChangedMinute = true;
		return new ClockAPressed();
	}
}
