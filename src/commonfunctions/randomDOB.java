package commonfunctions;

//import java.util.Date;
import java.util.GregorianCalendar;

//import org.apache.commons.lang3.RandomUtils;

public class randomDOB {

    public static void main(String[] args) {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1960, 2010);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        System.out.println(gc.get(gc.MONTH) + "/" + (gc.get(gc.DAY_OF_MONTH) + 1) + "/" + gc.get(gc.YEAR));
        //System.out.println(gc.get(gc.YEAR) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.DAY_OF_MONTH));
        
    }
    
   
    
 
//1/1/1970
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}