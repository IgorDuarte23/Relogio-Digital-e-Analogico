package State;

public class ClockChangeHour extends ClockState {
	
	@Override
	public String status() {
		return State.ChangeHour.toString();
	}

	@Override
	public ClockState ClickButtonB() {
		bManager.increaseHour();
		return new ClockBPressed();
	}

	public ClockState ClickButtonA()
	{
		bManager.AlreadyChangedHour = true;
		return new ClockAPressed();
	}
}
