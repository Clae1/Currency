import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        //Graph 1 has a path with arbitrage opportunity, 
        //Exchange rates collected from XE personal at 1:40am 
        double[][] graph1 = {
            {0, 0.61, 0.91, 0.83, 0.46}, //NZD
            {1.62, 0, 1.49, 1.35, 0.76}, //USD
            {1.08, 0.66, 0, 0.91, 0.51}, //AUD
            {1.19, 0.73, 1.10, 0, 0.56}, //CAD
            {2.13, 1.31, 1.95, 1.77, 0} //GBP
        };

        //test 2, has a arbitrage opportunity
        //Exchange rates collected from XE personal at 4:22am
        double[][] graph2 = {
            {0, 0.616, 0.919, 0.835, 0.471, 0.802}, //nzd
            {1.631, 0, 1.498, 1.357, 0.766, 1.305}, //usd
            {1.088, 0.669, 0, 0.901, 0.512, 0.873}, //aud
            {1.19, 0.73, 1.103, 0, 0.564, 0.962},   //cad 
            {2.12, 1.306, 1.952, 1.77, 0, 1.706},   //gbp
            {1.248, 0.766, 1.147, 1.041, 0.58, 0},  //sgd
        };

        //test 3, has a arbitrage opportunity 
        //Exchange rates collected from XE personal at 2:00am
        double[][] graph3 = {
            {0, 0.617, 0.918, 0.839, 0.470, 0.803, 87.704, 34.656},      //nzd
            {1.626, 0, 1.49, 1.35, 0.761, 1.299, 141.11, 56.10},         //usd
            {1.088, 0.671, 0, 0.511, 0.873, 0.87, 95, 37.766},           //aud 
            {1.19, 0.736, 1.098, 0, 0.56, 0.957, 103.638, 41.277},       //cad
            {2.127, 1.314, 1.95, 1.77, 0, 1.955, 185, 73.6032},          //gbp
            {1.245, 0.769, 1.147, 1.045, 0.585, 0, 108.32, 43.01},       //sgd
            {0.011, 0.007, 0.0106, 0.0096, 0.0053, 0.0092, 0, 0.397},    //jpy
            {0.0290, 0.0178, 0.026, 0.0242, 0.0136, 0.0232, 2.51351, 0}, //php
        };

        //Around here is where we have the printf and scanf statements 
        //to all user inputs for the graph. 
        Scanner obj = new Scanner(System.in);
        System.out.println("_______________________________________________________");
        System.out.println("Welcome to Currency Exchange");
        System.out.println("1. Demo exchange");
        System.out.println("2. Manual input currency exchange");
        System.out.println("\nInput Choice here 1 or 2 :");
        
        boolean success = false;

        //While statment condition will remain false to repeat the code if an error were to occur.
        //While statement will end if the correct inputs are done, returning success as true ending the loop. 
        while (!success) {
            try {
                int number = obj.nextInt(); //Check that the next input of the user is of interger type 
                switch (number) {
                    //This case will be true if number is equal to one 
                    case 1:
                        System.out.println("\nDemo Exchange");

                        //Testing graph 1 
                        Demo demo1 = new Demo();
                        demo1.testDemo(graph1, 0, 1); //Test 1
                        demo1.testDemo(graph1, 1, 0); //Test 2
                        demo1.testDemo(graph1, 2, 3); //Test 3
                        demo1.testDemo(graph1, 3, 4); //Test 4
                        demo1.testDemo(graph1, 4, 2); //Test 5


                        //Testing graph 2
                        Demo demo2 = new Demo();
                        demo2.testDemo(graph2, 0, 1); //Test 6
                        demo2.testDemo(graph2, 5, 3); //Test 7
                        demo2.testDemo(graph2, 0, 3); //Test 8
                        demo2.testDemo(graph2, 1, 3); //Test 9

                        //Testing graph 3
                        Demo demo3 = new Demo(); 
                        demo3.testDemo(graph3, 0, 3); //Test 10
                        demo3.testDemo(graph3, 7, 4); //Test 11
                        demo3.testDemo(graph3, 5, 0); //Test 12
                        demo3.testDemo(graph3, 2, 7); //Test 13
                        demo3.testDemo(graph3, 2, 1); //Test 14
                        demo3.testDemo(graph3, 7, 1); //Test 15

                        //Make success true to end the while statement 
                        success = true;
                        break;

                    //This case will be true if number is equal to 2
                    case 2:
                        User user = new User();
                        
                        //Run the user class function 
                        user.testUser();
                        System.out.println("Program has ended");

                         //Make success true to end the while statement 
                        success = true;
                        break;
                
                    default:
                        System.out.println("!!!Please Input a Integer: 1!!!");
                        break;
                }
            } 
            catch (Exception e) 
            {
                //If the user input is not equal to 1 or 2, print this error 
                //message to the user 
                System.out.println("Please Input a Integer 1 or 2!!!!");
                obj.next();
            }
        }
        obj.close();
    }
}
