import java.util.Scanner;

public class User 
{
    public static int size;
    public static double[][] userGraph;
    public static double[] dataArray;

    void testUser()
    {
        boolean checker = false; 
        Scanner obj = new Scanner(System.in);

        System.out.println("\n!!!Welcome to user graph creator!!!");
        System.out.println("___________________________________________");
        while (!checker)
        {
            try 
            {
                System.out.println("Enter the size of your graph from 2 to 7:");
                System.err.print("Input here: ");
                int number = obj.nextInt(); 

                obj.nextLine(); 

                checker = sizeChecker(number);
            } 
            catch (Exception e) 
            {
                System.out.println("Do not input letters only Integers");
                System.out.println("!!!Please Input an Integer number between 2 to 7!!!\n");
                obj.nextLine();
            }
        }

    
        dataArray = new double[size];
        userGraph = new double[size][size];
        int count = 0;
        
        while (count != size)
        {
            //change checker to false to prepare for the next inputs for the user
            checker = false; 
            try 
            {
                while (!checker)
                {
                    System.out.println("\nPlease Input format data as followed in example: data, data, data");
                    System.out.println("Enter your row " +count+ " data:");
                    System.out.print("Here: ");
                    
                    //Read the entire line for user input
                    String data = obj.nextLine();

                    //Convert the string input into the dataArray
                    checker = convertDatatoArray(data, count);
                }
                count++;
            } 
            catch (Exception e) 
            {
                System.out.println("\n!!!Format was not followed!!!");
                obj.nextLine();
            }
        }
        obj.close();
    }

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
       
        //Convert the string type data into double type
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
        return true; 
    }

    public boolean sizeChecker(int number)
    {
        if (number >= 2 && number <= 7) 
        {
            System.out.println("Size of 2D array is "+ number);
            size = number; 
            return true;
        }

        else 
        {
            System.out.println("\n!!!Please Input an Integer number between 1 to 7!!!\n");
        }
        return false; 
    }
}
