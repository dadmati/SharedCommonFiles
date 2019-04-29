package commonfunctions;

import java.util.Arrays;

public class RandNumGenerator {

    public static int RandInt(){
        double n = Math.random()*10;
        return (int) n;
        }

    public static void randTest(int n){
        int [] counts = new int [10];

        for(int i=0;i<n;i++){
            counts[i] = RandInt();
            System.out.println(counts[i]);
            } 
        }

    public static void main(String[] args) {
        int sampleSize = 1000;      
        System.out.println ("Sample Size: " + sampleSize);
        String[] intArray = new String[] {"Value","Count","Expected","Abs Diff","Percent Diff"};
        System.out.println(Arrays.toString(intArray));
        randTest(10);
        }
    }


