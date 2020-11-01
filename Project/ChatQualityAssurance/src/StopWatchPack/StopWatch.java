package StopWatchPack;

public class StopWatch implements IStopWatch {

	private static long startTime = 0;
	private static long stopTime = 0;
	private static boolean running = false;

	// Get execute time of a thread
	public static long getExecuteTime(Thread thread) {

		IStopWatch stopWatch = new StopWatch();
		stopWatch.Start();

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stopWatch.Stop();

		return stopWatch.getTime();
	}

	// Get execute time of thread + time out
	public static long getExecuteTime(Thread thread, long timeout)
	{
		return timeout;
		
	}
	
	@Override
	public void Start() {

		running = true;
		// Get system time
		startTime = System.currentTimeMillis();

	}

	@Override
	public void Stop() {

		running = false;
		// Get system time
		stopTime = System.currentTimeMillis();

	}

	@Override
	public long getTime() {
		return stopTime - startTime;
	}

	@Override
	public long getTimeSlipt() {
		// If still running
		if (running)
			return System.currentTimeMillis() - startTime;
		return getTime();
	}

	@Override
	public boolean Running() {
		return running;
	}

}
