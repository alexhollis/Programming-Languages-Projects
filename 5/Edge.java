public class Edge
{
    private String sourceNodeLabel;
    private String destinationNodeLabel;
    
    public Edge(String sourceNodeLabel, String destinationNodeLabel)
    {
        this.setSourceNodeLabel(sourceNodeLabel);
        this.setDestinationNodeLabel(destinationNodeLabel);
    }
    public synchronized void setSourceNodeLabel(String sourceNodeLabel)
    {
        this.sourceNodeLabel = sourceNodeLabel;
    }
    public synchronized String getSourceNodeLabel()
    {
        return this.sourceNodeLabel;
    }
    public synchronized void setDestinationNodeLabel(String destinationNodeLabel)
    {
        this.destinationNodeLabel = destinationNodeLabel;
    }
    public synchronized String getDestinationNodeLabel()
    {
        return this.destinationNodeLabel;
    }
}
