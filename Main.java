import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        //Graph 1 has a path with arbitrage opportunity, 
        //Exchange rates collected from XE personal at 1:40am 
        double[][] graph1 = {
            {0, 0.61, 0.92, 0, 0.47}, //NZD
            {0, 0, 0, 1.35, 0.76},    //USD
            {0, 0.66, 0, 0, 0},       //AUD
            {1.19, 0, 1.10, 0, 0},    //CAD
            {0, 0, 0, 1.77, 0}        //GBP
        };

        //test 2, has a negative cycle or arbitrage opportunity
        //Exchange rates collected from XE personal at 4:22am
        double[][] graph2 = {
            {0, 0.61, 0, 0, 0, 0},
            {0, 0, 1.49, 0, 0, 0},
            {0, 0, 0, 1.5, 0.51, 0.87},
            {1.19, 0.73, 0, 0, 0, 0},
            {0, 0, 0, 1.77, 0, 0},
            {0, 0, 0, 0, 0.58, 0},
        };

        //test 3, has a negative cycle or arbitrage opportunity 
        //Exchange rates collected from XE personal at 2:00am
        double[][] graph3 = {
            {0, 0.61, 0, 0, 0.47, 0, 0, 0},      //nzd
            {0, 0, 1.49, 1.35, 0, 0, 0, 56.10},  //usd
            {0, 0, 0, 0, 0, 0.87, 95.0, 0},      //aud 
            {1.19, 0, 0, 0, 0.56, 0, 0, 0},      //cad
            {0, 0, 0, 1.77, 0, 0, 185.0, 0},     //gbp
            {0, 0, 0, 0, 0, 0, 0, 43.01},        //sgd
            {0, 0, 0, 0, 0.5, 0.91, 0, 0},       //jpy
            {0, 0, 0.2, 0, 0, 0, 0.2, 0},        //php
        };

        //Around here is where we have the printf and scanf statements 
        //to all user inputs for the graph. 
        Scanner obj = new Scanner(System.in);
        System.out.println("_______________________________________________________");
        System.out.println("Welcome to Currency Exchange");
        System.out.println("1. Demo exchange");
        System.out.println("\nInput Choice here:");
        
        boolean success = false;
        while (!success) {
            try {
                int number = obj.nextInt();
                switch (number) {
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
                        demo3.testDemo(graph3, 2, 1); //Test 13

                        success = true;
                        break;
                    
                    default:
                        System.out.println("!!!Please Input a Integer: 1!!!");
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
