package State;

public class ClockRunning extends ClockState {
	
	@Override
	public String status() {
		return State.Running.toString();
	}

	@Override
	public ClockState ClickButtonA() {
		bManager.stopTimer();
		return new ClockAPressed();
	}

	@Override
	public ClockState ClickButtonB() {
		return null;
	}

}
