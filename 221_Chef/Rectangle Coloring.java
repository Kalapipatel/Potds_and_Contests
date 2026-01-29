/*
Problem Description:You have a rectangular fence with length $L$ and width $W$. The total perimeter is $2 \times (L + W)$. You have three paint 
colors: Red, Green, and Blue. The total amount of paint you have (sum of R, G, and B) is exactly equal to the length of the fence.You must paint the perimeter 
such that:Every side is painted completely.A single side can have multiple colors on it.The "Cost" of painting a side is equal to the number of distinct colors
used on that specific side.You need to arrange the colors around the perimeter to minimize the Total Cost (sum of costs of all 4 sides).Input:Number of test 
cases ($T$).For each test case, 5 numbers: $L$ (Length), $W$ (Width), $R$ (Red paint), $G$ (Green paint), $B$ (Blue paint).Output:The minimum total cost.
Example:PlaintextInput:
4
6 3 9 5 4
4 4 4 8 4
2 3 1 1 8
3 3 1 10 1

Output:
5
4
5
6
*/


import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // Using Scanner for simplicity, but for T=10^5, FastReader is often safer. 
        // Given the logic is O(1), Scanner should likely pass.
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int T = sc.nextInt();
            while(T-- > 0){
                long L = sc.nextLong();
                long W = sc.nextLong();
                long R = sc.nextLong();
                long G = sc.nextLong();
                long B = sc.nextLong();
                
                solve(L, W, R, G, B);
            }
        }
    }
    
    public static void solve(long L, long W, long R, long G, long B) {
        long perimeter = 2 * (L + W);
        
        // Positions of the 4 corners on the linearized perimeter
        // 0 -> L -> L+W -> 2L+W
        long[] corners = {0, L, L + W, 2 * L + W};
        
        // Lengths of the 4 sides
        long[] sideLens = {L, W, L, W};
        
        // Paint permutations to try (RGB and RBG)
        // We use a 2D array: { {R, G, B}, {R, B, G} }
        long[][] paintOrders = {
            {R, G, B},
            {R, B, G}
        };

        long minTotalCost = Long.MAX_VALUE;

        // 1. Iterate through both color orders (RGB vs RBG)
        for (long[] paints : paintOrders) {
            
            // Relative positions of boundaries between paints
            // Boundary 0 is at 0
            // Boundary 1 is at paints[0]
            // Boundary 2 is at paints[0] + paints[1]
            long[] boundaries = {0, paints[0], paints[0] + paints[1]};

            // 2. Try aligning each of the 3 boundaries with each of the 4 corners
            for (int bIdx = 0; bIdx < 3; bIdx++) {
                for (int cIdx = 0; cIdx < 4; cIdx++) {
                    
                    // Calculate the shift needed to align boundary[bIdx] with corner[cIdx]
                    // We want: (boundaries[bIdx] + shift) % perimeter == corners[cIdx]
                    long shift = corners[cIdx] - boundaries[bIdx];
                    
                    // Normalize shift to be positive [0, perimeter)
                    shift = (shift % perimeter + perimeter) % perimeter;

                    // Define the absolute start/end of each paint block after shifting
                    long[][] paintIntervals = new long[3][2];
                    
                    // Paint 0
                    paintIntervals[0][0] = shift;
                    paintIntervals[0][1] = shift + paints[0];
                    
                    // Paint 1
                    paintIntervals[1][0] = shift + paints[0];
                    paintIntervals[1][1] = shift + paints[0] + paints[1];
                    
                    // Paint 2
                    paintIntervals[2][0] = shift + paints[0] + paints[1];
                    paintIntervals[2][1] = shift + paints[0] + paints[1] + paints[2];

                    long currentCost = 0;

                    // 3. Calculate cost for each side based on overlaps
                    for (int s = 0; s < 4; s++) {
                        long sStart = corners[s];
                        long sEnd = sStart + sideLens[s];
                        
                        int distinctColors = 0;
                        for (int p = 0; p < 3; p++) {
                            if (hasOverlap(sStart, sEnd, paintIntervals[p][0], paintIntervals[p][1], perimeter)) {
                                distinctColors++;
                            }
                        }
                        currentCost += distinctColors;
                    }

                    if (currentCost < minTotalCost) {
                        minTotalCost = currentCost;
                    }
                }
            }
        }
        
        System.out.println(minTotalCost);
    }
    
    // Helper to check if a Paint Interval overlaps with a Side Interval
    // sStart, sEnd: Side coordinates
    // pStart, pEnd: Paint coordinates (pEnd can be > perimeter)
    public static boolean hasOverlap(long sStart, long sEnd, long pStart, long pEnd, long perimeter) {
        // Normalize paint interval relative to perimeter for checking
        long normPStart = pStart % perimeter;
        long normPEnd = pEnd % perimeter;
        
        // Case A: Paint interval wraps around (e.g., starts at 9, ends at 2 in size 10)
        // This happens if the original pStart < pEnd but after modulo it flips, 
        // OR if the interval length is exactly perimeter (full cover).
        // A simpler check: Did we cross the perimeter boundary in the un-modulo'd values?
        // Actually, let's stick to the logic:
        
        if (normPStart >= normPEnd && (pEnd - pStart) < perimeter) {
            // Wraps around: [normPStart, perimeter) AND [0, normPEnd)
            
            // Check overlap with [normPStart, perimeter)
            if (Math.max(sStart, normPStart) < Math.min(sEnd, perimeter)) return true;
            
            // Check overlap with [0, normPEnd)
            if (Math.max(sStart, 0) < Math.min(sEnd, normPEnd)) return true;
            
        } else {
            // Standard interval (doesn't wrap) or covers entire perimeter
            // If covers entire perimeter
            if ((pEnd - pStart) >= perimeter) return true;
            
            // Standard check
            if (Math.max(sStart, normPStart) < Math.min(sEnd, normPEnd)) return true;
        }
        
        return false;
    }
}

/*
Based on the analysis, this is a constructive geometry and optimization problem. The key insight is that since we want to minimize the number of colors 
per side, we should always keep each paint color in a single contiguous block along the perimeter. Splitting a color into multiple separated blocks only 
  creates more boundaries, which increases the cost.Optimal ApproachModel the Perimeter:Imagine the rectangle's perimeter as a circle of length $2(L+W)$.
  The four corners of the rectangle are located at positions:$0$$L$$L + W$$2L + W$The four sides are the intervals between these corners: $[0, L]$, $[L, L+W]$, 
etc.Paint Placement:We arrange the three colors (Red, Green, Blue) as contiguous blocks on this circle. There are only two distinct circular orders for the 
  colors:Order 1: R $\rightarrow$ G $\rightarrow$ BOrder 2: R $\rightarrow$ B $\rightarrow$ GIn any such arrangement, there are exactly 3 boundaries between 
  colors (e.g., R|G, G|B, B|R).Cost Calculation:The base cost to paint the rectangle is 4 (1 per side).Every time a color boundary falls strictly inside a 
  side (not at a corner), the cost for that side increases by 1.Therefore, to minimize cost, we must maximize the number of boundaries that align with the 
  corners.The Algorithm:Since the optimal solution must align at least one color boundary with one corner, we can iterate through all "pinned" 
  configurations:Try both color orders (RGB, RBG).For each order, the relative positions of the boundaries are fixed (e.g., at $0$, $R$, and $R+G$).
  Align each of the 3 boundaries with each of the 4 corners. (Total $2 \times 3 \times 4 = 24$ candidates).For each alignment, calculate the exact cost by 
  checking which colors overlap with which side.The minimum cost found across all 24 checks is the answer.

  */
