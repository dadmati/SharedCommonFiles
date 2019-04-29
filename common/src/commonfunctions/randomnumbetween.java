package commonfunctions;

import java.util.Random;
/*
 * Generate random integer between two given number using methods 
 * introduced in JDK 1.8.
 */

public class randomnumbetween {
  public static void main(String[] args) {
    Random randomObj = new Random();
    System.out.println(randomObj.ints(1, 12).findFirst().getAsInt()); ;
    
  }
}