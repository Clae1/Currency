import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        //Test 1, has no negative cycles 
        //Exchange rates collected from XE personal at 1:40am 
        double[][] graph1 = {
            {0, 0.61, 0.92, 0, 0.47}, //NZD
            {0, 0, 0, 1.35, 0.76},    //USD
            {0, 0.66, 0, 0, 0},       //AUD
            {1.19, 0, 1.10, 0, 0},    //CAD
            {0, 0, 0.63, 1.77, 0}        //GBP
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
            //    NZD  USD  AUD  CAD GBP SDP YEN
                { 0,   2,   0,   0,  0,  0,  2},
                { 0,   0,   0,  -1,  0,  0,  0},
                {-3,   1,   0,   0,  0,  0,  3},
                { 4,   0,   4,   0,  0,  2,  0},
                { 0,   0,   4,   0,  0,  0,  0},
                { 0,   0,   5,   0,  2,  0,  0},
                { 0,   0,   0,   0,  0,  0,  0},
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
                        demo1.testDemo(graph1, 0, 3);

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
