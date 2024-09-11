public class Demo 
{
    Functions Graph = new Functions();

    void testDemo(double[][] graph, int start, int end)
    {
        System.out.println("________________________________________________");
        double[][] copyGraph = Graph.setupGraph(graph);
        double[][] shortGraph = Graph.floydAlgorithm(copyGraph);
        double[] pathValue = Graph.pathValue(start, end, shortGraph);
        String[] pathCurrency = Graph.pathCreation(start, end, shortGraph, pathValue);

        //check for negative cycle
        //If there is a negative cycle run the code inside the IF statement
        if (Graph.checkNegativeCycle(pathValue) == true)
        {
            System.out.println("!!!Arbitrage opportunity found!!!\n");
            Graph.printGraph(shortGraph);
            Graph.printPathCurrecny(pathCurrency, start, end);
            Graph.printPathCurrecnyRate(pathValue, start, end);
        }

        //If the returned value is false, there is no negative cycle 
        else 
        {
            System.out.println("No Arbitrage opportunity found\n");
            Graph.printGraph(shortGraph);
            Graph.findingExchangeValue(start, end, shortGraph);
            Graph.printPathCurrecny(pathCurrency, start, end);
            Graph.printPathCurrecnyRate(pathValue, start, end);
        }
        System.out.println("\n________________________________________________\n");
    }
}
