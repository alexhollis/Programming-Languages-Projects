import java.util.concurrent.*;
/**
 * Worker thread to use with the SimpleThreadPool class
 * @author Brian Mauldin
 */
public class WorkerThread extends Thread 
{
	private ArrayBlockingQueue<Runnable> taskQueue;
	private boolean stopped = false;
	private boolean fullyStopped = false;
	
	/**
     * @Constructor
     */
	public WorkerThread(ArrayBlockingQueue<Runnable> taskQueue)
	{
		this.taskQueue = taskQueue;
	}
	
	/**
     * Main entry point for the Thread object.
     * @return void
     */
	public void run ()
	{
		while (!stopped)
		{
			try
			{
				Runnable task = (Runnable) this.taskQueue.take();
				task.run();
			}
			catch (InterruptedException ie)
			{
				continue;
			}
			catch (Exception e)
			{
				continue;
			}
		}
		fullyStopped = true;
	}
	
	/**
     * Stops the thread from looping and causes it to shut down gracefully
     * @return void
     */
	public synchronized void stopThread()
	{
		stopped = true;
		waitForStop();
	}
	
	/**
     * Called by the thread pool that manages the worker, thread pool waits for the thread to shut down
     * gracefully.
     * @return void
     */
	private void waitForStop()
	{
		while (!fullyStopped)
		{
			try
			{
				sleep(1);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
