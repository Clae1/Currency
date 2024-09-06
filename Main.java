public class Main 
{
    public static int size; 
    public static float[][] newGraph;

    public float[][] setupGraph(float[][] inputGraph)
    {
        size = inputGraph.length;
        newGraph = new float[size][size];
        
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
            }
        }
        return newGraph;
    }

    public static void main(String[] args) 
    {
        Main getGraph = new Main();

        //Around here is where we have the printf and scanf statements 
        //to all for user input for the graph. 
        //Here is where we have the input test 
        float[][] graph = {
            {0, 3, 8, 0, -4},
            {0, 0, 0, 1, 7},
            {0, 4, 0, 5, 11},
            {2, 5, -5, 0, -2},
            {0, 0, 0, 6, 0}
        };

        //To input the user inputgraph into setup graph.
        //This will copy the inputgraph into a new graph
        getGraph.setupGraph(graph);

        // Print the adjacency matrix
        for (int i = 0; i < size; ++i) 
        {
            for (int j = 0; j < size; ++j) 
            {
                System.out.print(newGraph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
