/*
Dividing Candies

Chef has gone to the candy store to buy a jar of candies for his kids.

There are N jars, where the i-th jar contains Ai candies.

Chef has X children, so he wants a jar whose number of candies is evenly divisible by X,
so that all children receive the same number of candies.

Among all valid jars, Chef chooses the one with the maximum number of candies.

If no such jar exists, output 0.

--------------------------------------------------

Input Format:

- The first line contains an integer T, the number of test cases.
- Each test case contains:
  - A line with two integers N and X.
  - A line with N integers:
    A1, A2, ..., AN

--------------------------------------------------

Output Format:

For each test case, print:
- The maximum Ai divisible by X
- Or 0 if no such jar exists

--------------------------------------------------

Constraints:

1 ≤ T ≤ 100
1 ≤ N ≤ 100
1 ≤ X ≤ 5
1 ≤ Ai ≤ 100

--------------------------------------------------

Sample Input:

4
4 1
2 3 5 7
4 3
2 3 5 7
4 3
3 9 11 6
4 4
3 9 11 6

--------------------------------------------------

Sample Output:

7
3
9
0

--------------------------------------------------

Explanation:

Test Case 1:
Every number is divisible by 1.
The largest jar contains 7 candies.

--------------------------------------------------

Test Case 2:
Only 3 is divisible by 3.

Answer:
3
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int t = scanner.nextInt();
            while (t-- > 0) {
                int n = scanner.nextInt();
                int x = scanner.nextInt();
                int maxCandies = 0;
                
                for (int i = 0; i < n; i++) {
                    int a = scanner.nextInt();
                    if (a % x == 0 && a > maxCandies) {
                        maxCandies = a;
                    }
                }
                System.out.println(maxCandies);
            }
        }
        scanner.close();

	}
}
