/*
**Problem Statement:** You have a gun with a magazine of `n` bullets, where the `i-th` bullet deals `a[i]` damage. Firing takes 1 second per bullet, 
and reloading the whole magazine takes `k` seconds. The enemy has `h` health. You are allowed to swap at most one pair of bullets before firing. The goal is 
to find the minimum time required to kill the enemy.

**Core Logic ($O(n)$ time):**
Because the total damage of a fully emptied magazine never changes, swapping bullets only optimizes the **final, partially fired magazine**.
1. We precompute the prefix sums of the damage.
2. For any target ending bullet `L` (from 1 to `n`), we find the optimal swap: swapping the smallest bullet inside the first `L` bullets with the largest bullet outside of it. (Precomputed using `minIn` and `maxOut` arrays).
3. We calculate the maximum possible damage for the first `L` bullets. The remaining health must be handled by full magazine cycles.
4. We calculate the total time for each possible ending bullet `L` and return the minimum time across all possibilities.

The Optimized LogicYou don't need to simulate the swaps or cycles second by second.
1. The total damage of a full magazine cycle, $S$, never changes regardless of the swap. Every element will be fired.
2. The only thing the swap optimizes is the last partial cycle.
3. For any target stopping point (bullet index $L$ from 1 to $n$), the maximum damage you can deal is the sum of the first $L$ elements plus the benefit of the optimal swap.
4. The optimal swap for a prefix of length $L$ is taking the smallest bullet inside the prefix and swapping it with the largest bullet outside the prefix (if the outside bullet is larger).
5. By calculating this max possible damage for all possible ending indices $L$, you can mathematically determine how many full cycles are required to deal the remaining health, giving you the exact time in $O(n)$.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            
            long[] a = new long[n];
            long S = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
                S += a[i];
            }
            
            // Precompute the minimum element inside the prefix up to index i
            long[] minIn = new long[n];
            long minSoFar = a[0];
            for (int i = 0; i < n; i++) {
                minSoFar = Math.min(minSoFar, a[i]);
                minIn[i] = minSoFar;
            }
            
            // Precompute the maximum element outside the prefix (from i+1 to n-1)
            long[] maxOut = new long[n];
            long maxSoFar = 0;
            for (int i = n - 1; i >= 0; i--) {
                maxOut[i] = maxSoFar;
                maxSoFar = Math.max(maxSoFar, a[i]);
            }
            
            // Precompute standard prefix sums
            long[] P = new long[n];
            long currentP = 0;
            for (int i = 0; i < n; i++) {
                currentP += a[i];
                P[i] = currentP;
            }
            
            long minTime = Long.MAX_VALUE;
            
            // Try ending the game on the L-th bullet of the final cycle
            for (int L = 1; L <= n; L++) {
                // Max damage we can do with L bullets (using at most 1 optimal swap)
                long maxP = P[L - 1];
                if (maxOut[L - 1] > minIn[L - 1]) {
                    maxP += (maxOut[L - 1] - minIn[L - 1]);
                }
                
                // Calculate remaining health that must be covered by full cycles
                long rem = h - maxP;
                long C = 0;
                
                if (rem > 0) {
                    // Ceiling division: ceil(rem / S)
                    C = (rem + S - 1) / S;
                }
                
                // Time taken = (Full cycles * Time per cycle) + Extra bullets
                long time = C * (n + k) + L;
                minTime = Math.min(minTime, time);
            }
            
            System.out.println(minTime);
        }
    }
}
