/*
Userstory:
I want an application that would allow me to store and keep track of my speedrun times. 
- I want it to allow me to insert new times and then see how they compare relatively to all of mine (like with standard deviation, mean, median, percentile).
- I would allow like an ascii representation that includes these statistics, the last 5 scores, and a place for me to insert new scores.
- Alongside these lifetime statistics, I want to see how my time compares to those scored in the last month, week, and day.
- I want to be able to save all scores into a file and load the program at another time and still retain all scores.
*/
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        List<Double> numbers = new ArrayList<Double>(); 
        

        double time;
        do {
            System.out.print("Enter in your time >>> ");
            time = console.nextDouble();
            if (time == 0) break;

            numbers.add(time);
        
        } while (time != 0);
	console.close();

        System.out.print("Mean:" + calculateMean(numbers));

    }
    

    /**
     * This calculates the mean for the passed in List of Doubles.
     * 
     * @param nums List of Doubles that are to be averaged.
     * @return Returns the average of the list.
     */
    private static double calculateMean(List<Double> nums) {
        // Edge case: Avoids an empty list doing 'average / 0' and crashing.
        if (nums.isEmpty())
            return 0.0; 

        double average = 0;
        for (Double number : nums) {
            average += number;
        }
        return average / nums.size();
    }
} 
