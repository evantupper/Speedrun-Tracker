/*
Userstory:
I want an application that would allow me to store and keep track of my speedrun times. 
- I want it to allow me to insert new times and then see how they compare relatively to all of mine (like with standard deviation, mean, median, percentile).
- I would allow like an ascii representation that includes these statistics, the last 5 scores, and a place for me to insert new scores.
- Alongside these lifetime statistics, I want to see how my time compares to those scored in the last month, week, and day.
- I want to be able to save all scores into a file and load the program at another time and still retain all scores.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        List<Double> numbers = new ArrayList<>(); 
        

        double time;
        do {
            if (numbers.size() > 0)
                System.out.println("Latest Time\t>>> " + numbers.get(numbers.size()-1));
            System.out.println("Median\t\t>>> " + calculateMedian(numbers));
            System.out.println("Mean\t\t>>> " + calculateMean(numbers));
            System.out.println("Std Dev.\t>>> " + calculateStdDev(numbers));
            System.out.println("Percentile\t>>> " + calculatePercentile(numbers, 5));

            int count = Math.min(numbers.size(), 5);
            System.out.println("Previous " + count + " times:");
            for (int i = 0; i < count; i++) {
                System.out.println("" + numbers.get(i));
            }

            System.out.print("\nEnter in your time\t>>> ");
            time = console.nextDouble();
            if (time == 0) break;
            
            numbers.add(time);
            System.out.print("\f"); // Clears screen
        } while (time != 0);
        console.close();

        

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
    /**
     * This calculates the median for the passed in List of Doubles.
     * 
     * @param nums List of Doubles that are used to find the median.
     * @return Returns the median of the list.
     */
    private static double calculateMedian(List<Double> nums) {
        if (nums.isEmpty())
            return 0.0;
        if(nums.size() == 1)
            return nums.getFirst();
        nums = cloneList(nums);

        Collections.sort(nums); 

        double middle1 = nums.get(nums.size() / 2);
        double middle2 = nums.get((nums.size() / 2) - 1);

        if (nums.size() % 2 == 0) { // Even size list
            return (middle1 + middle2) / 2; 
        }
        return middle1; // Odd size list
    }

    private static List<Double> cloneList(List<Double> nums) {
        List<Double> clone = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++)
            clone.add(nums.get(i));

        return clone;
    }

    private static double calculateStdDev(List<Double> nums) {
        if (nums.isEmpty() || nums.size() <= 1)
            return 0.0;
        if (nums.size() == 1)
            return nums.getFirst();

        double mean = calculateMean(nums);
        double stdDev = 0;
        double sum = 0;

        for (Double number : nums) {
            sum += Math.pow(number - mean, 2.0);
        }
        stdDev = Math.sqrt(sum / nums.size());
        
        return stdDev;
    }

    private static double calculatePercentile(List<Double> nums, double time) {
        if (nums.size() == 0)
            return 0;
        nums = cloneList(nums);
        double count = 0;
        double percentile = 0;
        Collections.sort(nums);

        for (Double number : nums) {
            if (number < time) // Number of times below time
                count++; 
        }
        percentile = (count / (double) nums.size()) * 100;

        return percentile;
    }
} 
