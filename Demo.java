public class Demo 
{
    Functions Graph = new Functions();

    void testDemo(double[][] graph, int start, int end)
    {
        System.out.println("________________________________________________");
        double[][] copyGraph = Graph.setupGraph(graph);
        double[][] shortGraph = Graph.floydAlgorithm(copyGraph);
        String[] path1 = Graph.pathCreation(start, end, shortGraph);

        //check for negative cycle
        //If there is a negative cycle run the code inside the IF statement
        if (Graph.checkNegativeCycle(shortGraph) == true)
        {
            System.out.println("!!!Arbitrage opportunity found!!!\n");
            Graph.printGraph(shortGraph);
            Graph.printPath(path1, start, end);
        }

        //If the returned value is false, there is no negative cycle 
        else 
        {
            System.out.println("No Arbitrage opportunity found\n");
            Graph.printGraph(shortGraph);
            Graph.findingExchangeValue(start, end, shortGraph);
            Graph.printPath(path1, start, end);
        }
        System.out.println("\n________________________________________________\n");
    }
}
