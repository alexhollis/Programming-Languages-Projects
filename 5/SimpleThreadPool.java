import java.util.concurrent.*;
/**
 * A simple thread pool.
 * @author Brian Mauldin
 */
public class SimpleThreadPool
{
	WorkerThread[] pool;
	ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<Runnable>(50);
	
	/**
     * @Constructor
     */
	public SimpleThreadPool(int numThreads)
	{
		pool = new WorkerThread[numThreads];
		for (int i = 0; i < numThreads; i++)
		{
			pool[i] = new WorkerThread(tasks);
			pool[i].start();
		}
	}
	
	/**
     * Enqueue a new Runnable task into the thread pool to be executed immediately
     * @return void
     */
	public void queueTask(Runnable task)
	{
		tasks.add(task);
	}
	
	/**
     * Commands the thread pool to shut down all its workers and die gracefully
     * @return void
     */
	public void shutDown()
	{
		for (int i = 0; i < pool.length; i++)
		{
			pool[i].interrupt();
			pool[i].stopThread();
		}
	}
}
