public class Functions {
    public static int size;
    public static int[][] path;

    // Will copy the inputted graph into a new graph
    public double[][] setupGraph(double[][] inputGraph) {
        size = inputGraph.length;
        double[][] newGraph = new double[size][size];

        path = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // If I and J are the same value, suggesting that we are on a node
                if (i == j) {
                    // If condition is true, make it so that at position [i][j] has
                    // a value of zero
                    newGraph[i][j] = 0;

                }

                // If I and J are not equal to each other copy the value from position [i][j]
                // to the new adjacency matrix newGraph in the same position.
                else if (inputGraph[i][j] != 0) {
                    newGraph[i][j] = inputGraph[i][j];
                }

                // Insert infinity value to suggest that there is no edge
                // between the current node and another node
                else {
                    newGraph[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return newGraph;
    }

    // Add new function for floyd-warshall algorithm
    public double[][] floydAlgorithm(double[][] newGraph) {
        double[][] shortGraph = new double[size][size];

        // Copies the weights of each edge to a graph that will hold shortest distances
        // between
        // the nodes.
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                shortGraph[i][j] = newGraph[i][j];
                path[i][j] = j;
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (shortGraph[i][k] + shortGraph[k][j] < shortGraph[i][j]) {
                        shortGraph[i][j] = shortGraph[i][k] + shortGraph[k][j];

                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        return shortGraph;
    }

    // Finding if the graph has a negative cycle
    public boolean checkNegativeCycle(double[][] graph) {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) 
            {
                if (graph[i][j] < 0 && i == j) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    // Construct a path that shows the shortest path from starting node to
    // end node.
    public String[] pathCreation(int start, int end, double[][] graph)
    {
        String[] tempPath = new String[size];

        //Will return an empty path to suggest that there is no path 
        //connecting the start and end node
        if (graph[start][end] == Double.POSITIVE_INFINITY) 
        {
            return tempPath;
        }

        int current = start;
        int position = 0;
        while (current != end && position < tempPath.length-1)
        {
            tempPath[position] = numberConverter(current);
            current = path[current][end];
            position++;
        }

        if (checkNegativeCycle(graph) == false)
        {
            tempPath[position] = numberConverter(end);
        }

        else if (checkNegativeCycle(graph) == true)
        {
            tempPath[position] = numberConverter(current);
        }
        return tempPath;
    }

    void findingExchangeValue(int start, int end, double[][] graph)
    {
        double exchangeValue = 0.0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) 
            {
                if (graph[i][j] == graph[start][end])
                {
                    exchangeValue = graph[i][j];
                }
            }
        }
        System.out.println("\nBest conversion rate: " + exchangeValue);
    }

    public String numberConverter(int number) {
        String convert = "";
        switch (number) {
            case 0:
            convert = "NZD";
                break;
                
            case 1:
            convert = "USD";
                break;
                
            case 2:
            convert = "AUS";
                break;

            case 3:
            convert = "CAN";
                break;
                
            case 4:
            convert = "GBP";
                break;
                
            case 5:
            convert = "SDP";
                break;

            case 6:
            convert = "YEN";
                break;

            case 7:
            convert = "PHP";
                break;
            
            default:
                break;
        }
        return convert;
    }

    // Prints the adjaency matrix
    void printGraph(double[][] graph) {
        System.out.println("Updated Graph with Shortest paths");
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (graph[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("INF ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    void printPath(String[] path, int start, int end) 
    {
        System.out.println("\nHere is the sequence of exchanges");
        for (int i = 0; i < path.length; i++) 
        {
            if (path[i] != null && i == path.length-1) {
                System.out.print(path[i]);
            }
            else if (path[i] != null) {
                System.out.print(path[i] + "->");
            }
        }
    }

}
