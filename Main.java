import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        //Here is where we have the input test 
        //test 1, no negative cycle
        double[][] graph1 = {
            {0, 3, 8, 0, -4},
            {0, 0, 0, 1, 7},
            {0, 4, 0, 0, 0},
            {2, 0, -5, 0, 0},
            {0, 0, 0, 6, 0}
        };

        //test 2, there is a negative cycle 
        double[][] graph2 = {
            {0, 1, 0, 0},
            {0, 0, -1, 1},
            {0, 0, 0, 3},
            {-3, 0, 0, 0}
        };

        double[][] graph3 = {
        //   S  A   B  C  D  E
            {0, 10, 0, 3, 0, 0}, 
            {0, 0,  2, 0,-3, 0}, 
            {0,-1,  0, 0, 0, 0},
            {0, 0,  0, 0, 3, 0},
            {0, 0,  4, 0, 0, 1},
            {0, 0,  4, 0, 0, 0},
        };

        double[][] graph4 = {
            //    0   1   2  3  4  5  6
                { 0,  2,  0,  0, 0, 0, 2},
                { 0,  0,  0, -1, 0, 0, 0},
                {-3,  1,  0,  0, 0, 0, 3},
                { 4,  0,  4,  0, 0, 2, 0},
                { 0,  0,  4,  0, 0, 0, 0},
                { 0,  0,  5,  0, 2, 0, 0},
                { 0,  0,  0,  0, 0, 0, 0},
            };


        //Around here is where we have the printf and scanf statements 
        //to all user inputs for the graph. 
        Scanner obj = new Scanner(System.in);
        System.out.println("_______________________________________________________");
        System.out.println("Welcome to Currency Exchange");
        System.out.println("1. Demo exchange \n2. Input exchange graph");
        System.out.println("\nInput Choice here:");
        
        boolean success = false;
        while (!success) {
            try {
                int number = obj.nextInt();
                switch (number) {
                    case 1:
                        System.out.println("\nDemo Exchange");

                        Demo demo1 = new Demo(); //Test 1 graph 
                        demo1.testDemo(graph1, 2, 4);

                        Demo demo2 = new Demo(); //Test 2 graph 
                        demo2.testDemo(graph2, 0, 2);

                        Demo demo3 = new Demo(); //Test 3 graph 
                        demo3.testDemo(graph3, 2, 4);

                        Demo demo4 = new Demo(); //Test 4 graph 
                        demo4.testDemo(graph4, 2, 4);

                        success = true;
                        break;
                    
                    case 2:
                        System.out.println("\nInput exchange graph");
                        success = true;
                        break;

                    default:
                        System.out.println("Please Input a Integer 1 or 2");
                        break;
                }
            } 
            catch (Exception e) {
                System.out.println("Please Input a Integer 1 or 2!!!!");
                obj.next();
            }
        }
        obj.close();
    }
}
