import java.util.Scanner;

public class User 
{
    //Create a new instance of Function class to allow the User class to use the functions 
    Functions Graph = new Functions();

    public static int size; //For the size of the array 
    public static double[][] userGraph; //Users inputted graph 
    public static double[] dataArray; //Used for inputting the users data to each from of the user graph 

    public static int start; //Starting node or currency 
    public static int end; //Ending node or currency 

    void testUser()
    {
        //Intialize checker as false so that condition for While is fulfilled 
        boolean checker = false; 
        Scanner obj = new Scanner(System.in);

        System.out.println("\n!!!Welcome to user graph creator!!!");
        System.out.println("___________________________________________");

        //While statement is used to repeat the code if an error was found in the user next input 
        while (!checker)
        {
            try 
            {
                System.out.println("Enter the size of your graph from 2 to 7:");
                System.err.print("Input here: ");
                int number = obj.nextInt(); //Scanner obj will check if user input is of Integer type 

                //Clears the previous input to prepare for the next input 
                obj.nextLine(); 

                //Input number into the sizeChecker function and it will return either a true or false 
                //depending on the number that user has inputted. This determine if the user has to
                //input the size of the array again
                checker = sizeChecker(number);
            }

            //This exception will specifically catch InputMisMatch excpetions 
            catch (Exception e) 
            {
                //Return an error message to the user that only integer values are allowed 
                System.out.println("Do not input letters only Integers");
                System.out.println("!!!Please Input an Integer number between 2 to 7!!!\n");

                //Clears the previous input to prepare for the next input 
                obj.nextLine();
            }
        }

        //Intializing dataArray and UserGraph as the size of the array has been set in the 
        //previous user input. 
        dataArray = new double[size];
        userGraph = new double[size][size];

        //count used to keep track of the current row that user is on.
        //Count is 0 to indicate that we are starting from row 0
        int count = 0;

        //This While statement will continue to loop as long as count is not equal to size,
        //this is to ensure that the user is able to input data for each row of thier graph.
        //based on the size of their graph.  
        while (count != size)
        {
            //change checker to false to prepare for the next inputs for the user
            //as previouslt checker was true. 
            checker = false; 
            try 
            {
                //While statement will check if checker is true. 
                //This will contine to loop if errors have occured such as the format was followed by the user
                //Loop will end if checker is equal to true. 
                while (!checker)
                {
                    System.out.println("\nPlease Input format data as followed in example: data, data, data");
                    System.out.println("Enter your row " +count+ " data:");
                    System.out.print("Here: ");
                    
                    //Read the entire line for user input
                    String data = obj.nextLine();

                    //Convert the string input into the dataArray
                    //Will return true or false depending if the user input followed the format correctly 
                    checker = convertDatatoArray(data, count);
                }

                //Once while loop condition is fulfilled increase the count by 1 
                count++;
            } 

            //This exception will specifically catch InputMisMatch excpetions 
            catch (Exception e) 
            {
                System.out.println("\n!!!Format was not followed!!!");
                obj.nextLine(); //Clear previous input
            }
        }

        System.out.println("\n________________________________________________");
        checker = false; //reset checker back to false

        while (!checker)
        {
            try 
            {
                //Print a message to the user of what currencies they want to convert
                System.out.println("\nPlease Input numbers as followed in this example: start, end");
                System.out.println("Choose a starting and sink node based on these options:");
                System.out.println("NZD(0), USD(1), AUD(2), CAD(3), GBP(4), SDP(5), YEN(6), PHP(7)");
                System.out.println("Here: ");

                String input = obj.nextLine(); //Clear previous input by user 

                //Convert the input data into Integer start and end variables
                getNodes(input);

                double[][] copyGraph = Graph.setupGraph(userGraph); //copyGraph will recieve an updated userGraph 
                double[][] shortGraph = Graph.floydAlgorithm(copyGraph); //shortGraph will recieve the updated graph so that copyGraph is not affected by the changes of floyd-warshall
                double[] pathValue = Graph.pathValue(start, end, shortGraph); //pathValue array will recieve the the currency exchange rate of the sequence based on the starting node and ending node
                String[] pathCurrency = Graph.pathCreation(start, end, shortGraph, pathValue, userGraph); //pathCurrency array will recieve the the currencies in the sequence based on the starting node and ending node

                //Check if there is arbitrage opportunity 
                //If there is a arbitrage opportunity run the code inside the IF statement
                if (Graph.checkNegativeCycle(pathValue, userGraph, start, end) == true)
                {
                    System.out.println("\n!!!Arbitrage opportunity found!!!\n");
                    Graph.printGraph(userGraph); //Prints the original userGraph 
                    Graph.printPathCurrecny(pathCurrency, start, end); //Prints the Currencies in the sequence 
                    Graph.printPathCurrecnyRate(pathValue, start, end); //Prints the currency exchange rate in the sequence 

                    //Return the arbitrage value of the sequence
                    System.out.println("\nArbitrage return: " + Graph.findingAbitrageValue(pathValue, userGraph, start, end));
                }

                //If the returned value is false, there is no arbitrage opportunity
                else 
                {
                    System.out.println("\nNo Arbitrage opportunity found\n");
                    Graph.printGraph(userGraph); //Prints the original userGraph 

                    //Return the best exchange rate 
                    Graph.findingExchangeValue(start, end, shortGraph, pathValue);

                    Graph.printPathCurrecny(pathCurrency, start, end); //Prints the Currencies in the sequence 
                    Graph.printPathCurrecnyRate(pathValue, start, end); //Prints the currency exchange rate in the sequence 
                }

                //These prints a message to the user to indicate whether they want to convert different currencies 
                //or else they can choose to exit the program by entering "Exit"
                System.out.println("\n________________________________________________");
                System.out.println("Do you want to enter different starting and ending currency?");
                System.out.println("If Yes: Press Enter.");
                System.out.println("If No: Input Exit.");
                System.out.print("Here: ");

                //Reads the user next input 
                String exitOption = obj.nextLine();

                //Checks if the user input is equal to exit 
                if (exitOption.equals("exit")  || exitOption.equals("Exit"))
                {
                    //This will exit the program
                    checker = true;
                }
            }

            //Catch InputMisMatch 
            catch (Exception e) 
            {
                //Provide an error message for the user to know an error has occured 
                System.out.println("\n!!!Format was not followed!!!");
                obj.nextLine(); //clear the previous input 
            }
        }
        obj.close();
    }

