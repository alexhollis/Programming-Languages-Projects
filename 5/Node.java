import java.util.*;
public class Node
{
    private String label;
    private double value;
    private ArrayList<Edge> edges;
   
    public Node(String label, double value)
    {
    	edges = new ArrayList<Edge>();
        this.label = label;
        this.value = value;
    }
    public Node(String label, ArrayList<Edge> edges)
    {
        this.label = label;
        this.edges = edges;
    }
    public synchronized void setLabel(String label)
    {
        this.label = label;
    }
    public synchronized void setValue(double value)
    {
        this.value = value;
    }
    public synchronized double getValue()
    {
        return this.value;
    }
    public synchronized String getLabel()
    {
        return this.label;
    }
    public synchronized void addEdge(Edge edge)
    {
        this.edges.add(edge);
    }
    public synchronized ArrayList<Edge> getEdges()
    {
        return this.edges;
    }
    public synchronized ArrayList<Edge> getOutgoingEdges()
    {
        ArrayList<Edge> outEdges = new ArrayList<Edge>();
        for (Edge edge : this.edges)
        {
            if (edge.getDestinationNodeLabel() != this.label)
            {
                outEdges.add(edge);
            }
        }
        return outEdges;
    }
    public synchronized ArrayList<Edge> getIncomingEdges()
    {
        ArrayList<Edge> inEdges = new ArrayList<Edge>();
        for (Edge edge : this.edges)
        {
            if (edge.getDestinationNodeLabel() == this.label)
            {
                inEdges.add(edge);
            }
        }
        return inEdges;
    }
    public String toString()
    {
        return new String(label + ": " + String.valueOf(value));
    }
}