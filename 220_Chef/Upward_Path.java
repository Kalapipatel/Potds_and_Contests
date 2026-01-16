/*
code : UPPATH
https://www.codechef.com/problems/UPPATH

The Grid & GoalYou have a grid with 2 Rows and N Columns.Start: Top-left cell $(1,1)$.End: Bottom-right cell $(2,N)$.Movement: You can only move Right or Down.C
onstraint: You can never move to a cell with a smaller value than your current cell. The value must remain the same or increase ($Value_{next} \ge Value_{current}$).
The Superpower (Swaps)Before you start walking, you can manipulate the grid. For any column $i$, you can swap the value in the top row ($A_{1,i}$) with the value in the 
bottom row ($A_{2,i}$).You can do this for as many (or as few) columns as you want.Your goal is to configure the columns so that at least one valid path exists from start 
to end.The Shape of the PathSince the grid only has 2 rows, every path from $(1,1)$ to $(2,N)$ has a specific "Z" shape:Walk Right along Row 1 from column 1 to some 
column $k$.Move Down at column $k$ (from Row 1 to Row 2).Walk Right along Row 2 from column $k$ to column $N$.Therefore, the problem asks: Can you flip the numbers in the 
columns such that there exists a specific column $k$ where you can safely switch from top to bottom, while keeping the entire path non-decreasing?

*/

import java.util.*;
import java.io.*;

class Codechef {

    public static void main(String[] args) throws java.lang.Exception {
        // FastReader sc = new FastReader();
        Scanner sc = new Scanner(System.in);
        
        try {
            String tStr = sc.next();
            if(tStr == null) return;
            int T = Integer.parseInt(tStr);

            while (T-- > 0) {
                int N = sc.nextInt();
                int[] row1 = new int[N];
                int[] row2 = new int[N];

                for (int i = 0; i < N; i++) row1[i] = sc.nextInt();
                for (int i = 0; i < N; i++) row2[i] = sc.nextInt();

                // minTop[i] = minimum valid value at (1, i) reachable from start
                // maxBot[i] = maximum valid value at (2, i) that can reach end
                int[] minTop = new int[N];
                int[] maxBot = new int[N];
                
                // --- 1. Fill minTop (Forward Pass) ---
                
                // Base case: For first column, we prefer the smaller value for minTop
                // But we must check if that small value is actually a valid start? 
                // Any value is valid start. To be greedy, we take the minimum of the col.
                // Wait! Actually, minTop[i] stores the value WE PICKED. 
                // Since we can swap, the available values are row1[0] and row2[0].
                // The minimum possible start is Math.min(row1[0], row2[0]).
                minTop[0] = Math.min(row1[0], row2[0]);

                for (int i = 1; i < N; i++) {
                    int prev = minTop[i - 1];
                    
                    // If previous was impossible, this is impossible
                    if (prev == Integer.MAX_VALUE) {
                        minTop[i] = Integer.MAX_VALUE;
                        continue;
                    }

                    int u = row1[i];
                    int v = row2[i];
                    
                    int best = Integer.MAX_VALUE;
                    
                    // Try to pick u
                    if (u >= prev) best = Math.min(best, u);
                    // Try to pick v
                    if (v >= prev) best = Math.min(best, v);
                    
                    minTop[i] = best;
                }

                // --- 2. Fill maxBot (Backward Pass) ---
                
                // Base case: Last column. We can finish with max possible value to maximize reach.
                maxBot[N - 1] = Math.max(row1[N - 1], row2[N - 1]);

                for (int i = N - 2; i >= 0; i--) {
                    int next = maxBot[i + 1];
                    
                    // If next step is impossible (cannot reach end from i+1), then i is dead end
                    if (next == -1) {
                        maxBot[i] = -1;
                        continue;
                    }

                    int u = row1[i];
                    int v = row2[i];
                    
                    int best = -1;
                    
                    // Try to pick u: valid if u <= next
                    if (u <= next) best = Math.max(best, u);
                    // Try to pick v: valid if v <= next
                    if (v <= next) best = Math.max(best, v);
                    
                    maxBot[i] = best;
                }

                // --- 3. Check Crossover Points ---
                boolean possible = false;

                for (int i = 0; i < N; i++) {
                    // We are at column i. We want to switch from Row 1 to Row 2 here.
                    // We can swap column i. Values are u, v.
                    
                    int u = row1[i];
                    int v = row2[i];
                    
                    // Previous limit (from left)
                    int leftLimit = (i == 0) ? Integer.MIN_VALUE : minTop[i - 1];
                    
                    // Next limit (from right)
                    int rightLimit = (i == N - 1) ? Integer.MAX_VALUE : maxBot[i + 1];
                    
                    // If left was blocked or right is blocked, we can't switch here
                    if (leftLimit == Integer.MAX_VALUE || rightLimit == -1) continue;

                    // Option A: Top = u, Bot = v
                    // Valid if: leftLimit <= u AND u <= v AND v <= rightLimit
                    if (u >= leftLimit && v <= rightLimit && u <= v) {
                        possible = true;
                        break;
                    }

                    // Option B: Top = v, Bot = u
                    // Valid if: leftLimit <= v AND v <= u AND u <= rightLimit
                    if (v >= leftLimit && u <= rightLimit && v <= u) {
                        possible = true;
                        break;
                    }
                }

                if (possible) System.out.println("Yes");
                else System.out.println("No");
            }
        } catch (Exception e) {
            return;
        }
    }
}

/* explanation ------------------
Imagine you are building a bridge that has two parts:

The Ramp Up (Row 1): You are walking along the top row. To make it easy to jump down to the bottom row later, you want your current height (value) to be as SMALL as possible.

Why? If you are at height 10, you can jump to any height 10 or greater. If you are at height 100, you are restricted to 100 or greater. Being "low" gives you more freedom.

The Landing Pad (Row 2): You are at the end, looking backwards. To make it easy to land here from the top row, you want your height to be as BIG as possible.

Why? If the landing pad is 100, you can jump down from 10, 50, or 90. If the landing pad is 5, you can only jump down from 1 to 5. Being "high" makes you an easier target.

The Approach:

Calculate the best possible (smallest) value to reach every cell in the top row. Let's call this minTop.

Calculate the best possible (largest) value to finish at every cell in the bottom row. Let's call this maxBot.

Check every column to see if we can build a "vertical bridge" connecting minTop (left) to maxBot (right).

example: 
Row 1: 10 50 40 90 
Row 2: 20 15 60 85

Solution:
minTop: [10, 15, 40, 85]
maxBot: [20, 50, 60, 90]

$\le$ = <=
Now, we try to construct the "Z" path at every column i.A valid bridge at column i looks like this:Left Limit $\le$ Top Cell $\le$ Bottom Cell $\le$ Right LimitLeft 
Limit: minTop[i-1] (Where we came from)Right Limit: maxBot[i+1] (Where we are going)Let's check Column 1 (Index 1):Left Limit: minTop[0] = 10Right 
Limit: maxBot[2] = 60Column 1 Values: {50, 15}Can we bridge 10 and 60 using 15 and 50?We need: $10 \le \text{Small} \le \text{Big} \le 60$.Let's put 15 on Top and 50 on
Bottom.Check: $10 \to 15 \to 50 \to 60$.Is $10 \le 15$? Yes.Is $15 \le 50$? Yes (The drop down).Is $50 \le 60$? Yes.Success!We found a valid path:$(1,0)=10 \to (1,1)=15 
\to (2,1)=50 \to (2,2)=60 \to (2,3)=90$.
*/