    //This functions purpose is to make it so that the user inputs are converted into
    //integer values so that we can input it into start and end variables. So that we 
    //know what the starting and ending currencies are. 
    void getNodes (String input)
    {
        //Will split the string into substrings based on ", "
        //Each splitData will hold each substring into its array 
        String[] splitData = input.split(", ");
        
        //The for loop will iterate through the splitData array and if
        //i is equal to a specific position in the splitData array 
        //add the element value to start or end 
        for (int i = 0; i < splitData.length; i++)
        {
            if (i == 0)
            {
                start = Integer.parseInt(splitData[0]);
            }
            else if (i == 1)
            {
                end = Integer.parseInt(splitData[1]);
            }
        }
    }

    //The purpose of this function is to convert the users input into the dataArray[]
    //and input the values inside dataArray[] into the specific row of UserGraph adjaency matrix
    public boolean convertDatatoArray(String data, int count)
    {
        String[] splitData =  data.split(", ");

        //This IF statement is to check if the user input matches the 
        //length of the 2D array. 
        if (splitData.length-1 > size)
        {
            //IF condition is true, return false to indicate that 
            //the user input is incorrect and must repeat the row input. 
            System.out.println("!!!Input size was incorrect!!!");
            return false;
        }
       
        //Convert the string type data into double type and add the values into
        //dataArray[] array
        for (int i = 0; i < size; i++)
        {
            dataArray[i] = Double.parseDouble(splitData[i]);
        }

        //Add the dataArray into the 2D-array userGraph for a specific row 
        //based on the current count 
        for (int j = 0; j < size; j++)
        {
            userGraph[count][j] = dataArray[j];
        }

        //Return true to indicate that no errors have occurred and 
        //the while loop will stop. 
        return true; 
    }

    //Purpose of this function is to check if the graph size input is correct 
    public boolean sizeChecker(int number)
    {
        //If the number is greater or equal to 2 and number is less than or equal to 8
        //then the condition is true. 
        if (number >= 2 && number <= 8) 
        {
            System.out.println("Size of 2D array is "+ number);
            size = number; 
            return true;
        }

        //Else statement is used to print the error message to indicate to the user 
        //that the their graph size input is less than 2, greater than 8 or is not 
        //of integer type. 
        else 
        {
            System.out.println("\n!!!Please Input an Integer number between 2 to 8!!!\n");
        }
        return false; 
    }
}
