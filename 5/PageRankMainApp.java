import java.util.*;
import java.io.*;
/**
 * PageRank parallelized
 * @author Brian Mauldin, Ronnie, Supriya, Alex Hollis
 */
public class PageRankMainApp
{

    public static void main(String[] args) throws InterruptedException
    {
    	System.out.println("Hello, World!");
        ArrayList<Node> graph = new ArrayList<Node>();
        
        graph.add(new Node("A", .09091));
        graph.get(0).addEdge(new Edge("D", "A")); // incoming link
        graph.get(0).addEdge(new Edge("A", "B")); // outgoing...
        graph.get(0).addEdge(new Edge("A", "C")); // ...
        graph.get(0).addEdge(new Edge("A", "D"));
        graph.get(0).addEdge(new Edge("A", "E"));
        graph.get(0).addEdge(new Edge("A", "F"));
        graph.get(0).addEdge(new Edge("A", "m1"));
        graph.get(0).addEdge(new Edge("A", "m2"));
        graph.get(0).addEdge(new Edge("A", "m3"));
        graph.get(0).addEdge(new Edge("A", "m4"));
        graph.get(0).addEdge(new Edge("A", "m5"));
        
        
        graph.add(new Node("B", .09091));
        graph.get(1).addEdge(new Edge("B","C"));
        graph.get(1).addEdge(new Edge("C","B"));
        graph.get(1).addEdge(new Edge("F","B"));
        graph.get(1).addEdge(new Edge("E","B"));
        graph.get(1).addEdge(new Edge("m3","B"));
        graph.get(1).addEdge(new Edge("m2","B"));
        graph.get(1).addEdge(new Edge("m1","B"));
        graph.get(1).addEdge(new Edge("D","B"));
        
        
        graph.add(new Node("C", .09091));
        graph.get(2).addEdge(new Edge("C","B"));
        graph.get(2).addEdge(new Edge("B","C"));
        
        
        graph.add(new Node("D", .09091));
        graph.get(3).addEdge(new Edge("D","A"));
        graph.get(3).addEdge(new Edge("D","B"));
        graph.get(3).addEdge(new Edge("E","D"));
        
        graph.add(new Node("E", .09091));
        graph.get(4).addEdge(new Edge("E","B"));
        graph.get(4).addEdge(new Edge("E","D"));
        graph.get(4).addEdge(new Edge("E","F"));
        graph.get(4).addEdge(new Edge("F","E"));
        graph.get(4).addEdge(new Edge("m1","E"));
        graph.get(4).addEdge(new Edge("m2","E"));
        graph.get(4).addEdge(new Edge("m3","E"));
        graph.get(4).addEdge(new Edge("m4","E"));
        graph.get(4).addEdge(new Edge("m5","E"));
        

        graph.add(new Node("F", .09091));
        graph.get(5).addEdge(new Edge("F","E"));
        graph.get(5).addEdge(new Edge("E","F"));
        graph.get(5).addEdge(new Edge("F","B"));
        
        graph.add(new Node("m1", .09091));
        graph.get(6).addEdge(new Edge("m1","B"));
        graph.get(6).addEdge(new Edge("m1","E"));
        
        graph.add(new Node("m2", .09091));
        graph.get(7).addEdge(new Edge("m2","B"));
        graph.get(7).addEdge(new Edge("m2","E"));
        
        
        graph.add(new Node("m3", .09091));
        graph.get(8).addEdge(new Edge("m3","B"));
        graph.get(8).addEdge(new Edge("m3","E"));
        
        graph.add(new Node("m4", .09091));
        graph.get(9).addEdge(new Edge("m4","E"));
        
        graph.add(new Node("m5", .09091));
        graph.get(10).addEdge(new Edge("m5","E"));
        
        SimpleThreadPool tPool = new SimpleThreadPool(0);
        PageRankRunnable pRank = new PageRankRunnable(tPool, graph, 0, graph.size());
        System.out.println("here2");
        
        for (int i = 0; i < 50; i++) {//run many times
        	pRank.run();
        	pRank.setGraph(pRank.getNewGraph());
	     }
        System.out.println("here2");
        //Thread.sleep(4000);
        tPool.shutDown();
        System.out.println("here2");
        for (Node node: pRank.getGraph())
        {
        	System.out.println(node.toString());
        }
    }
    
   /** 
    public void parseStory() throws Exception
    {
        BufferedReader stops = new BufferedReader(new FileReader(new File("stops.txt")));
        BufferedReader story = new BufferedReader(new FileReader(new File("story.txt")));
        String stopsString = new String();
        String storyString = new String();
        String line = new String();
        while ((line = stops.readLine()) != null)
        {
            stopsString.concat(" ");
            stopsString.concat(line);
        }
        
        while ((line = stops.readLine()) != null)
        {
            storyString.concat(" ");
            storyString.concat(line);
        }
        
        
    }
    */
    
}