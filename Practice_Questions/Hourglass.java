/*
Explanation of the ProblemThe problem asks how much sand is left in the top of an hourglass at a specific time $m$.There are two main scenarios based on how fast Vadim 
flips the hourglass ($k$) compared to the total size of the hourglass ($s$):Scenario 1: He flips it SLOWLY ($k \ge s$)The hourglass has time to empty completely before he 
flips it.Behavior: It runs for $s$ minutes, stays empty, and then gets flipped at minute $k$.Logic: We just check where time $m$ falls in this $k$-minute cycle. If it's 
within the first $s$ minutes, we do subtraction. If it's after $s$, it's empty (0).Scenario 2: He flips it FAST ($k < s$)The hourglass never empties completely in the 
first turn. It creates a repeating 2-step loop.Step A (Even intervals): The hourglass starts full ($s$). After $k$ minutes, he flips it.Step B (Odd intervals): The 
hourglass was just flipped. The sand that fell in Step A (which is exactly $k$ amount) is now at the top. It runs for $k$ minutes and empties exactly as the next flip 
happens.Logic:If we are in an even interval ($0, 2, 4...$), we calculate sand remaining from $s$.If we are in an odd interval ($1, 3, 5...$), we calculate sand remaining 
from $k$.
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int t = sc.nextInt();
            
            while (t-- > 0) {
                long s = sc.nextLong(); // Hourglass capacity
                long k = sc.nextLong(); // Flip frequency
                long m = sc.nextLong(); // Time to check
                
                // Case 1: Flip time is larger than capacity (k >= s)
                // The sand empties fully and waits for the next flip.
                if (k >= s) {
                    long timeInCycle = m % k;
                    
                    if (timeInCycle >= s) {
                        System.out.println(0);
                    } else {
                        System.out.println(s - timeInCycle);
                    }
                } 
                // Case 2: Flip time is smaller than capacity (k < s)
                // It alternates between starting with 's' sand and 'k' sand.
                else {
                    long intervalIndex = m / k;
                    long timeInInterval = m % k;
                    
                    // Even intervals start with full capacity (s)
                    if (intervalIndex % 2 == 0) {
                        System.out.println(s - timeInInterval);
                    } 
                    // Odd intervals start with the sand that fell in the previous turn (k)
                    else {
                        System.out.println(k - timeInInterval);
                    }
                }
            }
        }
    }
}
