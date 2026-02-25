/*
Chef is participating in a tournament.

He must face N opponents.

If he wins against the i-th opponent, he will receive Ai coins.
If he loses, he will receive Bi coins.

It is guaranteed that Ai ≥ Bi.

Chef decides that he must lose at least once.

Under this constraint, what is the maximum total number of coins he can receive?

1 ≤ T ≤ 1000
1 ≤ N ≤ 100
1 ≤ Bi ≤ Ai ≤ 100


input:
4
3
5 3 8
3 3 4
1
10
5
4
6 3 8 7
5 1 5 1
4
9 8 7 6
9 8 7 6

output:
16
5
23
30

Explanation:

Test case 1:
It is optimal for Chef to win the first match, lose the second match, and win the third match.

Total coins:
A1 + B2 + A3 = 5 + 3 + 8 = 16

--------------------------------------------------

Test case 2:
There is only one match.

Chef must lose at least once, so he has no choice but to lose it.

Coins received:
B1 = 5
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
            int A[] = new int[n];
            int B[] = new int[n];
            int maxPossibleSum = 0;
            
		    for(int i=0; i<n; i++){
		        A[i] = sc.nextInt();
		        maxPossibleSum += A[i];
		    }
		    for(int i=0; i<n; i++){
		        B[i] = sc.nextInt();
		    }
		    
		    if(n == 1){
		        System.out.println(B[0]);
		        continue;
		    }
		    
		    int ans = 0;
		    for(int i=0; i<n; i++){
		        int num = maxPossibleSum - A[i] + B[i];
		        ans = Math.max(ans, num);
		    }
		    
		    System.out.println(ans);
		    
		}

	}
}
