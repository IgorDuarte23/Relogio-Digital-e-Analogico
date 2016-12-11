package State;


public class ClockBPressed extends ClockState {

	@Override
	public String status() {
		return State.Bpressed.toString();
	}

	@Override
	public ClockState ClickButtonB() {

		if(!bManager.AlreadyChangedHour)
			return new ClockChangeHour();
		
		else
			return new ClockChangeMinute();
	}

	@Override
	public ClockState ClickButtonA() {
		return null;
	}

}
