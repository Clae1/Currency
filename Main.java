public class Main 
{
    public static int size; 

    //Will copy the inputted graph into a new graph
    public double[][] setupGraph(double[][] inputGraph)
    {
        size = inputGraph.length;
        double[][] newGraph = new double[size][size];
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == j)
                {
                    newGraph[i][j] = 0;
                }
                else if (inputGraph[i][j] != 0)
                {
                    newGraph[i][j] = inputGraph[i][j];
                }
                else
                {
                    newGraph[i][j] = Double.POSITIVE_INFINITY; 
                }
            }
        }
        return newGraph;
    }

    //Add new function for floyd-warshall algorithm
    public double[][] floydAlgorithm(double[][] newGraph)
    {
        double[][] shortGraph = new double[size][size];

        for (int i = 0; i < size; ++i) 
        {
            for (int j = 0; j < size; ++j) 
            {
                shortGraph[i][j] = newGraph[i][j];
            }
        }

        for (int k = 0; k < size; k++)
        {
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    if (shortGraph[i][k] + shortGraph[k][j] < shortGraph[i][j])
                    {
                        shortGraph[i][j] = shortGraph[i][k] + shortGraph[k][j];
                    }
                }
            }
        }
        return shortGraph;
    }

    //Finding if the graph has a negative cycle 
    public boolean checkNegativeCycle(double[][] graph)
    {
        for (int i = 0; i < size; ++i) 
        {
            for (int j = 0; j < size; ++j) 
            {
                if (graph[i][j] > 0)
                {
                    System.out.println("There is a negative cycle");
                    return true; 
                }
            }
        }
        return true; 
    }

    //Prints the adjaency matrix 
    void printGraph(double[][] graph)
    {
        System.out.println("A   B    C   D    E");
        for (int i = 0; i < size; ++i) 
        {
            for (int j = 0; j < size; ++j) 
            {
                if (graph[i][j] == Double.POSITIVE_INFINITY)
                {
                    System.out.print("INF ");
                }
                else
                {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) 
    {
        Main getGraph = new Main();

        //Around here is where we have the printf and scanf statements 
        //to all user inputs for the graph. 
        
        //Here is where we have the input test 
        //test 1, no negative cycle
        double[][] graph = {
            {0, 3, 8, 0, -4},
            {0, 0, 0, 1, 7},
            {0, 4, 0, 5, 11},
            {2, 5, -5, 0, -2},
            {0, 0, 0, 6, 0}
        };

        //test 2, there is a negative cycle 
        // double[][] graph = {
        //     {0, 1, 0, 0},
        //     {0, 0, -1, 1},
        //     {0, 0, 0, 3},
        //     {-3, 0, 0, 0}
        // };

        //To input the user inputgraph into setup graph.
        //This will copy the inputgraph into a new graph
        double[][] newGraph = getGraph.setupGraph(graph);
        
        // Print the adjacency matrix
        System.out.println("Intial Graph");
        getGraph.printGraph(newGraph);

        //Here we must implement the floyd-warshall algorithm 
        //by creating a new function that will take in the newGraph 
        //and retrun a float adjanecy matrix with the shortest paths.
        double[][] updatedGraph = getGraph.floydAlgorithm(newGraph);
        System.out.println("\nUpdated graph with shortest path");
        getGraph.printGraph(updatedGraph);
        getGraph.checkNegativeCycle(updatedGraph);
    }
}
