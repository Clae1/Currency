public class Demo 
{
    //Create a new instance of Function class to allow the User class to use the functions
    Functions Graph = new Functions();

    //This keeps track of the number of test that have been done
    public static int count = 1;

    void testDemo(double[][] graph, int start, int end)
    {
        System.out.println("________________________________________________");
        System.out.println("Test "+count);
        double[][] copyGraph = Graph.setupGraph(graph); //copyGraph will recieve an updated graph 
        double[][] shortGraph = Graph.floydAlgorithm(copyGraph); //shortGraph will recieve the updated graph so that copyGraph is not affected by the changes of floyd-warshall
        double[] pathValue = Graph.pathValue(start, end, shortGraph); //pathValue array will recieve the the currency exchange rate of the sequence based on the starting node and ending node
        String[] pathCurrency = Graph.pathCreation(start, end, shortGraph, pathValue, graph); //pathCurrency array will recieve the the currencies in the sequence based on the starting node and ending node

        //Check if there is arbitrage opportunity 
        //If there is a arbitrage opportunity run the code inside the IF statement
        if (Graph.checkNegativeCycle(pathValue, graph, start, end) == true)
        {
            System.out.println("!!!Arbitrage opportunity found!!!\n");
            Graph.printGraph(graph); //Prints the original graph 
            Graph.printPathCurrecny(pathCurrency, start, end); //Prints the Currencies in the sequence
            Graph.printPathCurrecnyRate(pathValue, start, end); //Prints the currency exchange rate in the sequence 

            //Return the arbitrage value of the sequence
            System.out.println("\n\nArbitrage return: " + Graph.findingAbitrageValue(pathValue, graph, start, end));
        }

        //If the returned value is false, there is no arbitrage opportunity
        else 
        {
            System.out.println("No Arbitrage opportunity found\n");
            Graph.printGraph(graph);//Prints the original userGraph 

            //Return the best exchange rate 
            Graph.findingExchangeValue(start, end, graph, pathValue);

            Graph.printPathCurrecny(pathCurrency, start, end); //Prints the Currencies in the sequence 
            Graph.printPathCurrecnyRate(pathValue, start, end); //Prints the currency exchange rate in the sequence 
        }
        System.out.println("\n________________________________________________\n");
        count++;
    }
}
