/*
An array B of size M is called good if for all 1≤i≤M, either one of the following conditions hold:
- Bi = 1
- There exists j just that 1<= j < i and Bj = Bi - 1

Given an array A of size N, find the size of the largest good subsequence B of A. B is said to be a subsequence of 
A if it can be formed by deleting several elements from the array A without changing the order of the remaining elements.

input:
3
4
1 2 3 4
5
1 3 1 2 3
3
2 2 2

output:
4
4
0

Test Case 1 : The entire array is good.

Test Case 2 : The subsequence  [1,1,2,3] of length 4 is good.

Logic Explanation:
1. Keep track of the maximum value (current_max) you have successfully added to your subsequence sequence (starting at 0).

2. Iterate through the array. For each number X:If X = 1, you can always include it.If X > 1, you can include it only if you have already found an X-1 previously 
(which means X \le \text{current\_max} + 1).

3. If you include the number, update current_max if the new number extends the range (i.e., current_max = max(current_max, X)).
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
                int[] a = new int[n];
                for(int i=0; i<n; i++){
                    a[i] = sc.nextInt();
                }
                
                int ans = 0;
                int current_max = 0;
                
                for(int x : a){
                    if (x <= current_max + 1) {
                        ans++;
                        current_max = Math.max(current_max, x);
                    }
                }
                
                System.out.println(ans);
        }

	}
}
