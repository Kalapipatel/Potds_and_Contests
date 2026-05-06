/*
Finding All

Define a function f(x, y) for integers x, y such that -1 ≤ x, y ≤ 1:

f(x, y) = -1 if x < y
f(x, y) =  0 if x = y
f(x, y) =  1 if x > y

You are given an array A of N integers where:

-1 ≤ Ai ≤ 1

Initially:
X = 0

--------------------------------------------------

Process:

For each i from 1 to N:

X = f(X, Ai)

--------------------------------------------------

Task:

Over all possible rearrangements of the array A,
determine all possible final values of X.

Print the values in sorted increasing order.

--------------------------------------------------

Input Format:

- First line contains T, the number of test cases.
- For each test case:
  - First line contains N
  - Second line contains:
    A1, A2, ..., AN

--------------------------------------------------

Output Format:

For each test case, print all possible final values of X
in sorted increasing order.

--------------------------------------------------

Constraints:

1 ≤ T ≤ 10^4
1 ≤ N ≤ 2·10^5
-1 ≤ Ai ≤ 1

The sum of N over all test cases does not exceed 2·10^5.

--------------------------------------------------

Sample Input:

3
3
1 0 -1
3
1 1 -1
2
0 0

--------------------------------------------------

Sample Output:

0
-1 0
0

--------------------------------------------------

Explanation:

Test Case 1:

Consider the arrangement:
[1, 0, -1]

Initially:
X = 0

Step 1:
X = f(0, 1) = -1

Step 2:
X = f(-1, 0) = -1

Step 3:
X = f(-1, -1) = 0

Final value:
0

It can be shown that every rearrangement also results in X = 0.

--------------------------------------------------

Test Case 2:

Arrangement:
[1, 1, -1]

Produces:
X = 0

Arrangement:
[1, -1, 1]

Produces:
X = -1
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            int c1 = 0;
            int cMinus1 = 0;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    c1++;
                } else if (val == -1) {
                    cMinus1++;
                }
            }
            
            if (c1 == 0 && cMinus1 == 0) {
                sb.append("0\n");
            } else if (c1 == 0 && cMinus1 > 0) {
                sb.append("1\n");
            } else if (c1 > 0 && cMinus1 == 0) {
                sb.append("-1\n");
            } else {
                if (c1 >= 2) {
                    sb.append("-1 ");
                }
                sb.append("0");
                if (cMinus1 >= 2) {
                    sb.append(" 1");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());

	}
}
