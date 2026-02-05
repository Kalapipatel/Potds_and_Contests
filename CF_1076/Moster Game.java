/*
You have been gifted a new game called "Elathy". In the game, you are given n swords, each with own strength. In particular sword numberd i has a strength of ai. 
The game consists of n levels, each of which features a moster.

You start at level 1 and progress further. To pass level i and move on to level i+1, you need to defeat the monster at level i. To defeat the monster at level i
, you need to deal it bi sword strikes. The swords in the game are very fragile, so they can only deal one strike before breaking. If you complete level n
or run out of swords, you can finish the game and proceed to score calculation.

Before the game, you are allowed to choose the difficulty level. If you choose difficulty x, swords with a strength less than x
will not affect the monsters. The game score in this case is equal to x
multiplied by the number of levels completed. Your task is to choose the game difficulty in such a way as to maximize the game score.

input:
5
3
1 3 4
2 1 1
2
2 3
1 1
4
1 2 3 4
2 2 1 1
6
4 4 1 4 5 4
2 2 4 1 2 2
10
1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000
1 1 1 1 1 1 1 1 1 1

output:
3
4
3
8
10000000000
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int T = sc.nextInt();
            while (T-- > 0) {
                int n = sc.nextInt();
                
                long[] a = new long[n];
                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextLong();
                }
                
                long[] b = new long[n];
                for (int i = 0; i < n; i++) {
                    b[i] = sc.nextLong();
                }

                Arrays.sort(a);

                long[] cost = new long[n + 1];
                cost[0] = 0;
                for (int i = 0; i < n; i++) {
                    cost[i + 1] = cost[i] + b[i];
                }

                long maxScore = 0;
                int levelPtr = 0;

                for (int k = 1; k <= n; k++) {
                    long currentDifficulty = a[n - k];
                    
                    while (levelPtr < n && cost[levelPtr + 1] <= k) {
                        levelPtr++;
                    }
                    
                    long currentScore = currentDifficulty * levelPtr;
                    maxScore = Math.max(maxScore, currentScore);
                }
                
                System.out.println(maxScore);
            }
        }
    }
}

/*
The game asks you to make a strategic trade-off between difficulty and progress.

High Difficulty (x)

Increases your multiplier (score)

Fewer swords are usable (weak swords break)

You pass fewer levels

Low Difficulty (x)

Decreases your multiplier

Almost all swords are usable

You pass more levels

👉 Goal:
Maximize the value:

Score = Difficulty (x) × Levels Completed


You must find the sweet spot where this product is maximized.

⚔️ Key Rules to Remember
1. Swords Are Ammo

Any sword with strength ≥ x is valid.

Every valid sword counts as 1 unit of ammo, regardless of strength.

A sword is used once and then breaks.

Example:

Difficulty x = 5

Sword strength 5 and sword strength 100 are equivalent.

2. Cumulative Level Cost

To pass level k, you must pay the cost of all previous levels.

Total cost to pass k levels = b1 + b2 + ... + bk

🧪 Sample 1 Explanation
Input
N = 3   (3 swords, 3 levels)
Swords (a): [1, 3, 4]
Level Costs (b): [2, 1, 1]

Cumulative Level Costs

Level 1 → 2 swords

Level 2 → 2 + 1 = 3 swords

Level 3 → 2 + 1 + 1 = 4 swords

Scenario A: Difficulty x = 4

Valid swords: {4}

Total swords: 1

Progress:

Level 1 cost = 2 → ❌ (1 < 2)

Result:

Levels Passed = 0
Score = 4 × 0 = 0

Scenario B: Difficulty x = 3

Valid swords: {3, 4}

Total swords: 2

Progress:

Level 1 cost = 2 → ✅

Level 2 cost = 1 → ❌ (0 left)

Result:

Levels Passed = 1
Score = 3 × 1 = 3

Scenario C: Difficulty x = 1

Valid swords: {1, 3, 4}

Total swords: 3

Progress:

Level 2 cost = 3 → ✅

Level 3 cost = 4 → ❌

Result:

Levels Passed = 2
Score = 1 × 2 = 2

✅ Winner
Maximum Score = 3 (Difficulty x = 3)

🔍 Complex Example

This example shows why neither max difficulty nor min difficulty is always optimal.

Setup
Swords (a): [10, 10, 10, 6, 6, 6, 6, 2]
Level Costs (b): [2, 1, 4, 100]

Cumulative Costs

1 level → 2

2 levels → 3

3 levels → 7

4 levels → 107

Option 1: High Difficulty (x = 10)

Valid swords: [10, 10, 10]

Total swords: 3

Progress:

2 levels → cost 3 → ✅

3 levels → cost 7 → ❌

Result:

Levels Passed = 2
Score = 10 × 2 = 20

Option 2: Medium Difficulty (x = 6)

Valid swords: [10, 10, 10, 6, 6, 6, 6]

Total swords: 7

Progress:

3 levels → cost 7 → ✅

4 levels → cost 107 → ❌

Result:

Levels Passed = 3
Score = 6 × 3 = 18

Option 3: Low Difficulty (x = 2)

Valid swords: all swords

Total swords: 8

Progress:

3 levels → cost 7 → ✅

4 levels → cost 107 → ❌

Result:

Levels Passed = 3
Score = 2 × 3 = 6

🏆 Final Conclusion

Even though lower difficulty allows you to pass more levels, a higher multiplier can still win.

Best Score = 20 (Difficulty x = 10)


👉 Always optimize Difficulty × Levels Passed, not just one of them.
*/
