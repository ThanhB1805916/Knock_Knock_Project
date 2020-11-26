package StopWatchPack;

public interface IStopWatch {

	public void Start();

	public void Stop();

	//Return the total time (StopTime - StartTime)
	public long getTime();
	
	//Return current time - start time
	public long getTimeSlipt();
	
	public boolean Running();
	
}
