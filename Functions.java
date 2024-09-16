public class Functions 
{
    public static int size; //For the size of the array 

    //These adjacency matrixes are used for a similar purpose of keeping track of 
    //the currencies and currency exchange rates in the current sequence
    public static int[][] path; 
    public static double[][] pathRate; 

    public static boolean cycleChecker; //Used to indicate whether the current sequence has arbitrage sequence 

    // Will copy the inputted graph into a new graph
    public double[][] setupGraph(double[][] inputGraph) 
    {
        //Make it so that the size is equal to the length of the inputted graph 
        size = inputGraph.length;

        //Initialzing these values by the size of the array so that values can be added into 
        //these 2D-arrays 
        double[][] newGraph = new double[size][size]; 
        path = new int[size][size];
        pathRate = new double[size][size];

        //This nested FOR loop purpose is to copy the contents of the inputGraph to the newGraph. 
        //There is certain If or else statments that affect what values can be inserted into the 
        //adjaency matrix. 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // If I and J are the same value, suggesting that we are on a node
                if (i == j) {
                    // If condition is true, make it so that at position [i][j] has
                    // a value of zero
                    newGraph[i][j] = 1;
                    path[i][j] = i;
                }

                // If I and J are not equal to each other, copy the value from position [i][j]
                // to the new adjacency matrix newGraph in the same position.
                else if (inputGraph[i][j] != 0) {
                    newGraph[i][j] = inputGraph[i][j];

                    //Populate the path with the current number of j 
                    path[i][j] = j;

                    //Populate the pathRate with currency exchange rate values from the inputted Graph
                    pathRate[i][j] = inputGraph[i][j];
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

    //The purpose of this function is to find the shortest path from one node to another node,
    //slowly improving the path and updating path, pathRate and shortGraph if an improvement in the 
    //path can be made. 
    public double[][] floydAlgorithm(double[][] newGraph) 
    {
        //Intialize the shortGraph with size of the inputted graph 
        double[][] shortGraph = new double[size][size];

        // Copies the weights of each edge to a graph that will hold shortest distances
        // between the nodes.
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                shortGraph[i][j] = newGraph[i][j];
            }
        }

        //The most important part of the floyd-warshall algorithm is the K loop as it 
        //allows the algoritm to iterate through all the nodes of the shortGraph adjacency matrix 
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) 
                {
                    //This is the main part of the floyd-warshall algorithm. K represented as the intermediate node between [i] and [j],
                    //by passing through K is the [i][k] + [k][j] less than the current value of [i][j]. 
                    //If the condition is true then run the code in the statement, if condition is false then passing through K will not improve
                    //the path. 
                    if (shortGraph[i][k] + shortGraph[k][j] < shortGraph[i][j]) 
                    {
                        //By passing through K, the combined sum of [i][k] + [k][j] will be inserted to shortGraph[i][j]
                        shortGraph[i][j] = shortGraph[i][k] + shortGraph[k][j];

                        //Both path and PathRate are adjusted by [i][k], due to passing through K 
                        path[i][j] = path[i][k];
                        pathRate[i][j] = pathRate[i][k];
                    }
                }
            }
        }
        //This will return an updated graph with the shortest path 
        return shortGraph;
    }

    // Finding if the sequence from start to end have a arbitrage opportunity
    public boolean checkNegativeCycle(double[] pathValue, double[][] graph, int start, int end) 
    {
        //Intialize negativeLog to 0.0 as we do not want to add extra values 
        //when calcualting for negativeLog 
        double negativeLog = 0.0;
        
        //For loop will iterate through pathValue array 
        for (int i = 0; i < pathValue.length; i++)
        {
            //To ensure that only currency exchange rate values are accounted for 
            if (pathValue[i] != 0)
            {
                //Calculate the negative logarithm of each currency exhange rate and add 
                //the values to negativeLog
                negativeLog += -Math.log10(pathValue[i]);
            }
        }

        //Check if negative log is less than 0
        //If true then there is a possibility that there is a arbitrage opportunity 
        if (negativeLog < 0)
        {
            //We will double check that the sequence has arbitrage opportunity by adding 
            //the starting node exchange rate node to negativeLog, since arbitrage opportunity 
            //is a cycle that increase the starting currency. 
            double negativelogCheck = negativeLog;
            negativelogCheck += -Math.log10(graph[end][start]);

            //If by adding the starting currency exhange the negativelogCheck is less than 0 
            //means that there is a arbitrage opportunity 
            if (negativelogCheck < 0)
            {
                //Make cycleChecker = true so that we do not have to call this function again 
                //for other functions 
                cycleChecker = true;
                return true;
            }
        }

        //If condition above is false,
        //then return false to indicate there is no arbitrage opportunity
        cycleChecker = false;
        return false;
    }

    //Construct a path that shows the exchange values from starting currency to 
    //end currency  
    public double[] pathValue(int start, int end, double[][] graph)
    {
        //Initialize a temporary array by the size of the inputted graph
        double[] tempPath = new double[size];

        //Used to input the currency exchange rate to the tempPath 
        double input = 1.0;

        //Used for the while condition to allow the While statement to loop based 
        //on the number of elements in the path[][]
        int current = start;

        //Used for the While condition to make sure than the number of iterations 
        //do not exceed the size of tempPath, and place the input in the correct position in the tempPath array 
        int position = 0;
        while (current != end && position < tempPath.length-1)
        {
            tempPath[position] = input; //Add the exchange rate value to specificed position in the array 
            input = pathRate[current][end]; //Change the exchange rate value of input by the pathRate[current][end]
            current = path[current][end]; //Change the value of current, to iterate to the next exchange rate value of the sequence 
            position++; //Increment position 
        }

        //Add the last currency exchange value into the tempPath
        tempPath[position] = input;
        return tempPath; 
    }

    // Construct a path that shows the sequence from starting currency to ending currency 
    public String[] pathCreation(int start, int end, double[][] shortGraph, double[] pathValue, double[][] graph)
    {
        //Intialize the temporart String array by the size of the inputted graph
        String[] tempPath = new String[size];

        //Will return an empty path to suggest that there is no path 
        //connecting the start and end node
        if (shortGraph[start][end] == Double.POSITIVE_INFINITY) 
        {
            return tempPath;
        }

        //Make current equal to the starting currency 
        int current = start;

        //Used for the While condition to make sure than the number of iterations 
        //do not exceed the size of tempPath, and place the current in the correct position in the tempPath array 
        int position = 0;

        while (current != end && position < tempPath.length-1)
        {
            tempPath[position] = numberConverter(current);
            current = path[current][end];
            position++;
        }

        //If this IF statement condition is true, add end currency 
        if (cycleChecker == false)
        {
            tempPath[position] = numberConverter(end);
        }

        //If this IF statement condition is true, add the starting currecny
        //to indicate that there is arbitrage opportunity and the cylce needs to loop
        else if (cycleChecker == true)
        {
            tempPath[position] = numberConverter(current);
        }
        return tempPath;
    }

    //Converts the number into a string based on the value of the number
    //For example, if number is equal to 0, then number is equal NZD currency
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
            convert = "AUD";
                break;

            case 3:
            convert = "CAD";
                break;
                
            case 4:
            convert = "GBP";
                break;
                
            case 5:
            convert = "SGD";
                break;

            case 6:
            convert = "JPY";
                break;

            case 7:
            convert = "PHP";
                break;
            
            default:
                break;
        }
        return convert;
    }

    //Purpose of this function is to find the best exchange rate when there is no
    //arbitrage opportunity. 
    void findingExchangeValue(int start, int end, double[][] graph, double[] pathValue)
    {
        double exchangeValue = 1.0;
        int count = 0; 

        //Used numberConverter to convert the start and end values to String values 
        //to prepare for println 
        String startCurrency = numberConverter(start);
        String endCurrency = numberConverter(end);

        //check how many values inside pathValue
        for (int i = 0; i < pathValue.length; i++)
        {
            if (pathValue[i] != 0)
            {
                count++;
            }
        }

        //If the number of elements in array is less than or equal to 0
        //then we are dealing with a direct exchange 
        if (count <= 1)
        {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) 
                {
                    //If the values are equal to eachother 
                    if (graph[i][j] == graph[start][end])
                    {
                        //The best conversion rate between the start and end node is graph[i][j]
                        exchangeValue = graph[i][j];
                    }
                }
            }
        }

        //If the count is greater than 1 then we are dealing a intermediate currencies 
        else 
        {
            //Iterate through pathValue 
            for (int i = 0; i < pathValue.length; i++)
            {
                //Only include exchange rate values into the exchangeValue 
                if (pathValue[i] != 0)
                {
                    exchangeValue *= pathValue[i];
                }
            }
        }
        System.out.println("\nBest conversion rate from " + startCurrency + " to " +  endCurrency + ": " + exchangeValue);
    }

    //Purpose of the function is to find the arbitrage retun value when an arbitrage opportunity 
    //occurs in the sequence. 
    public double findingAbitrageValue(double[] pathValue, double[][] graph, int start, int end) {
        //Initialize the value to 1.0 as we are multiplying 
        double value = 1.0;

        //Iterate through the pathValue[i] and multipy each exchange rate value
        for (int i = 0; i < pathRate.length; i++)
        {
            if (pathValue[i] != 0)
            {
                value *= pathValue[i];
            }
        }

        //The way that the pathValue is constructed it does not include the starting exchange rate at the end of the sequnce,
        //thus we have to add the through this line of code. graph[end][start] finding the currency exchange rate from end node to start node. 
        value *= graph[end][start];

        //return the arbitrage value 
        return value;
    }

    // Prints the adjaency matrix
    void printGraph(double[][] graph) {
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

    //Prints the sequence of currencies based on the pathCurrency array
    void printPathCurrecny(String[] pathCurrency, int start, int end) 
    {
        System.out.println("\nHere is the sequence of exchanges:");

        //Iterate through the pathCurrency array 
        for (int i = 0; i < pathCurrency.length; i++) 
        {
            //Print the pathCurrency element if pathCurrency[i] is not null 
            if (pathCurrency[i] != null) 
            {
                System.out.print(pathCurrency[i]);

                //This statement is used to make it so that an arrow is not 
                //added after the last currency is printed. It checks
                //the pathCurrency next next value is not null. 
                if (i < pathCurrency.length-1 && pathCurrency[i+1] != null)
                {
                    System.out.print("->");
                }
            }
        }

        //Used when arbitrage opportunity has occurred, print the starting currency 
        //again to show a cycle
        if (cycleChecker == true)
        {
            System.out.println("->"+pathCurrency[0]);
        }
    }

    //Print the sequence currency exchange rate based on the pathValue array 
    void printPathCurrecnyRate(double[] pathValue, int start, int end) 
    {
        System.out.println("\n\nHere is the sequence of exchanges rates:");

        //Iterate through the pathValue array 
        for (int i = 0; i < pathValue.length; i++) 
        {
            //Print the pathCurrency element if pathValue[i] is not 0 
            if (pathValue[i] != 0) 
            {
                System.out.print(pathValue[i]);

                //This statement is used to make it so that an arrow is not 
                //added after the last currrency exchange rate is printed. It checks
                //the pathValue next next value is not 0. 
                if (i < pathValue.length-1 && pathValue[i+1] != 0) 
                {
                    System.out.print("->");
                }
            }
        }
    }
}
