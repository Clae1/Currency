public class Main 
{
    public static int size;
    public static int[][] path;

    //Will copy the inputted graph into a new graph
    public double[][] setupGraph(double[][] inputGraph)
    {
        size = inputGraph.length;
        double[][] newGraph = new double[size][size];

        path = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                //If I and J are the same value, suggesting that we are on a node 
                if (i == j)
                {
                    //If condition is true, make it so that at position [i][j] has 
                    //a value of zero
                    newGraph[i][j] = 0;
                }

                //If I and J are not equal to each other copy the value from position [i][j]
                //to the new adjacency matrix newGraph in the same position. 
                else if (inputGraph[i][j] != 0)
                {
                    newGraph[i][j] = inputGraph[i][j];
                }

                //Insert infinity value to suggest that there is no edge 
                //between the current node and another node 
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

        //Just for debugging
        int[][] test = new int[size][size];
        test = path;

        //Copies the weights of each edge to a graph that will hold shortest distances between
        //the nodes. 
        for (int i = 0; i < size; ++i) 
        {
            for (int j = 0; j < size; ++j) 
            {
                shortGraph[i][j] = newGraph[i][j];
                path[i][j] = j;

                if (i == j)
                {
                    path[i][j] = 0;
                }
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
                        
                        path[i][j] = path[i][k];
                        test[i][j] = test[i][k]; //for debugging 
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
                if (graph[i][j] < 0 && i==j)
                {
                    System.out.println("There is a negative cycle");
                    return true; 
                }
            }
        }
        return true; 
    }

    //Construct a path that shows the shortest path from starting node to
    //end node. 
    public int[] pathCreation(int start, int end, double[][] graph)
    {
        int[] tempPath = new int[size];

        //Will return an empty path to suggest that there is no path 
        //connecting the start and end node
        if (graph[start][end] == Double.POSITIVE_INFINITY)
        {
            return tempPath;
        }

        int current = start;
        int position = 0;
        for (; current != end; current = path[current][end])
        {
            if (current == -1)
            {
                return null;
            }
            tempPath[position] = current;
            position++;
        }

        if (path[current][end] == -1)
        {
            return null;
        }
        tempPath[position] = end;
        return tempPath;
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
        // double[][] graph = {
        //     {0, 3, 8, 0, -4},
        //     {0, 0, 0, 1, 7},
        //     {0, 4, 0, 5, 11},
        //     {2, 5, -5, 0, -2},
        //     {0, 0, 0, 6, 0}
        // };

        //test 2, there is a negative cycle 
        // double[][] graph = {
        //     {0, 1, 0, 0},
        //     {0, 0, -1, 1},
        //     {0, 0, 0, 3},
        //     {-3, 0, 0, 0}
        // };

        // double[][] graph = {
        // //   S  A   B  C  D  E
        //     {0, 10, 0, 3, 0, 0}, 
        //     {0, 0,  2, 0,-3, 0}, 
        //     {0,-1,  0, 0, 0, 0},
        //     {0, 0,  0, 0, 3, 0},
        //     {0, 0,  4, 0, 0, 1},
        //     {0, 0,  4, 0, 0, 0},
        // };

        double[][] graph = {
            //    0   1   2  3  4  5  6
                { 0,  2,  0,  0, 0, 0, 2},
                { 0,  0,  0, -1, 0, 0, 0},
                {-3,  1,  0,  0, 0, 0, 3},
                { 1,  0,  4,  0, 0, 2, 0},
                { 0,  0,  4,  0, 0, 0, 0},
                { 0,  0,  5,  0, 2, 0, 0},
                { 0,  0,  0,  0, 0, 0, 0},
            };


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

        System.out.println("\n");
        int[] currentPath = getGraph.pathCreation(3, 0, updatedGraph);
        for (int i = 0; i < currentPath.length; i++)
        {
            System.out.print(currentPath[i] + " ");
        }
    }
}
