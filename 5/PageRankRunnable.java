import java.util.ArrayList;
public class PageRankRunnable implements Runnable
{
    private SimpleThreadPool threadPool;
    private ArrayList<Node> graph;
    private ArrayList<Node> newGraph;
    private int startIndex;
    private int endIndex;
    
    private double dampingFactor = 0.85;
    
    /**
    * @Constructor
    */
    public PageRankRunnable(SimpleThreadPool threadPool, ArrayList<Node> graph, int startIndex, int endIndex)
	{
		this.threadPool = threadPool;
		this.graph = graph;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.newGraph = new ArrayList<Node>(0);
	}
	
	public ArrayList<Node> getGraph()
	{
		return this.graph;
	}
	
	public void setGraph(ArrayList<Node> graph)
	{
		this.graph = graph;
	}
	public ArrayList<Node> getNewGraph()
	{
		return this.newGraph;
	}
	
	/**
	 * Main PageRank algorithm
	 * @return void
	*/
	public void calcPageRank()
	{
		this.newGraph = new ArrayList<Node>(this.endIndex - this.startIndex + 1);
		double pageRankSum = 0;
		int newGraphPosition = 0;
		for (int i = this.startIndex; i < this.endIndex; i++) // our subarray of nodes
		{
			this.newGraph.add(this.graph.get(i));
			for (Edge incomingEdge: this.graph.get(i).getIncomingEdges()) // get the incoming edges for each node in this subarray
			{
				for (int a = 0; a < this.graph.size(); a++) // 
				{
					for (Edge outgoingEdges: this.graph.get(a).getOutgoingEdges())
					{
						if (incomingEdge.getDestinationNodeLabel() == outgoingEdges.getDestinationNodeLabel())
						{
							pageRankSum = pageRankSum + (this.graph.get(a).getValue()/this.graph.get(a).getOutgoingEdges().size());
						}
					}
				}
			}
			this.newGraph.get(newGraphPosition).setValue(((1 - this.dampingFactor)/this.graph.size()) + (this.dampingFactor * pageRankSum)); // the main calc
			newGraphPosition++;
			pageRankSum = 0;
		}
		System.out.println("done");
	}
	
	public void splitData()
	{
		//if ((this.endIndex - startIndex) == (graph.size()/4) || (this.endIndex - this.startIndex) < (graph.size()/4) || this.endIndex < 4)
		//{
			System.out.println("no thread split");
			this.calcPageRank();
		//}
		/*
		else
		{
			System.out.println("split");
			ArrayList<Node> left = this.graph;
			ArrayList<Node> right = this.graph;
			PageRankRunnable leftR = new PageRankRunnable(this.threadPool, left, this.startIndex, this.endIndex/2);
			this.threadPool.queueTask(leftR);
			PageRankRunnable rightR = new PageRankRunnable(this.threadPool, right, this.endIndex/2 + 1, this.endIndex);
			this.threadPool.queueTask(rightR);
			this.newGraph.addAll(leftR.getGraph());
			this.newGraph.addAll(rightR.getGraph());
		}
		*/
	}
	
	/**
     * Main entry point for the Runnable object
     * @return void
     */
	public void run()
	{
		this.splitData();
	}
}